package johnson.michael.ticketsimulator;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * PoliceOfficer represents a traffic enforcement officer. It is immutable.
 */
public class PoliceOfficer {
  /**
   * First names for generating random officers
   */
  private static final List<String> FIRST_NAMES = List.of("Jaclyn", "Carmen", "Sarah", "Carl",
      "Joshua", "Angie", "Paul", "Richard", "Horace", "Pauline");
  /**
   * Last names for generating random officers
   */
  private static final List<String> LAST_NAMES = List.of("Scott", "Simmons", "Adams", "Maher",
      "Roque", "Hodapp", "Lucius", "Barks", "Cooper", "Libbey");
  /**
   * The minimum badge number for a random officer
   */
  private static final int MINIMUM_BADGE_NUMBER = 1000;
  /**
   * The maximum badge number for a random officer
   */
  private static final int MAXIMUM_BADGE_NUMBER = 9999;
  /**
   * This officer's badge number
   */
  private int badgeNumber;
  /**
   * This officer's first name
   */
  private String firstName;
  /**
   * This officer's last name
   */
  private String lastName;

  /**
   * @param badgeNumber The new officer's badge number
   * @param firstName The new officer's first name
   * @param lastName The new officer's last name
   */
  public PoliceOfficer(final int badgeNumber, final String firstName, final String lastName) {
    this.badgeNumber = badgeNumber;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  private PoliceOfficer() {} // Hide the no-arg constructor

  /**
   * Generates a random PoliceOfficer
   *
   * @return A randomly generated PoliceOfficer
   */
  public static PoliceOfficer generateRandom() {
    final ThreadLocalRandom random = ThreadLocalRandom.current();

    final int badgeNumber = random.nextInt(MINIMUM_BADGE_NUMBER, MAXIMUM_BADGE_NUMBER + 1);
    final String firstName = FIRST_NAMES.get(random.nextInt(FIRST_NAMES.size()));
    final String lastName = LAST_NAMES.get(random.nextInt(LAST_NAMES.size()));
    return new PoliceOfficer(badgeNumber, firstName, lastName);
  }

  public void patrolStreet(final Street street) {
    final List<ParkingMeter> meters = street.getParkingMeters();
    for (int i = 0; i < meters.size(); i++) {
      this.assessMeter(i + 1, meters.get(i));
    }
  }

  public ParkingTicket assessMeter(final int meterNumber, final ParkingMeter meter) {
    if (meter == null) {
      // We were given a null meter
      throw new IllegalArgumentException("meter");
    }

    System.out.println(String.format("%s assesses meter #%,d:", this, meterNumber));

    final ParkedCar car = meter.getCar();
    if (car == null) {
      // There's no car parked at this meter
      System.out.println("\tThere is no car parked at this meter.");
      return null;
    }

    if (car.getMinutesParked() <= (meter.getTimePurchased() + 1)) {
      System.out.println(String.format("\tThe %s %s %s has %,.0f minutes remaining on their meter.",
          car.getColor(), car.getMake(), car.getModel(),
          meter.getTimePurchased() - car.getMinutesParked()));
      return null;
    }

    final ParkingTicket ticket = new ParkingTicket(car, meter, this);
    System.out.println(String.format(
        "\tThe %s %s %s (plate %s) is issued a ticket for $%,.2f by %s for being %.0f minutes over.",
        car.getColor(), car.getMake(), car.getModel(), car.getLicensePlate(), ticket.getFine(),
        this, car.getMinutesParked() - meter.getTimePurchased()));

    return ticket;
  }

  /**
   * @return The officer's badge number
   */
  public int getBadgeNumber() {
    return this.badgeNumber;
  }

  /**
   * @return The officer's first name
   */
  public String getFirstName() {
    return this.firstName;
  }

  /**
   * @return The officer's last name
   */
  public String getLastName() {
    return this.lastName;
  }

  @Override
  public String toString() {
    return "Officer " + this.getLastName() + " (#" + this.getBadgeNumber() + ")";
  }
}
