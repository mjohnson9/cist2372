package johnson.michael.travelexpenses;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * EntryFrame is a frame for entering company travel expenses. It takes responsibility for
 * continuation of the program.
 */
public class EntryFrame extends JFrame {
  /**
   * The text field for the number of days that the trip lasted.
   */
  private final JTextField daysField = new JTextField(8);
  /**
   * The text field for the cost of airfare.
   */
  private final JTextField airfareField = new JTextField("0.00", 8);
  /**
   * The text field for the cost of renting a vehicle.
   */
  private final JTextField rentalFeesField = new JTextField("0.00", 8);
  /**
   * The text field for the number of miles driven.
   */
  private final JTextField milesDrivenField = new JTextField("0.0", 8);
  /**
   * The text field for the cost of parking fees.
   */
  private final JTextField parkingFeesField = new JTextField("0.00", 8);
  /**
   * The text field for the cost of using taxis.
   */
  private final JTextField taxiFeesField = new JTextField("0.00", 8);
  /**
   * The text field for the cost of the conference.
   */
  private final JTextField conferenceFeesField = new JTextField("0.00", 8);
  /**
   * The text field for the cost of lodging.
   */
  private final JTextField lodgingFeesField = new JTextField("0.00", 8);
  /**
   * The submit button.
   */
  private final JButton submitButton = new JButton("Submit");

  /**
   * Instantiates a new EntryFrame. This assembles all of the entry frame's elements and prepares it
   * for display.
   */
  public EntryFrame() {
    super();

    this.setTitle("Travel Expense Calculator");

    this.setLayout(new GridLayout(9, 2, 5, 5));

    this.add(new JLabel("How many days was the trip?", JLabel.RIGHT));
    this.add(this.daysField);

    this.add(new JLabel("Airfare:", JLabel.RIGHT));
    this.add(this.airfareField);

    this.add(new JLabel("Rental fees:", JLabel.RIGHT));
    this.add(this.rentalFeesField);

    this.add(new JLabel("Miles driven in a personal vehicle:", JLabel.RIGHT));
    this.add(this.milesDrivenField);

    this.add(new JLabel("Parking fees:", JLabel.RIGHT));
    this.add(this.parkingFeesField);

    this.add(new JLabel("Taxi fees:", JLabel.RIGHT));
    this.add(this.taxiFeesField);

    this.add(new JLabel("Conference fees:", JLabel.RIGHT));
    this.add(this.conferenceFeesField);

    this.add(new JLabel("Lodging fees:", JLabel.RIGHT));
    this.add(this.lodgingFeesField);

    this.add(new JLabel());
    this.add(this.submitButton);
    this.submitButton.addActionListener(new SubmitHandler());

    this.pack();
    final Dimension packedSize = this.getSize();
    this.setMinimumSize(packedSize);
    this.setSize(packedSize.width + 20, packedSize.height + 20);
  }

  /**
   * {@code SubmitHandler} handles a press of the submit button. It processes the {@code EntryFrame}
   * text field contents and, if valid, displays a ResultsFrame.
   */
  private class SubmitHandler implements ActionListener {
    @Override
    public void actionPerformed(final ActionEvent e) {
      final Expenses expenses = new Expenses();

      // If any of the handler functions return false, they failed to parse. In that case, do
      // nothing.
      if (!this.handleDays(expenses)) {
        return;
      }
      if (!this.handleAirfare(expenses)) {
        return;
      }
      if (!this.handleRentalFees(expenses)) {
        return;
      }
      if (!this.handleMilesDriven(expenses)) {
        return;
      }
      if (!this.handleParkingFees(expenses)) {
        return;
      }
      if (!this.handleTaxiFees(expenses)) {
        return;
      }
      if (!this.handleConferenceFees(expenses)) {
        return;
      }
      if (!this.handleLodgingFees(expenses)) {
        return;
      }

      if (expenses.getTotal() == 0.00d) {
        JOptionPane.showMessageDialog(EntryFrame.this,
            "Are you sure that you had no expenses? Please go back and enter some expenses.");
        return;
      }

      EntryFrame.this.setVisible(false);

      final ResultsFrame resultsFrame = new ResultsFrame(expenses);
      resultsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      resultsFrame.setVisible(true);
    }

    /**
     * Parses the {@code daysField} text field and enters its value into {@code expenses}.
     * @param expenses The Expenses object to enter the parsed, validated number of days into.
     * @return A boolean indicating whether or not validation was successful. False on a failed
     *     parsing or validation.
     */
    private boolean handleDays(final Expenses expenses) {
      try {
        final int numDays = Integer.parseInt(EntryFrame.this.daysField.getText());

        if (numDays < 0) {
          JOptionPane.showMessageDialog(EntryFrame.this, "The number of days cannot be negative.");
          return false;
        }
        if (numDays == 0) {
          JOptionPane.showMessageDialog(EntryFrame.this, "The number of days cannot be zero.");
          return false;
        }

        expenses.setDays(numDays);
        return true;
      } catch (final NumberFormatException e) {
        JOptionPane.showMessageDialog(EntryFrame.this,
            "The number of days that you entered is not a valid number. Please enter a number.");
        return false;
      }
    }

    /**
     * Parses the {@code airfareField} text field and enters its value into {@code expenses}.
     * @param expenses The Expenses object to enter the parsed, validated airfare into.
     * @return A boolean indicating whether or not validation was successful. False on a failed
     *     parsing or validation.
     */
    private boolean handleAirfare(final Expenses expenses) {
      try {
        expenses.setAirfare(this.parseField(EntryFrame.this.airfareField, "airfare"));
        return true;
      } catch (final IllegalArgumentException e) {
        JOptionPane.showMessageDialog(EntryFrame.this, e.getMessage());
        return false;
      }
    }

    /**
     * Parses the {@code rentalFeesField} text field and enters its value into {@code expenses}.
     * @param expenses The Expenses object to enter the parsed, validated rental fees into.
     * @return A boolean indicating whether or not validation was successful. False on a failed
     *     parsing or validation.
     */
    private boolean handleRentalFees(final Expenses expenses) {
      try {
        expenses.setRentalFees(this.parseField(EntryFrame.this.rentalFeesField, "rental fees"));
        return true;
      } catch (final IllegalArgumentException e) {
        JOptionPane.showMessageDialog(EntryFrame.this, e.getMessage());
        return false;
      }
    }

    /**
     * Parses the {@code milesDrivenField} text field and enters its value into {@code expenses}.
     * @param expenses The Expenses object to enter the parsed, validated miles driven into.
     * @return A boolean indicating whether or not validation was successful. False on a failed
     *     parsing or validation.
     */
    private boolean handleMilesDriven(final Expenses expenses) {
      try {
        expenses.setMilesDriven(this.parseField(EntryFrame.this.milesDrivenField, "miles driven"));
        return true;
      } catch (final IllegalArgumentException e) {
        JOptionPane.showMessageDialog(EntryFrame.this, e.getMessage());
        return false;
      }
    }

    /**
     * Parses the {@code parkingFeesField} text field and enters its value into {@code expenses}.
     * @param expenses The Expenses object to enter the parsed, validated parking fees into.
     * @return A boolean indicating whether or not validation was successful. False on a failed
     *     parsing or validation.
     */
    private boolean handleParkingFees(final Expenses expenses) {
      try {
        expenses.setParkingFees(this.parseField(EntryFrame.this.parkingFeesField, "parking fees"));
        return true;
      } catch (final IllegalArgumentException e) {
        JOptionPane.showMessageDialog(EntryFrame.this, e.getMessage());
        return false;
      }
    }

    /**
     * Parses the {@code taxiFeesField} text field and enters its value into {@code expenses}.
     * @param expenses The Expenses object to enter the parsed, validated taxi fees into.
     * @return A boolean indicating whether or not validation was successful. False on a failed
     *     parsing or validation.
     */
    private boolean handleTaxiFees(final Expenses expenses) {
      try {
        expenses.setTaxiFees(this.parseField(EntryFrame.this.taxiFeesField, "taxi fees"));
        return true;
      } catch (final IllegalArgumentException e) {
        JOptionPane.showMessageDialog(EntryFrame.this, e.getMessage());
        return false;
      }
    }

    /**
     * Parses the {@code conferenceFeesField} text field and enters its value into {@code expenses}.
     * @param expenses The Expenses object to enter the parsed, validated conference fees into.
     * @return A boolean indicating whether or not validation was successful. False on a failed
     *     parsing or validation.
     */
    private boolean handleConferenceFees(final Expenses expenses) {
      try {
        expenses.setConferenceFees(
            this.parseField(EntryFrame.this.conferenceFeesField, "conference fees"));
        return true;
      } catch (final IllegalArgumentException e) {
        JOptionPane.showMessageDialog(EntryFrame.this, e.getMessage());
        return false;
      }
    }

    /**
     * Parses the {@code lodgingFeesField} text field and enters its value into {@code expenses}.
     * @param expenses The Expenses object to enter the parsed, validated lodging fees into.
     * @return A boolean indicating whether or not validation was successful. False on a failed
     *     parsing or validation.
     */
    private boolean handleLodgingFees(final Expenses expenses) {
      try {
        expenses.setLodgingCost(this.parseField(EntryFrame.this.lodgingFeesField, "lodging fees"));
        return true;
      } catch (final IllegalArgumentException e) {
        JOptionPane.showMessageDialog(EntryFrame.this, e.getMessage());
        return false;
      }
    }

    /**
     * Parses a JTextField that's expected to be a dollar amount.
     *
     * @param field The field to parse.
     * @param fieldName The name of the field for error messages.
     *
     * @return The parsed, validated value.
     *
     * @throws IllegalArgumentException If the JTextField does not contain a valid dollar
     *     amount, this exception is thrown with a user-friendly message.
     */
    private double parseField(final JTextField field, final String fieldName)
        throws IllegalArgumentException {
      try {
        final double parsed = Double.parseDouble(field.getText());
        if (parsed < 0) {
          throw new IllegalArgumentException("The " + fieldName + " cannot be negative.");
        }

        return parsed;
      } catch (final NumberFormatException e) {
        throw new IllegalArgumentException(
            "The " + fieldName + " must be a decimal number. You did not enter a decimal number.",
            e);
      }
    }
  }
}
