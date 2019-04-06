package johnson.michael.hotel;

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
   * The room assigned to the reservation.
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
   * @param room The {@see HotelRoom} that has been reserved.
   * @param numberOfNights The number of nights that the guest will be staying.
   */
  public Reservation(
      final Hotel hotel, final Guest guest, final HotelRoom room, final int numberOfNights) {
    this.reservationNumber = currentReservationNumber;
    currentReservationNumber += 1;

    this.setStatus(Status.INVALID);
    this.setGuest(guest);
    this.setHotel(hotel);
    this.setHotelRoom(room);
    this.setRoomType(room.getRoomType());
    this.setNumberOfNights(numberOfNights); // totalCostForTheStay will be calculated by this call
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
   * Retrieves the hotel room assigned to the reservation.
   * @return The hotel room assigned to the reservation.
   */
  public HotelRoom getHotelRoom() {
    return this.hotelRoom;
  }

  /**
   * Sets the reservation's hotel room.
   * @param hotelRoom The new hotel room for the reservation.
   */
  public void setHotelRoom(final HotelRoom hotelRoom) {
    this.hotelRoom = hotelRoom;
    // We need to recalculate the total cost because the average nightly price may have changed
    this.calculateTotalCost();
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

  private void calculateTotalCost() {
    if (this.hotelRoom == null) {
      // We can't calculate a total without a HotelRoom
      this.setTotalCostForTheStay(0.00d);
      return;
    }
    this.setTotalCostForTheStay(
        ((double) this.numberOfNights) * this.hotelRoom.getAverageNightlyPrice());
  }

  /**
   * Checks the guest in for the reservation. Sets the reservation status to VALID and marks the
   * room occupied.
   */
  @Override
  public void checkIn() {
    if (this.getStatus() == Status.VALID) {
      // The guest is already checked in
      return;
    }

    this.setStatus(Status.VALID);
    this.getHotelRoom().setVacant(false);
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
    room.setReserved(false);
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
