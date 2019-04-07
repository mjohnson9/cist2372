package johnson.michael.hotel.ui;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import johnson.michael.hotel.Guest;
import johnson.michael.hotel.Reservation;
import johnson.michael.hotel.RoomType;
import johnson.michael.hotel.Status;

/**
 * A {@see TableModel} for a list of reservations.
 */
public class ReservationsTableModel extends AbstractTableModel {
  /**
   * A reference to the {@see Hotel}'s list of reservations.
   */
  private final ArrayList<Reservation> reservations;

  /**
   * Constructs an instance of this class.
   * @param reservations The reservation list to work on.
   */
  public ReservationsTableModel(final ArrayList<Reservation> reservations) {
    super();

    if (reservations == null) {
      throw new IllegalArgumentException("reservations cannot be null");
    }

    this.reservations = reservations;
  }

  @Override
  public int getRowCount() {
    return this.reservations.size();
  }

  @Override
  public int getColumnCount() {
    final TableColumn[] columns = TableColumn.values();
    return columns.length;
  }

  @Override
  public String getColumnName(final int column) {
    final TableColumn[] columns = TableColumn.values();
    final TableColumn tableColumn = columns[column];
    return tableColumn.toString();
  }

  @Override
  public Class<?> getColumnClass(final int columnIndex) {
    final TableColumn[] columns = TableColumn.values();
    final TableColumn column = columns[columnIndex];

    switch (column) {
      case RESERVATION_STATUS:
        return Status.class;
      case ROOM_TYPE:
        return RoomType.class;
      default:
        return String.class;
    }
  }

  @Override
  public Object getValueAt(final int rowIndex, final int columnIndex) {
    final Reservation reservation = this.reservations.get(rowIndex);

    final TableColumn[] tableColumns = TableColumn.values();
    final TableColumn column = tableColumns[columnIndex];

    switch (column) {
      case RESERVATION_NUMBER:
        return String.format("%,d", reservation.getReservationNumber());
      case RESERVATION_STATUS:
        return reservation.getStatus();
      case ROOM_TYPE:
        return reservation.getRoomType();
      case GUEST_NAME:
        final Guest guest = reservation.getGuest();
        return guest.getFirstName() + ' ' + guest.getLastName();
      case NUMBER_OF_NIGHTS:
        return String.format("%,d", reservation.getNumberOfNights());
      case TOTAL_COST:
        return String.format("$%,.2f", reservation.getTotalCostForTheStay());
      default:
        return null;
    }
  }

  /**
   * To be called whenever a reservation is marked as checked in.
   * @param index The index of the reservation that was checked in.
   */
  public void reservationCheckedIn(final int index) {
    this.fireTableCellUpdated(index, TableColumn.RESERVATION_STATUS.ordinal());
  }

  /**
   * To be called whenever a reservation is marked as checked out.
   * @param index The index of the reservation that was checked out.
   */
  public void reservationCheckedOut(final int index) {
    this.fireTableRowsDeleted(index, index);
  }

  /**
   * To be called when new reservations are added to the end of the list.
   */
  public void reservationAdded() {
    final int row = this.reservations.size() - 1;
    this.fireTableRowsInserted(row, row);
  }

  /**
   * The list of columns in the {@see ReservationsTableModel}.
   */
  private enum TableColumn {
    /**
     * The reservation number column.
     */
    RESERVATION_NUMBER,
    /**
     * The reservation status column.
     */
    RESERVATION_STATUS,
    /**
     * The reservation room type column.
     */
    ROOM_TYPE,
    /**
     * The reservation's guest's full name column.
     */
    GUEST_NAME,
    /**
     * The reservation's number of nights column.
     */
    NUMBER_OF_NIGHTS,
    /**
     * The reservation's total cost column.
     */
    TOTAL_COST;

    @Override
    public String toString() {
      switch (this) {
        case RESERVATION_NUMBER:
          return "Reservation #";
        case RESERVATION_STATUS:
          return "Status";
        case ROOM_TYPE:
          return "Room Type";
        case GUEST_NAME:
          return "Guest Name";
        case NUMBER_OF_NIGHTS:
          return "Number of Nights";
        case TOTAL_COST:
          return "Total Cost";
        default:
          return "Unknown";
      }
    }
  }
}
