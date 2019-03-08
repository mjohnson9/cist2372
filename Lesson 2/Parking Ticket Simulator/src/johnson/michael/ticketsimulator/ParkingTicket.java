package johnson.michael.ticketsimulator;

public class ParkingTicket {
  private ParkedCar car;
  private ParkingMeter meter;
  private PoliceOfficer officer;

  public ParkingTicket(final ParkedCar car, final ParkingMeter meter, final PoliceOfficer officer) {
    this.car = car;
    this.meter = meter;
    this.officer = officer;
  }

  /**
   * ParkingTicket does not have a no-arg constructor
   */
  private ParkingTicket() {}

  public ParkedCar getCar() {
    return this.car;
  }

  public ParkingMeter getMeter() {
    return this.meter;
  }

  public PoliceOfficer getOfficer() {
    return this.officer;
  }

  public double getFine() {
    final double timeOver = this.car.getMinutesParked() - this.meter.getTimePurchased();
    if (timeOver <= 0) {
      return 0d;
    }

    final double timeOverHours = timeOver / 60d;

    return 25d + (Math.ceil(Math.max(timeOverHours - 1, 0)) * 10d);
  }
}
