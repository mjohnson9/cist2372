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
   * @throws OverCapacityException When the party is too large for the room type.
   * @throws NoVacancyException When the hotel has no rooms of the given {@code roomType}.
   */
  Reservation bookRoom(RoomType roomType, Guest guest, int numberOfNights)
      throws OverCapacityException, NoVacancyException;
}
