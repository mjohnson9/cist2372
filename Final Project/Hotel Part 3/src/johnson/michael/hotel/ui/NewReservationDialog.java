package johnson.michael.hotel.ui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.util.LinkedList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import johnson.michael.hotel.Guest;
import johnson.michael.hotel.Hotel;
import johnson.michael.hotel.RoomType;
import johnson.michael.hotel.exceptions.NoVacancyException;
import johnson.michael.hotel.exceptions.OverCapacityException;

/**
 * A dialog for creating a new reservation.
 */
public class NewReservationDialog extends JDialog {
  /**
   * The hotel we're making a reservation for.
   */
  private final Hotel hotel;
  /**
   * A text field for entering the guest's first name.
   */
  private final JTextField firstNameField;
  /**
   * A text field for entering the guest's last name.
   */
  private final JTextField lastNameField;
  /**
   * A text field for entering the guest's street address.
   */
  private final JTextField streetAddressField;
  /**
   * A text field for entering the guest's city, state, and ZIP code.
   */
  private final JTextField cityStateField;
  /**
   * A text field for entering the number of adults in the guest's party.
   */
  private final JTextField adultsField;
  /**
   * A text field for entering the number of children in the guest's party.
   */
  private final JTextField childrenField;
  /**
   * A dropdown menu for choosing a room type.
   */
  private final JComboBox<RoomType> roomTypeField;
  /**
   * A text field for entering the number of nights for the stay.
   */
  private final JTextField numberOfNightsField;

  /**
   * Constructs an instance of this class. Note that you must still display the dialog yourself.
   * @param hotel The {@see Hotel} to create a reservation for.
   */
  public NewReservationDialog(final Frame parent, final Hotel hotel) {
    super(parent, true);

    this.setLayout(new GridLayout(5, 4));

    this.hotel = hotel;

    // First name
    this.firstNameField = new JTextField();
    this.addFieldAndLabel(this.firstNameField, "First name:");

    // Last name
    this.lastNameField = new JTextField();
    this.addFieldAndLabel(this.lastNameField, "Last name:");

    // Street address
    this.streetAddressField = new JTextField();
    this.addFieldAndLabel(this.streetAddressField, "Street address:");

    // City/state field
    this.cityStateField = new JTextField();
    this.addFieldAndLabel(this.cityStateField, "City, state, and ZIP code:");

    // Adults field
    this.adultsField = new JTextField();
    this.addFieldAndLabel(this.adultsField, "Number of adults:");

    // Adults field
    this.childrenField = new JTextField();
    this.addFieldAndLabel(this.childrenField, "Number of children:");

    // Room type
    this.roomTypeField = new JComboBox<>(RoomType.values());
    this.addFieldAndLabel(this.roomTypeField, "Room type:");

    // Number of nights
    this.numberOfNightsField = new JTextField();
    this.addFieldAndLabel(this.numberOfNightsField, "Number of nights:");

    // Add two blank JLabels to fill the first two columns
    this.add(new JLabel());
    this.add(new JLabel());

    // The cancel and submit buttons
    this.addCancelButton();
    this.addSubmitButton();

    // Pack the panel and use the packed size as the minimum size
    this.pack();
    final Dimension packedSize = this.getSize();
    this.setMinimumSize(packedSize);
  }

  /**
   * Adds a field and its label to the dialog.
   * @param textField The text field to add.
   * @param labelText The text for the text field's label.
   */
  private void addFieldAndLabel(final JComponent textField, final String labelText) {
    final JLabel label = new JLabel(labelText);
    label.setHorizontalAlignment(JLabel.RIGHT);
    label.setLabelFor(textField);

    this.add(label);
    this.add(textField);
  }

  /**
   * Adds a cancel button to the dialog.
   */
  private void addCancelButton() {
    final JButton cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(e -> this.close());

    this.add(cancelButton);
  }

  /**
   * Adds a submit button to the dialog.
   */
  private void addSubmitButton() {
    final JButton submitButton = new JButton("Submit");
    submitButton.addActionListener(e -> this.submit());

    this.add(submitButton);

    // Make pressing enter to submit the form work
    this.getRootPane().setDefaultButton(submitButton);
  }

  /**
   * Closes this dialog and disposes of consumed resources.
   */
  private void close() {
    this.setVisible(false);
    this.dispose();
  }

  /**
   * Attempts to submit the dialog's form. Validation is performed on all inputs and the submission
   * is cancelled if the form doesn't pass validation.
   */
  private void submit() {
    final LinkedList<String> errors = new LinkedList<>();

    final Guest guest = this.validateGuest(errors);

    // RoomType is the only thing the user can select, so a cast is safe.
    final RoomType roomType = (RoomType) this.roomTypeField.getSelectedItem();

    int numNights = 0;
    final String numNightsString = this.numberOfNightsField.getText().trim();
    if (numNightsString.isEmpty()) {
      errors.add("The number of nights cannot be empty.");
    } else {
      try {
        numNights = Integer.parseInt(numNightsString);

        if (numNights < 0) {
          errors.add("The number of nights cannot be negative.");
        } else if (numNights == 0) {
          errors.add("The guest must stay for at least one night.");
        }
      } catch (final NumberFormatException ex) {
        errors.add("The number of nights must be a number.");
      }
    }

    if (!errors.isEmpty()) {
      // Show the errors to the user
      JOptionPane.showMessageDialog(
          this, String.join("\n", errors), "Form Invalid", JOptionPane.ERROR_MESSAGE);
      // There were reservation errors; tell the calling function by returning null
      return;
    }

    try {
      this.hotel.bookRoom(roomType, guest, numNights);
    } catch (final NoVacancyException ex) {
      JOptionPane.showMessageDialog(this, "There are no rooms of the chosen type available.",
          "Occupancy Error", JOptionPane.ERROR_MESSAGE);
      return;
    } catch (final OverCapacityException ex) {
      JOptionPane.showMessageDialog(this,
          String.format(
              "The entered party is too large for the chosen room.%nA %s can hold %,d people, while the guest's%nparty has %,d people.",
              roomType, ex.getRoomCapacity(), ex.getPartySize()),
          "Occupancy Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    this.close();
  }

  /**
   * Validates and constructs a {@see Guest} from the dialog form.
   * @param errors The list of errors to add validation error messages to.
   * @return A fully constructed {@see Guest} if there are no validation errors or null if there
   *     were validation errors.
   */
  private Guest validateGuest(final LinkedList<String> errors) {
    final Guest guest = new Guest();

    final String firstName = this.firstNameField.getText().trim();
    guest.setFirstName(firstName);
    if (firstName.isEmpty()) {
      errors.add("First name cannot be blank.");
    }

    final String lastName = this.lastNameField.getText().trim();
    guest.setLastName(lastName);
    if (lastName.isEmpty()) {
      errors.add("Last name cannot be blank.");
    }

    final String streetAddress = this.streetAddressField.getText().trim();
    if (streetAddress.isEmpty()) {
      errors.add("Street address cannot be blank.");
    }

    final String cityState = this.cityStateField.getText().trim();
    if (cityState.isEmpty()) {
      errors.add("City, state, and ZIP code cannot be blank.");
    }

    final String fullAddress = streetAddress + '\n' + cityState;
    guest.setAddress(fullAddress);

    final String numAdultsString = this.adultsField.getText().trim();
    if (numAdultsString.isEmpty()) {
      errors.add("Number of adults cannot be blank.");
    } else {
      try {
        final int numAdults = Integer.parseInt(numAdultsString);
        guest.setNumberAdultsInParty(numAdults);

        if (numAdults < 0) {
          errors.add("Number of adults cannot be negative.");
        } else if (numAdults == 0) {
          errors.add("There must be at least one adult on the reservation.");
        }
      } catch (final NumberFormatException ex) {
        errors.add("Number of adults must be a number.");
      }
    }

    final String numChildrenString = this.childrenField.getText().trim();
    if (numChildrenString.isEmpty()) {
      errors.add("Number of children cannot be blank.");
    } else {
      try {
        final int numChildren = Integer.parseInt(numChildrenString);
        guest.setNumberChildrenInParty(numChildren);

        if (numChildren < 0) {
          errors.add("Number of children cannot be negative.");
        }
      } catch (final NumberFormatException ex) {
        errors.add("Number of children must be a number.");
      }
    }

    if (!errors.isEmpty()) {
      // There were reservation errors; tell the calling function by returning null
      return null;
    }

    return guest;
  }
}
