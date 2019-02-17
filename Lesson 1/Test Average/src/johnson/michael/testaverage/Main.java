package johnson.michael.testaverage;

import javax.swing.JOptionPane;

public class Main {

  public static void main(String[] args) {
    final double[] testScores = promptTestScores(5);
    final String report = prepareReport(testScores);
    JOptionPane.showMessageDialog(null, report);
  }

  /**
   * prepareReport prepares a report to be displayed to the user
   *
   * @param testScores The test scores
   * @return A report that is ready to be displayed to the user
   */
  public static String prepareReport(final double[] testScores) {
    final StringBuilder report = new StringBuilder();

    final double averageScore = calcAverage(testScores);
    final char averageLetter = determineGrade(averageScore);
    report.append(String.format("Average score: %s (%.1f)\n", averageLetter, averageScore));

    for (int i = 0; i < testScores.length; i++) {
      if (i > 0) {
        report.append('\n');
      }

      final double testScore = testScores[i];
      final char letter = determineGrade(testScore);
      report.append(String.format("Test %d: %s (%.1f)", i + 1, letter, testScore));
    }

    return report.toString();
  }

  /**
   * calcAverage calculates the average of an array of test scores
   *
   * @param testScores The test scores to be used
   * @return The average of the given test scores
   */
  public static double calcAverage(final double[] testScores) {
    double sum = 0;
    for (double score : testScores) { // We use a foreach loop for its convenience
      sum += score;
    }
    return sum
        / ((double) testScores.length); // cast length to double to prevent floating point errors
  }

  /**
   * determineGrade determines the letter grade for a test score
   *
   * @param testScore The test score
   * @return The corresponding letter grade for the given test score
   */
  public static char determineGrade(final double testScore) {
    if (testScore < 60) {
      // 0-59
      return 'F';
    } else if (testScore < 70) {
      // 60-69
      return 'D';
    } else if (testScore < 80) {
      // 70-79
      return 'C';
    } else if (testScore < 90) {
      // 80-89
      return 'B';
    } else {
      // 90+
      return 'A';
    }
  }

  /**
   * promptTestScores prompts the user for a number of test scores and validates them
   *
   * @param numScores The number of scores to prompt for
   * @return An array of validated test scores of the specified length
   */
  public static double[] promptTestScores(final int numScores) {
    double[] testScores = new double[numScores];
    for (int i = 0; i < testScores.length; i++) {
      final String numberName = numberToOrderName(i + 1);
      while (true) { // Continue to loop until we have a reason to exit the loop
        final String answer = JOptionPane
            .showInputDialog(String.format("What was the %s test score?", numberName));
        if (answer == null) { // The user cancelled the dialog
          System.exit(0); // Exit the program
        }
        if (answer.trim().equals("")) {
          JOptionPane.showMessageDialog(null, "The test score cannot be blank.");
          continue; // Repeat the loop
        }
        try {
          // Attempt to parse the given input as an integer
          final double testScore = Double.parseDouble(answer);
          if (testScore < 0) {
            JOptionPane.showMessageDialog(null, "The test score cannot be negative.");
            continue; // Repeat the loop
          } else if (testScore > 100) {
            JOptionPane.showMessageDialog(null, "The test score cannot be greater than 100.");
            continue; // Repeat the loop
          }
          testScores[i] = testScore;
          break;
        } catch (NumberFormatException e) {
          // The given input was not an integer
          JOptionPane.showMessageDialog(null, "The given test score was not a valid number.");
          continue; // Repeat the loop
        }
      }
    }
    return testScores;
  }

  /**
   * numberToOrderName converts an integer into its word representation
   *
   * @param n The number to convert
   * @return The word representing the given number
   */
  public static String numberToOrderName(int n) {
    switch (n) {
      case 1:
        return "first";
      case 2:
        return "second";
      case 3:
        return "third";
      case 4:
        return "fourth";
      case 5:
        return "fifth";
      default:
        return n + "th";
    }
  }
}
