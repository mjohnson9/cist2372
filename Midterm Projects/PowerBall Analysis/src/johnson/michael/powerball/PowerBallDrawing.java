package johnson.michael.powerball;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Scanner;

public class PowerBallDrawing {
  /**
   * The minimum that an individual ball roll can be.
   */
  public static final int MIN_NUMBER = 1;

  /**
   * The maximum that an individual ball roll can be.
   */
  public static final int MAX_NUMBER = 69;

  /**
   * The minimum that a PowerBall number can be.
   */
  public static final int MIN_POWERBALL = 1;

  /**
   * The maximum that a PowerBall number can be.
   */
  public static final int MAX_POWERBALL = 42;

  /**
   * The five numbers that were rolled.
   */

  private int[] numbers;
  /**
   * The PowerBall number.
   */
  private int powerBall;

  public PowerBallDrawing(final int[] numbers, final int powerBall) {
    // Validate numbers
    if (numbers.length != 5) {
      throw new IllegalArgumentException("numbers must be 5 elements in length");
    }
    for (int i = 0; i < numbers.length; i++) {
      final int number = numbers[i];
      if (number < MIN_NUMBER || number > MAX_NUMBER) {
        throw new IllegalArgumentException(
            "elements of numbers must be in the range of [" + MIN_NUMBER + ", " + MAX_NUMBER + "]");
      }
    }

    // Validate powerBall
    if (powerBall < MIN_POWERBALL || powerBall > MAX_POWERBALL) {
      throw new IllegalArgumentException(
          "powerBall must be in the range of [" + MIN_POWERBALL + ", " + MAX_POWERBALL + "]");
    }

    this.powerBall = powerBall;
    this.numbers = numbers.clone(); // Clone numbers so that we own our own array just for this
                                    // class and not a reference back to the caller's array
  }

  public static PowerBallDrawing[] readFromFile(File file) throws FileNotFoundException {
    ArrayList<PowerBallDrawing> powerBallDrawings = new ArrayList<>();

    final Scanner fileScanner = new Scanner(file);
    while (fileScanner.hasNext()) {
      final String line = fileScanner.nextLine();
      final Scanner lineReader = new Scanner(new StringReader(line));

      int[] rolls = new int[5];
      for (int i = 0; i < rolls.length; i++) {
        rolls[i] = lineReader.nextInt();
      }

      int powerBall = lineReader.nextInt();
      powerBallDrawings.add(new PowerBallDrawing(rolls, powerBall));
    }

    PowerBallDrawing[] rollsArray = new PowerBallDrawing[powerBallDrawings.size()];
    powerBallDrawings.toArray(rollsArray);
    return rollsArray;
  }

  public int getNumber(final int rollNum) {
    return this.numbers[rollNum];
  }

  public int getPowerBall() {
    return this.powerBall;
  }
}
