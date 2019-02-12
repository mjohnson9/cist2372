package johnson.michael.carpetcalculator;

/**
 * RoomDimension represents the dimensions of a room.
 */
public class RoomDimension {

  /**
   * The length of the room in feet
   */
  private double length;

  /**
   * The width of the room in feet
   */
  private double width;

  /**
   * @param width The width of the new RoomDimension
   * @param length The length of the new RoomDimension
   */
  public RoomDimension(final double width, final double length) {
    this.setWidth(width);
    this.setLength(length);
  }

  private RoomDimension() {
  } // Hide the no-arg constructor


  /**
   * @return The length of the room in feet.
   */
  public double getLength() {
    return this.length;
  }

  /**
   * Sets the length of the room
   *
   * @param length The new length of the room in feet
   */
  public void setLength(double length) {
    this.length = length;
  }

  /**
   * @return The width of the room in feet.
   */
  public double getWidth() {
    return this.width;
  }

  /**
   * Sets the width of the room
   *
   * @param width The new width of the room in feet
   */
  public void setWidth(double width) {
    this.width = width;
  }

  /**
   * @return The area of the room in square feet
   */
  public double getArea() {
    return this.length * this.width;
  }

  @Override
  public String toString() {
    return "Room[" + this.getLength() + ", " + this.getWidth() + "]";
  }
}
