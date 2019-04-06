package johnson.michael.hotel;

/**
 * A DoubleSuiteHotelRooms is a {@see DoubleHotelRooms} with a micro fridge.
 */
@SuppressWarnings("ClassTooDeepInInheritanceTree")
public class DoubleSuiteHotelRooms extends DoubleHotelRooms {
  /**
   * Constructs an instance of this class.
   */
  public DoubleSuiteHotelRooms() {
    super();

    this.setRoomType(RoomType.DOUBLE_SUITE_HOTEL_ROOMS);
    this.setMicroFridge(true);
  }

  // toString is inherited from HotelRoom because the implementation would be the same

  // The default equals is correct for DoubleSuiteHotelRoom because two instances of HotelRoom could
  // have the same properties but be separate rooms
}
