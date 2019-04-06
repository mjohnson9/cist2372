package johnson.michael.hotel;

import johnson.michael.hotel.exceptions.NoVacancyException;
import johnson.michael.hotel.exceptions.OverCapacityException;

/**
 * An interface that defines how hotel rooms are to be booked.
 */
public interface BookHotelRoom {
  /**
   * Attempts to book a room of the given {@see RoomType}.
   *
   * @param roomType The type of room to book.
   * @param guest The guest who is reserving the room.
   * @param numberOfNights The number of nights that the room will be reserved for.
   *
   * @return A {@see Reservation} for the booked room.
   *
   * @throws NoVacancyException When there are no rooms that can be booked.
   * @throws OverCapacityException When the party is too large for the room type.
   */
  Reservation bookRoom(RoomType roomType, Guest guest, int numberOfNights)
      throws NoVacancyException, OverCapacityException;
}
