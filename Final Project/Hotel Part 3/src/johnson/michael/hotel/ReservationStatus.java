package johnson.michael.hotel;

/**
 * An interface for allowing the manipulation of a reservation's status.
 */
public interface ReservationStatus {
  /**
   * Checks the guest in for the reservation.
   */
  void checkIn();

  /**
   * Checks the guest out for the reservation.
   */
  void checkOut();
}
