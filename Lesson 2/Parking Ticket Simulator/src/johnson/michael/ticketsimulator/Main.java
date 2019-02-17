package johnson.michael.ticketsimulator;

import java.util.concurrent.ThreadLocalRandom;

final class Main {

  private static final double MINIMUM_MINUTES_PARKED = 3d;
  private static final double MAXIMUM_MINUTES_PARKED = 6d * 60d;

  private static final double MINIMUM_MINUTES_BOUGHT = 15d;
  private static final double MAXIMUM_MINUTES_BOUGHT = 4d * 60d;

  /**
   * Main should never be instantiated
   */
  private Main() {
  }

  public static void main(final String[] args) {
    final ThreadLocalRandom random = ThreadLocalRandom.current();

    final PoliceOfficer officer = PoliceOfficer.generateRandom();
    final Street street = new Street("Sugarloaf Parkway", officer);

    for (int i = 0; i < 10; i++) {
      final ParkingMeter meter = new ParkingMeter();

      final boolean hasCar = random.nextBoolean();
      if (hasCar) {
        final ParkedCar car = ParkedCar.generateRandom();
        car.addParkedMinutes(random.nextDouble(MINIMUM_MINUTES_PARKED, MAXIMUM_MINUTES_PARKED));
        meter.setCar(car);
        meter.buyTime(random.nextDouble(MINIMUM_MINUTES_BOUGHT, MAXIMUM_MINUTES_BOUGHT));
      }

      street.addParkingMeter(meter);
    }

    officer.patrolStreet(street);
  }
}
