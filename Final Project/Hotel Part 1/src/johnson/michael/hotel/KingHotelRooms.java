package johnson.michael.hotel;

/**
 * A KingHotelRooms is a {@see HotelRoom} with a capacity of 3.
 */
public class KingHotelRooms extends HotelRoom {
  /**
   * Constructs an instance of this class.
   */
  public KingHotelRooms() {
    super();

    this.setRoomType(RoomType.KING_HOTEL_ROOMS);
    this.setCapacity(3);
  }

  // toString is inherited from HotelRoom because the implementation would be the same

  // The default equals is correct for KingHotelRoom because two instances of HotelRoom could have
  // the same properties but be separate rooms
}
