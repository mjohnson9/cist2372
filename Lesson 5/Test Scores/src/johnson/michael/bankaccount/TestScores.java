package johnson.michael.bankaccount;

import johnson.michael.bankaccount.exceptions.InvalidTestScore;

/**
 * TestScores is a container for a list of test scores. It holds an arbitrary length array of test
 * scores and provides utility functions for those scores.
 */
public class TestScores {

  /**
   * The minimum score (inclusive) that can be in the scores array
   */
  private static final double MIN_SCORE = 0;
  /**
   * The maximum score (inclusive) that can be in the scores array
   */
  private static final double MAX_SCORE = 100;

  /**
   * The list of test scores. All scores are between 0 and 100 (inclusive).
   */
  private final double[] scores;


  /**
   * Constructs a new TestScores with the given scores as its values
   *
   * @param scores The scores to use
   * @throws InvalidTestScore When a given score in the scores array is not within valid bounds
   */
  public TestScores(final double[] scores) throws InvalidTestScore {
    if (scores.length == 0) {
      throw new IllegalArgumentException("scores cannot be empty");
    }

    for (int i = 0; i < scores.length; i++) {
      final double score = scores[i];
      if ((score < MIN_SCORE) || (score > MAX_SCORE)) {
        // This score is invalid
        throw new InvalidTestScore(i, score);
      }
    }

    this.scores = scores.clone(); // Clone scores so that we don't have a mutable reference
  }

  /**
   * Averages the stored test scores
   *
   * @return The average of the test scores
   */
  public double average() {
    double sum = 0;
    for (final double score : this.scores) {
      sum += score;
    }

    return sum / ((double) this.scores.length);
  }

  /**
   * @return The test scores
   */
  public double[] getScores() {
    return this.scores.clone(); // Return a clone so that our internal version cannot be mutated
  }
}
