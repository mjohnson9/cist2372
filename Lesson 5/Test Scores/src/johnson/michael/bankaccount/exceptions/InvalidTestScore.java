package johnson.michael.bankaccount.exceptions;

public class InvalidTestScore extends IllegalArgumentException {

  /**
   * The index of the invalid test score
   */
  private final Integer index;
  /**
   * The value of the invalid test score
   */
  private final Double score;

  /**
   * Constructs an {@code InvalidTestScore} with a detail message explaining the score and index
   * that are invalid.
   *
   * @param index The index of the invalid score
   * @param score The value of the invalid score
   */
  public InvalidTestScore(final int index, final double score) {
    super(String.format("Invalid score of %.0f at index %d", score, index));

    this.index = index;
    this.score = score;
  }

  /**
   * @return The index of the invalid test score
   */
  public int getIndex() {
    if (this.index == null) {
      throw new NullPointerException("No index is stored");
    }

    return this.index;
  }

  /**
   * @return The value of the invalid test score
   */
  public double getScore() {
    if (this.score == null) {
      throw new NullPointerException("No score is stored");
    }

    return this.score;
  }
}
