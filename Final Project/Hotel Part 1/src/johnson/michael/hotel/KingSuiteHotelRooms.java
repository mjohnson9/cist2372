package johnson.michael.hotel;

/**
 * A KingSuiteHotelRooms is a {@see KingHotelRooms} with a micro fridge.
 */
public class KingSuiteHotelRooms extends KingHotelRooms {
  /**
   * Constructs an instance of this class.
   */
  public KingSuiteHotelRooms() {
    super();

    this.setRoomType(RoomType.KING_SUITE_HOTEL_ROOMS);
    this.setMicroFridge(true);
  }

  // toString is inherited from HotelRoom because the implementation would be the same

  // The default equals is correct for KingSuiteHotelRoom because two instances of HotelRoom could
  // have the same properties but be separate rooms
}
