package johnson.michael.hotel;

/**
 * Base class for any hotel room classes.
 */
public abstract class HotelRoom {
  /**
   * The capacity of the hotel room.
   */
  private int capacity = 0;
  /**
   * The room's type.
   */
  private RoomType roomType = null;
  /**
   * Whether or not the hotel room has a micro fridge.
   */
  private boolean microFridge = false;
  /**
   * The average nightly price of the hotel room.
   */
  private double averageNightlyPrice = 0.00d;
  /**
   * Whether or not the hotel room is vacant.
   */
  private boolean vacant = true;

  /**
   * Whether or not the hotel room is reserved.
   */
  private boolean reserved = false;

  /**
   * Retrieves the capacity of the hotel room.
   * @return The capacity of the hotel room.
   */
  public int getCapacity() {
    return this.capacity;
  }

  /**
   * Sets the capacity of the hotel room.
   * @param capacity The new capacity of the hotel room.
   */
  public void setCapacity(final int capacity) {
    this.capacity = capacity;
  }

  /**
   * Retrieves the hotel room's type.
   * @return The hotel room's type.
   */
  public RoomType getRoomType() {
    return this.roomType;
  }

  /**
   * Sets the hotel room's type.
   * @param roomType The new hotel room type.
   */
  public void setRoomType(final RoomType roomType) {
    this.roomType = roomType;
  }

  /**
   * Checks if the hotel room has a micro fridge.
   * @return True if the hotel room has a micro fridge, false otherwise.
   */
  public boolean hasMicroFridge() {
    return this.microFridge;
  }

  /**
   * Sets whether or not the hotel room has a micro fridge.
   * @param microFridge The new micro fridge value. True if the hotel room has a micro fridge, false
   *     otherwise.
   */
  public void setMicroFridge(final boolean microFridge) {
    this.microFridge = microFridge;
  }

  /**
   * Retrieves the hotel room's average nightly price.
   * @return The hotel room's average nightly price.
   */
  public double getAverageNightlyPrice() {
    return this.averageNightlyPrice;
  }

  /**
   * Sets the hotel room's average nightly price.
   * @param averageNightlyPrice The new average nightly price of the hotel room.
   */
  public void setAverageNightlyPrice(final double averageNightlyPrice) {
    this.averageNightlyPrice = averageNightlyPrice;
  }

  /**
   * Checks if the hotel room is vacant.
   * @return True if the hotel room is vacant, false otherwise.
   */
  public boolean isVacant() {
    return this.vacant;
  }

  /**
   * Sets the hotel room's vacancy status.
   * @param vacant The new vacancy status. True if the hotel room is vacant, false otherwise.
   */
  public void setVacant(final boolean vacant) {
    this.vacant = vacant;
  }

  /**
   * Checks if the hotel room has been reserved.
   * @return True if the hotel room is reserved, false otherwise.
   */
  public boolean isReserved() {
    return this.reserved;
  }

  /**
   * Sets the hotel room's reserved status.
   * @param reserved The new reserved status. True if the hotel room is reserved, false otherwise.
   */
  public void setReserved(final boolean reserved) {
    this.reserved = reserved;
  }

  @Override
  public String toString() {
    return "Hotel Room\nCapacity: " + this.getCapacity() + "\nRoom type: " + this.getRoomType()
        + "\n" + (this.hasMicroFridge() ? "Has a micro fridge" : "Does not have a micro fridge")
        + "\nAverage nightly price: " + String.format("%.2f", this.getAverageNightlyPrice()) + "\n"
        + (this.isVacant() ? "Vacant" : "Occupied");
  }

  // The default equals is correct for HotelRoom because two instances of HotelRoom could have the
  // same properties but be separate rooms
}
