package johnson.michael.ticketsimulator;

public class ParkingMeter {
  /**
   * The time purchased in minutes
   */
  private double timePurchased;

  /**
   * The currently parked car
   */
  private ParkedCar car;

  public ParkingMeter() {
    this.timePurchased = 0d;
    this.car = null;
  }

  /**
   * The time purchased in minutes
   */
  public double getTimePurchased() {
    return this.timePurchased;
  }

  /**
   * Buys more time at the meter
   *
   * @param minutes The number of minutes purchased
   */
  public void buyTime(final double minutes) {
    this.timePurchased += minutes;
  }

  /**
   * @return The currently parked car
   */
  public ParkedCar getCar() {
    return this.car;
  }

  /**
   * Sets the currently parked car
   *
   * @param car The new car to be parked at this meter
   */
  public void setCar(final ParkedCar car) {
    this.car = car;
    this.timePurchased = 0d;
  }
}
