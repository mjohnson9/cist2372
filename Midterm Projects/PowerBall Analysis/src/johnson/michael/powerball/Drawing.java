package johnson.michael.powerball;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Drawing represents a single drawing in a PowerBall lottery.
 */
public class Drawing {
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

  private final int[] numbers;
  /**
   * The PowerBall number.
   */
  private final int powerBall;

  /**
   * Constructs a new Drawing object, representing one drawing.
   * @param numbers The 5 numbers that were drawn in the PowerBall.
   * @param powerBall The PowerBall number that was drawn.
   */
  public Drawing(final int[] numbers, final int powerBall) {
    // Validate numbers
    if (numbers.length != 5) {
      throw new IllegalArgumentException("numbers must be 5 elements in length");
    }
    for (final int number : numbers) {
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

  /**
   * Reads a list of Drawings from a file.
   * @param file The file to read from.
   * @return The drawings, in the order that they were in the file.
   * @throws FileNotFoundException If {@code file} wasn't found.
   * @throws InputMismatchException When the input file does not conform to the expected format.
   */
  public static Drawing[] readFromFile(final File file)
      throws FileNotFoundException, InputMismatchException {
    final List<Drawing> drawings = new ArrayList<>();

    final Scanner fileScanner = new Scanner(file);
    while (fileScanner.hasNext()) {
      final String line = fileScanner.nextLine();
      final Scanner lineReader = new Scanner(new StringReader(line));

      final int[] rolls = new int[5];
      for (int i = 0; i < rolls.length; i++) {
        rolls[i] = lineReader.nextInt();
      }

      final int powerBall = lineReader.nextInt();
      drawings.add(new Drawing(rolls, powerBall));
    }
    fileScanner.close();

    final Drawing[] rollsArray = new Drawing[drawings.size()];
    drawings.toArray(rollsArray);
    return rollsArray;
  }

  /**
   * Gets the result of a roll for a specific ball.
   * @param rollNum The roll number.
   * @return The ball number.
   */
  public int getNumber(final int rollNum) {
    return this.numbers[rollNum];
  }

  /**
   * @return The PowerBall number.
   */
  public int getPowerBall() {
    return this.powerBall;
  }
}
