package johnson.michael.hotel;

import johnson.michael.hotel.exceptions.NoVacancyException;

/**
 * Represents a reservation for a hotel room.
 */
public class Reservation implements ReservationStatus {
  /**
   * The current reservation number. It's assigned and then incremented by 1 for every reservation,
   * giving each reservation a unique number.
   */
  private static int currentReservationNumber = 1;

  /**
   * The reservation's unique number.
   */
  private final int reservationNumber;
  /**
   * The status of the reservation.
   */
  private Status status;
  /**
   * The total cost of the reservation.
   */
  private double totalCostForTheStay;
  /**
   * The type of room booked with the reservation.
   */
  private RoomType roomType;
  /**
   * The guest who placed this reservation.
   */
  private Guest guest;
  /**
   * The currently occupied hotel room. Only set after check in.
   */
  private HotelRoom hotelRoom;
  /**
   * The hotel that the reservation is booked at.
   */
  private Hotel hotel;
  /**
   * The number of nights that the guest will be staying.
   */
  private int numberOfNights;

  /**
   * Constructs a new instance of this class.
   * @param hotel The {@see Hotel} that the reservation is at.
   * @param guest The {@see Guest} that the reservation is for.
   * @param roomType The {@see RoomType} that was booked.
   * @param numberOfNights The number of nights that the guest will be staying.
   */
  public Reservation(
      final Hotel hotel, final Guest guest, final RoomType roomType, final int numberOfNights) {
    this.reservationNumber = currentReservationNumber;
    currentReservationNumber += 1;

    this.setStatus(Status.INVALID);
    this.setGuest(guest);
    this.setHotel(hotel);
    this.setHotelRoom(null);
    this.setRoomType(roomType);
    this.setNumberOfNights(numberOfNights); // totalCostForTheStay will be calculated by this call
  }

  /**
   * Retrieves the reservation counter that provides unique reservation numbers.
   * @return The current value of the reservation counter.
   */
  public static int getStaticReservationNumber() {
    return currentReservationNumber;
  }

  /**
   * Sets the reservation counter that provides unique reservation numbers.
   * @param reservationNumber The value to set the reservation number to.
   */
  public static void setStaticReservationNumber(final int reservationNumber) {
    currentReservationNumber = reservationNumber;
  }

  /**
   * Retrieves the reservation's unique number.
   * @return The unique number for this reservation.
   */
  public int getReservationNumber() {
    return this.reservationNumber;
  }

  /**
   * Retrieves the reservation's status.
   * @return The reservation's status.
   */
  public Status getStatus() {
    return this.status;
  }

  /**
   * Sets the reservation's status.
   * @param status The new status for the reservation.
   */
  public void setStatus(final Status status) {
    this.status = status;
  }

  /**
   * Retrieves the total cost of the reservation.
   * @return The total cost of the reservation in dollars.
   */
  public double getTotalCostForTheStay() {
    return this.totalCostForTheStay;
  }

  /**
   * Sets the total cost of the reservation.
   * @param totalCostForTheStay The new total cost of the reservation. The value should be in
   *     dollars.
   */
  public void setTotalCostForTheStay(final double totalCostForTheStay) {
    this.totalCostForTheStay = totalCostForTheStay;
  }

  /**
   * Retrieves the reservation's room type.
   * @return The reservation's room type.
   */
  public RoomType getRoomType() {
    return this.roomType;
  }

  /**
   * Sets the reservation's room type.
   * @param roomType The new room type for the reservation.
   */
  public void setRoomType(final RoomType roomType) {
    this.roomType = roomType;
  }

  /**
   * Retrieves the reservation's registered guest.
   * @return The reservation's registered guest.
   */
  public Guest getGuest() {
    return this.guest;
  }

  /**
   * Sets the reservation's registered guest.
   * @param guest The new registered guest for the reservation.
   */
  public void setGuest(final Guest guest) {
    this.guest = guest;
  }

  /**
   * Retrieves the hotel room occupied by the reservation. If the reservation hasn't checked in yet,
   * this is {@code null}.
   * @return The hotel room occupied by the reservation or {@code null} if the reservation hasn't
   *     checked in yet.
   */
  public HotelRoom getHotelRoom() {
    return this.hotelRoom;
  }

  /**
   * Sets the hotel room occupied by the reservation. This should only be set once the reservation
   * is checked in.
   * @param hotelRoom The new {@see HotelRoom} for the reservation.
   */
  public void setHotelRoom(final HotelRoom hotelRoom) {
    this.hotelRoom = hotelRoom;
  }

  /**
   * Retrieves the hotel that the reservation has been placed at.
   * @return The reservation's host hotel.
   */
  public Hotel getHotel() {
    return this.hotel;
  }

  /**
   * Sets the hotel that the reservation is placed at.
   * @param hotel The new hotel for the reservation.
   */
  public void setHotel(final Hotel hotel) {
    this.hotel = hotel;
  }

  /**
   * Retrieves the number of nights for the reservation.
   * @return The number of nights that the reservation will last.
   */
  public int getNumberOfNights() {
    return this.numberOfNights;
  }

  /**
   * Sets the number of nights for the reservation.
   * @param numberOfNights The new number of nights for the reservation.
   */
  public void setNumberOfNights(final int numberOfNights) {
    this.numberOfNights = numberOfNights;
    // We need to recalculate the total cost because the number of nights has changed
    this.calculateTotalCost();
  }

  /**
   * Recalculates {@code totalCostForTheStay} based on {@code hotelRoom.getAverageNightlyPrice()}
   * and {@code numberOfNights}.
   */
  private void calculateTotalCost() {
    try {
      HotelRoom hotelRoom = this.getHotelRoom();
      if (hotelRoom == null) {
        hotelRoom = this.getHotel().findRoom(this.getRoomType());
      }

      this.setTotalCostForTheStay(
          ((double) this.getNumberOfNights()) * hotelRoom.getAverageNightlyPrice());
    } catch (NoVacancyException e) {
      // The hotel has no rooms of the type we reserved. This should never happen because a Hotel
      // would have to remove rooms.
      this.setTotalCostForTheStay(0.00d);
    }
  }

  /**
   * Checks the guest in for the reservation. Sets the reservation status to VALID and marks the
   * room occupied.
   */
  @Override
  public void checkIn() throws NoVacancyException {
    if (this.getStatus() == Status.VALID) {
      // The guest is already checked in
      return;
    }

    final HotelRoom room = this.getHotel().findRoomForCheckIn(this.getRoomType());
    this.setHotelRoom(room);

    room.setVacant(false);
    this.setStatus(Status.VALID);
  }

  /**
   * Checks the guest out for the reservation. Sets the reservation status to INVALID and marks the
   * room vacant.
   */
  @Override
  public void checkOut() {
    if (this.getStatus() == Status.INVALID) {
      // The guest is not checked in
      return;
    }

    this.setStatus(Status.INVALID);

    final HotelRoom room = this.getHotelRoom();
    room.setVacant(true);
    this.setHotelRoom(null);
    this.getHotel().removeReservation(this);
  }

  @Override
  public String toString() {
    return "Reservation #" + this.getReservationNumber();
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof Reservation)) {
      // We're comparing to a different class: the objects can never be equal
      return false;
    }

    final Reservation other = (Reservation) obj;

    // Two reservations are the same if they have the same reservation number
    return this.getReservationNumber() == other.getReservationNumber();
  }

  // To complete the invariant that equal objects have equal hashCodes, we have to implement
  // hashCode
  @Override
  public int hashCode() {
    return this.getReservationNumber();
  }
}
