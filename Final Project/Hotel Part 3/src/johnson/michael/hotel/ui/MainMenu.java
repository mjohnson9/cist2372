package johnson.michael.hotel.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import johnson.michael.hotel.Hotel;
import johnson.michael.hotel.Reservation;
import johnson.michael.hotel.Status;
import johnson.michael.hotel.exceptions.NoVacancyException;

/**
 * The main menu for the reservation GUI. It contains a table listing all reservations as well as
 * buttons to take actions on those reservations.
 */
public class MainMenu extends JFrame {
  /**
   * Set a minimum height because pack will make the window very short when there are no
   * reservations.
   */
  private static final int MIN_HEIGHT = 400;
  /**
   * The hotel we're operating on.
   */
  private final Hotel hotel;

  /**
   * The table holding the list of reservations.
   */
  private final JTable reservationTable;

  /**
   * The table model for {@code reservationTable}. It's used to notify the table that it needs to
   * update.
   */
  private final ReservationsTableModel reservationTableModel;

  /**
   * Constructs an instance of this class.
   * @param hotel The hotel to operate this menu on.
   */
  public MainMenu(final Hotel hotel) {
    super();

    this.hotel = hotel;

    this.setLayout(new BorderLayout());
    this.setTitle("Hotel");

    this.reservationTableModel = new ReservationsTableModel(hotel.getReservations());

    this.reservationTable = new JTable(this.reservationTableModel);
    this.reservationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    this.add(new JScrollPane(this.reservationTable), BorderLayout.CENTER);

    final JPanel buttonPanel = this.createButtonPanel();
    this.add(buttonPanel, BorderLayout.SOUTH);

    // Pack and set the minimum size to the packed size
    this.pack();
    final Dimension packedSize = this.getSize();
    if (packedSize.height < MIN_HEIGHT) {
      packedSize.height = MIN_HEIGHT;
      this.setSize(packedSize);
    }
    this.setMinimumSize(packedSize);
  }

  /**
   * Creates a panel with buttons to be put in the SOUTH part of the primary panel.
   * @return The created panel with button children.
   */
  private JPanel createButtonPanel() {
    final JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    final JButton createReservationButton = new JButton("Create reservation");
    createReservationButton.addActionListener(e -> this.createReservation());
    panel.add(createReservationButton);

    final JButton checkInButton = new JButton("Check in");
    checkInButton.addActionListener(e -> this.checkIn());
    panel.add(checkInButton);

    final JButton checkOutButton = new JButton("Check out");
    checkOutButton.addActionListener(e -> this.checkOut());
    panel.add(checkOutButton);

    return panel;
  }

  /**
   * Opens the dialog for creating new reservations.
   */
  private void createReservation() {
    final int startingReservationsSize = this.hotel.getReservations().size();

    final NewReservationDialog dialog = new NewReservationDialog(this, this.hotel);
    dialog.setLocationRelativeTo(this);
    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    dialog.setVisible(true); // Because the dialog is modal, this blocks until the dialog is closed

    final int endingReservationsSize = this.hotel.getReservations().size();

    if (endingReservationsSize > startingReservationsSize) {
      // The dialog wasn't cancelled and a reservation was added
      this.reservationTableModel.reservationAdded();
    }
  }

  /**
   * Handles a click on the "Check in" button. Attempts to check in the selected reservation.
   */
  private void checkIn() {
    final int selectedIndex = this.reservationTable.getSelectedRow();
    if (selectedIndex == -1) {
      // No row is selected
      JOptionPane.showMessageDialog(
          this, "You must select a reservation to check in.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    final Reservation reservation = this.hotel.getReservations().get(selectedIndex);

    if (reservation.getStatus() == Status.VALID) {
      // The guest has not yet checked in
      JOptionPane.showMessageDialog(
          this, "That reservation has already checked in.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    try {
      reservation.checkIn();
      this.reservationTableModel.reservationCheckedIn(selectedIndex);
    } catch (final NoVacancyException ex) {
      JOptionPane.showMessageDialog(this,
          "There are no vacant " + reservation.getRoomType() + " rooms.", "Error",
          JOptionPane.ERROR_MESSAGE);
      return;
    }
  }

  /**
   * Handles a click on the "Check out" button. Attempts to check out the selected reservation.
   */
  private void checkOut() {
    final int selectedIndex = this.reservationTable.getSelectedRow();
    if (selectedIndex == -1) {
      // No row is selected
      JOptionPane.showMessageDialog(
          this, "You must select a reservation to check out.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    final Reservation reservation = this.hotel.getReservations().get(selectedIndex);

    if (reservation.getStatus() == Status.INVALID) {
      // The guest has not yet checked in
      JOptionPane.showMessageDialog(
          this, "That reservation has not yet checked in.", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    reservation.checkOut();
    this.reservationTableModel.reservationCheckedOut(selectedIndex);
  }
}
