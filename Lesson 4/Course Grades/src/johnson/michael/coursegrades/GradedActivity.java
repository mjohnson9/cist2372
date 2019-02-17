package johnson.michael.coursegrades;

public class GradedActivity {

  /**
   * The score for this activity
   */
  private double score = 100.0;

  /**
   * Creates a GradedActivity with a score of 100.0.
   */
  public GradedActivity() {
  }

  /**
   * Creates a GradedActivity with the specified score
   *
   * @param score The initial score
   */
  public GradedActivity(double score) {
    this.setScore(score);
  }

  /**
   * @return The score for this activity
   */
  public double getScore() {
    return score;
  }

  /**
   * Changes the score for this activity
   *
   * @param score The new score for this activity
   */
  public void setScore(double score) {
    this.score = score;
  }

  /**
   * @return The letter grade for this activity
   */
  public char getGrade() {
    final double score = this.getScore();

    if (score < 70) {
      return 'F';
    } else if (score < 80) {
      return 'C';
    } else if (score < 90) {
      return 'B';
    } else {
      return 'A';
    }
  }

  public String toString() {
    return "Activity: " + this.getGrade() + " (" + this.getScore() + ")";
  }
}
