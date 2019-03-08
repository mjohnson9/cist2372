package johnson.michael.ticketsimulator;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * ParkedCar represents a car parked at a parking meter.
 */
public class ParkedCar {
  /**
   * List of makes and models to use in randomly generating a ParkedCar
   */
  private static final Map<String, List<String>> MODELS = Collections.unmodifiableMap(
      Map.of("Ford", List.of("F-150", "F-250", "Fusion", "Focus", "Taurus"), "Chevrolet",
          List.of("Impala", "Corvette", "Camaro", "Volt"), "Tesla",
          List.of("Model S", "Model X", "Model 3", "Roadster")));
  /**
   * List of colors to use in randomly generating a car
   */
  private static final List<String> COLORS =
      List.of("Gray", "Blue", "Red", "Purple", "White", "Black");
  /**
   * The make of the car
   */
  private String make;
  /**
   * The model of the car
   */
  private String model;
  /**
   * The color of the car
   */
  private String color;
  /**
   * The car's license plate
   */
  private String licensePlate;
  /**
   * How long the car has been parked at the meter in minutes
   */
  private double minutesParked;

  /**
   * @param make The new car's make
   * @param model The new car's model
   * @param color The new car's color
   * @param licensePlate The new car's license plate number
   */
  public ParkedCar(
      final String make, final String model, final String color, final String licensePlate) {
    this.make = make;
    this.model = model;
    this.color = color;
    this.licensePlate = licensePlate;
  }

  /**
   * ParkedCar does not offer a no-arg constructor
   */
  private ParkedCar() {}

  public static ParkedCar generateRandom() {
    final ThreadLocalRandom random = ThreadLocalRandom.current();

    final Object[] makes = MODELS.keySet().toArray();
    final String make = (String) makes[random.nextInt(makes.length)];

    final List<String> models = MODELS.get(make);
    final String model = models.get(random.nextInt(models.size()));

    final String color = COLORS.get(random.nextInt(COLORS.size()));

    final String licensePlate = generateLicensePlate();

    return new ParkedCar(make, model, color, licensePlate);
  }

  private static String generateLicensePlate() {
    final ThreadLocalRandom random = ThreadLocalRandom.current();

    final StringBuilder builder = new StringBuilder();
    for (int i = 0; i < 3; i++) {
      builder.append(generateUpperCaseLetter());
    }
    for (int i = 0; i < 4; i++) {
      builder.append(random.nextInt(10));
    }

    return builder.toString();
  }

  private static char generateUpperCaseLetter() {
    final ThreadLocalRandom random = ThreadLocalRandom.current();

    final char minimumLetter = 'A';
    final char maximumLetter = 'Z';

    return (char) random.nextInt((int) minimumLetter, (int) maximumLetter + 1);
  }

  /**
   * @return The make of the car
   */
  public String getMake() {
    return this.make;
  }

  /**
   * @return The model of the car
   */
  public String getModel() {
    return this.model;
  }

  /**
   * @return The color of the car
   */
  public String getColor() {
    return this.color;
  }

  /**
   * @return The car's license plate
   */
  public String getLicensePlate() {
    return this.licensePlate;
  }

  /**
   * @return The number of minutes that the car has been parked at the meter
   */
  public double getMinutesParked() {
    return this.minutesParked;
  }

  /**
   * Adds time to how long the car has been parked at the meter
   *
   * @param minutesParked The number of minutes to be added to the car's parked time
   */
  public void addParkedMinutes(final double minutesParked) {
    this.minutesParked += minutesParked;
  }
}
