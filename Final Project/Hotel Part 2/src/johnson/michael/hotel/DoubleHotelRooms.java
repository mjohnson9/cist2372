package johnson.michael.hotel;

/**
 * A DoubleHotelRooms is a {@see HotelRoom} with a capacity of 4.
 */
public class DoubleHotelRooms extends HotelRoom {
  /**
   * Constructs an instance of this class.
   */
  public DoubleHotelRooms() {
    super();

    this.setRoomType(RoomType.DOUBLE_HOTEL_ROOMS);
    this.setCapacity(4);
  }

  // toString is inherited from HotelRoom because the implementation would be the same

  // The default equals is correct for DoubleHotelRoom because two instances of HotelRoom could have
  // the same properties but be separate rooms
}
