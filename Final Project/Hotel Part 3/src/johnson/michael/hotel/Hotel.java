package johnson.michael.hotel;

import java.util.ArrayList;
import johnson.michael.hotel.exceptions.NoVacancyException;
import johnson.michael.hotel.exceptions.OverCapacityException;

/**
 * Represents a Hotel. Allows booking of rooms as well as retrieving the room list.
 */
public class Hotel implements BookHotelRoom {
  /**
   * The number of hotel room types.
   */
  private static final int NUM_ROOM_TYPES = 4;
  /**
   * The number of rooms per hotel room type generated in the no-arg constructor.
   */
  private static final int NUM_ROOMS_PER_TYPE = 5;
  /**
   * The average nightly price of a DoubleHotelRooms.
   */
  private static final double DOUBLE_HOTEL_ROOMS_PRICE = 95.50d;
  /**
   * The average nightly price of a DoubleSuiteHotelRooms.
   */
  private static final double DOUBLE_SUITE_HOTEL_ROOMS_PRICE = 105.36d;
  /**
   * The average nightly price of a KingHotelRooms.
   */
  private static final double KING_HOTEL_ROOMS_PRICE = 96.50d;
  /**
   * The average nightly price of a KingSuiteHotelRooms.
   */
  private static final double KING_SUITE_HOTEL_ROOMS_PRICE = 104.52d;

  /**
   * The list of hotel rooms.
   */
  private ArrayList<HotelRoom> hotelRooms;
  /**
   * The list of reservations.
   */
  private ArrayList<Reservation> reservations;

  /**
   * Constructs an instance of this class. It initializes the hotel room list with 5 rooms of each
   * type.
   */
  public Hotel() {
    // Initialize hotelRooms with a capacity equal to the number of rooms we're about to add
    this.setHotelRooms(new ArrayList<>(NUM_ROOM_TYPES * NUM_ROOMS_PER_TYPE));
    this.setReservations(new ArrayList<>());

    for (int i = 0; i < NUM_ROOMS_PER_TYPE; i++) {
      final DoubleHotelRooms room = new DoubleHotelRooms();
      room.setAverageNightlyPrice(DOUBLE_HOTEL_ROOMS_PRICE);
      this.hotelRooms.add(room);
    }

    for (int i = 0; i < NUM_ROOMS_PER_TYPE; i++) {
      final KingHotelRooms room = new KingHotelRooms();
      room.setAverageNightlyPrice(KING_HOTEL_ROOMS_PRICE);
      this.hotelRooms.add(room);
    }

    for (int i = 0; i < NUM_ROOMS_PER_TYPE; i++) {
      final DoubleSuiteHotelRooms room = new DoubleSuiteHotelRooms();
      room.setAverageNightlyPrice(DOUBLE_SUITE_HOTEL_ROOMS_PRICE);
      this.hotelRooms.add(room);
    }

    for (int i = 0; i < NUM_ROOMS_PER_TYPE; i++) {
      final KingSuiteHotelRooms room = new KingSuiteHotelRooms();
      room.setAverageNightlyPrice(KING_SUITE_HOTEL_ROOMS_PRICE);
      this.hotelRooms.add(room);
    }
  }

  /**
   * Retrieves the list of rooms.
   * @return The list of rooms.
   */
  public ArrayList<HotelRoom> getHotelRooms() {
    return this.hotelRooms;
  }

  /**
   * Sets the list of rooms.
   * @param hotelRooms The new list of rooms.
   */
  private void setHotelRooms(final ArrayList<HotelRoom> hotelRooms) {
    this.hotelRooms = hotelRooms;
  }

  /**
   * Retrieves the list of reservations.
   * @return The list of reservations.
   */
  public ArrayList<Reservation> getReservations() {
    return this.reservations;
  }

  /**
   * Sets the list of reservations.
   * @param reservations The new list of reservations.
   */
  private void setReservations(final ArrayList<Reservation> reservations) {
    this.reservations = reservations;
  }

  /**
   * Removes a reservation from the list.
   * @param reservation The reservation to be removed.
   */
  public void removeReservation(final Reservation reservation) {
    this.reservations.remove(reservation);
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof Hotel)) {
      // We're not comparing to another hotel; they'll never be equal
      return false;
    }

    final Hotel other = (Hotel) obj;

    // Two Hotels are equal if they reference the same room list
    return this.getHotelRooms() == other.getHotelRooms();
  }

  // In order to not violate the invariant that equal objects have equal hashCodes, we have to
  // implement hashCode to do the same as our equals method.
  @Override
  public int hashCode() {
    return this.getHotelRooms().hashCode();
  }

  @Override
  public String toString() {
    return "Hotel with " + this.hotelRooms.size() + " rooms\n"
        + (this.hasVacancy() ? "Not full" : "Full");
  }

  /**
   * Checks if the hotel has any vacant rooms.
   * @return true if the hotel has a vacant room, false otherwise.
   */
  public boolean hasVacancy() {
    for (final HotelRoom room : this.hotelRooms) {
      if (room.isVacant()) {
        return true;
      }
    }

    return false;
  }

  /**
   * Finds a room of the given type. The room may or may not be vacant.
   * @param roomType The type of room to find.
   * @return A room of the requested type.
   * @throws NoVacancyException If this Hotel has no rooms of the given type.
   */
  public HotelRoom findRoom(final RoomType roomType) throws NoVacancyException {
    return this.findRoom(roomType, false);
  }

  /**
   * Finds a room of a given type for check in.
   * @param roomType The type of room to find.
   * @return A vacant room of the given type.
   * @throws NoVacancyException If this Hotel has no vacant rooms of the given type.
   */
  public HotelRoom findRoomForCheckIn(final RoomType roomType) throws NoVacancyException {
    final HotelRoom room = this.findRoom(roomType, true);
    return room;
  }

  @Override
  public Reservation bookRoom(final RoomType roomType, final Guest guest, final int numberOfNights)
      throws OverCapacityException, NoVacancyException {
    final int partySize = guest.getNumberAdultsInParty() + guest.getNumberChildrenInParty();

    final HotelRoom room = this.findRoom(roomType, false);

    if (room.getCapacity() < partySize) {
      // Fail fast: all rooms of a given type have the same capacity. We can throw an
      // OverCapacityException here regardless of whether this particular room is booked or
      // occupied.
      throw new OverCapacityException(room.getCapacity(), partySize);
    }

    final Reservation reservation = new Reservation(this, guest, roomType, numberOfNights);
    this.reservations.add(reservation);
    return reservation;
  }

  /**
   * Finds a room of a given type, optionally vacant.
   * @param roomType The type of room to find.
   * @param mustBeVacant If true, only a vacant room will be returned. If false, any room of the
   *     given type will be returned.
   * @return A room of the requested type, optionally vacant.
   * @throws NoVacancyException If no room matching the parameters could be found.
   */
  private HotelRoom findRoom(final RoomType roomType, final boolean mustBeVacant)
      throws NoVacancyException {
    for (final HotelRoom room : this.hotelRooms) {
      if (room.getRoomType() != roomType) {
        // Ignore this room, it's not of the correct type.
        continue;
      }

      if (mustBeVacant && !room.isVacant()) {
        // Ignore this room; we have to find a vacant room and this one is occupied
        continue;
      }

      return room;
    }

    throw new NoVacancyException();
  }
}
