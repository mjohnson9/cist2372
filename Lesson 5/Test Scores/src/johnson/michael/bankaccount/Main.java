package johnson.michael.bankaccount;

import javax.swing.JOptionPane;
import johnson.michael.bankaccount.exceptions.InvalidTestScore;

public final class Main {
  // Don't allow instances of Main to be created
  private Main() {}

  public static void main(final String[] args) {
    final double[] scoresArray = UI.promptTestScores();
    if (scoresArray.length == 0) {
      // The user cancelled before entering any scores
      return;
    }

    final TestScores scores = buildTestScores(scoresArray);
    if (scores == null) {
      // All of the scores were invalid
      UI.displayAllDiscardedMessage();
      return;
    }

    UI.displayTestScores(scores);
  }

  /**
   * Builds a {@link TestScores} from an array of test scores. It discards the invalid scores and
   * displays a message to the user describing which scores were discarded.
   */
  private static TestScores buildTestScores(double[] scoresArray) {
    TestScores scores;
    // We have to keep track of how many scores we've discarded so that we can display the correct
    // score number to the user
    int discardedScores = 0;
    final StringBuilder discardedMessage = new StringBuilder();
    while (true) {
      try {
        // Attempt to construct TestScores
        scores = new TestScores(scoresArray);
        break; // break out of the loop if we were successful
      } catch (final InvalidTestScore e) {
        // There was an invalid score
        final int index = e.getIndex();
        scoresArray = removeElement(scoresArray, index); // Remove the invalid score from the array

        // Add to the message to the user about which scores were discarded
        if (discardedMessage.length() > 0) {
          discardedMessage.append('\n');
        }
        discardedMessage.append(String.format("Discarded score #%d (%.1f) because it was invalid.",
            (index + 1 + discardedScores), e.getScore()));
        discardedScores++;

        if (scoresArray.length == 0) {
          // All of the scores in the array were invalid, return null
          return null;
        }
      }
    }

    if (discardedMessage.length() > 0) {
      // Some scores were discarded, display the message to the user
      JOptionPane.showMessageDialog(null, discardedMessage);
    }

    return scores;
  }

  /**
   * Removes an element from an array
   *
   * @param array The array to operate on
   * @param index The index to be removed
   * @return A new array with the index removed
   */
  private static double[] removeElement(final double[] array, final int index) {
    final double[] newArray = new double[array.length - 1];
    // Copy the old array from 0 to index (exclusive)
    System.arraycopy(array, 0, newArray, 0, index);
    // Copy the old array from index + 1 to the end
    System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
    return newArray;
  }
}
