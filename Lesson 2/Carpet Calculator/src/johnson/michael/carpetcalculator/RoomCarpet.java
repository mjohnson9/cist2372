package johnson.michael.carpetcalculator;

public class RoomCarpet {
  /**
   * The dimensions of the room
   */
  private RoomDimension dimensions;

  /**
   * The cost of the carpet per square foot
   */
  private double cost;

  /**
   * @param dimensions The dimensions of the room to be carpeted
   * @param cost The cost per square foot of the carpet
   */
  public RoomCarpet(RoomDimension dimensions, double cost) {
    this.setDimensions(dimensions);
    this.setCost(cost);
  }

  private RoomCarpet() {} // Hide the no-arg constructor

  /**
   * @return The dimensions of the room
   */
  public RoomDimension getDimensions() {
    return this.dimensions;
  }

  /**
   * Sets the dimensions of the room
   *
   * @param dimensions The new dimensions of the room
   */
  public void setDimensions(RoomDimension dimensions) {
    this.dimensions = dimensions;
  }

  /**
   * @return The cost per square foot of the carpet
   */
  public double getCost() {
    return this.cost;
  }

  /**
   * Sets the cost of the carpet
   *
   * @param cost The cost per square foot of the carpet
   */
  public void setCost(double cost) {
    this.cost = cost;
  }

  /**
   * @return The total cost of the carpet for a room with the given dimensions and cost per square
   * foot
   */
  public double getTotalCost() {
    return this.getCost() * this.getDimensions().getArea();
  }

  @Override
  public String toString() {
    return "Carpet[" + this.getDimensions() + ", $" + String.format("%.2f", getTotalCost()) + "]";
  }
}
