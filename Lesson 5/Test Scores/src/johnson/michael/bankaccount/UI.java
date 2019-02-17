package johnson.michael.bankaccount;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public final class UI {

  // This is a utility class with only static methods; don't allow instances to be created
  private UI() {
  }

  /**
   * Displays information about a {@link TestScores} object to the user
   *
   * @param scores The object whose information should be displayed
   */
  public static void displayTestScores(final TestScores scores) {
    final StringBuilder message = new StringBuilder();

    message.append("The scores you entered were: ");

    int line = 1;
    final double[] scoresArray = scores.getScores();
    for (int i = 0; i < scoresArray.length; i++) {
      final double score = scoresArray[i];

      if (i > 0) {
        // Append a comma before each score after the first one
        message.append(", ");
      }

      // Don't allow lines to get too long
      final int maxLength = line * 60;
      if (message.length() > maxLength) {
        // The message is too long; start on a new line
        message.append("\n");
        line++;
      }

      if (i == (scoresArray.length - 1)) {
        // Append "and" before the last score
        message.append("and ");
      }

      // Append the test score
      message.append(String.format("%.1f", score));
    }
    message.append('\n'); // Append a final newline after the scores

    message.append(String.format("The average score is %.1f.", scores.average()));

    JOptionPane.showMessageDialog(null, message);
  }

  /**
   * Displays a message to the user that all of their scores were discarded
   */
  public static void displayAllDiscardedMessage() {
    JOptionPane.showMessageDialog(null, "All scores were discarded because they were invalid.");
  }

  /**
   * Prompts the user for a list of test scores
   *
   * @return The test scores provided by the user
   */
  public static double[] promptTestScores() {
    final ArrayList<Double> scores = new ArrayList<>();

    while (true) {
      final Double score = promptTestScore();
      if (score == null) {
        break;
      }

      scores.add(score);
    }

    // Convert scores to an array
    final double[] scoresArray = new double[scores.size()];
    for (int i = 0; i < scores.size(); i++) {
      scoresArray[i] = scores.get(i);
    }

    return scoresArray;
  }

  /**
   * Prompts the user for a single test score
   *
   * @return The user-provided test score or null if the user would like to stop entering test
   * scores
   */
  private static Double promptTestScore() {
    Double result = null;

    do {
      String response = JOptionPane.showInputDialog(
          "Please enter a test score to be added to the list:\n(Press cancel to stop adding test scores)");
      if (response == null) {
        // User pressed cancel
        return null;
      }

      // Trim any leading or trailing whitespace
      response = response.trim();

      if (response.isEmpty()) {
        // The user's response was empty; just prompt again
        continue;
      }

      try {
        result = Double.parseDouble(response);
      } catch (final NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "\"" + response
            + "\" is not a valid test score. Test scores must be a number, optionally with a decimal point.");
      }
    } while (result == null);

    return result;
  }
}
