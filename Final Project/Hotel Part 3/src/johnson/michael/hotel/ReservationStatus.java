package johnson.michael.hotel;

import johnson.michael.hotel.exceptions.NoVacancyException;

/**
 * An interface for allowing the manipulation of a reservation's status.
 */
public interface ReservationStatus {
  /**
   * Checks the guest in for the reservation.
   * @throws NoVacancyException If the hotel has no vacant rooms of the reserved type.
   */
  void checkIn() throws NoVacancyException;

  /**
   * Checks the guest out for the reservation.
   */
  void checkOut();
}
