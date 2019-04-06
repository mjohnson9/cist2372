package johnson.michael.hotel;

/**
 * Represents the types of rooms that can be created. There is one for each room class.
 */
public enum RoomType {
  /**
   * Corresponds to the {@see DoubleHotelRooms} class.
   */
  DOUBLE_HOTEL_ROOMS,
  /**
   * Corresponds to the {@see DoubleSuiteHotelRooms} class.
   */
  DOUBLE_SUITE_HOTEL_ROOMS,
  /**
   * Corresponds to the {@see KingHotelRooms} class.
   */
  KING_HOTEL_ROOMS,
  /**
   * Corresponds to the {@see KingSuiteHotelRooms} class.
   */
  KING_SUITE_HOTEL_ROOMS;

  @Override
  public String toString() {
    switch (this) {
      case DOUBLE_HOTEL_ROOMS:
        return "Double";
      case DOUBLE_SUITE_HOTEL_ROOMS:
        return "Double Suite";
      case KING_HOTEL_ROOMS:
        return "King";
      case KING_SUITE_HOTEL_ROOMS:
        return "King Suite";
      default:
        return "Unknown";
    }
  }

  // equals not implemented because default enum equals behavior is correct
}
