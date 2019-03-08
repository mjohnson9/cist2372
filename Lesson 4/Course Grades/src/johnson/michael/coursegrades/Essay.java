package johnson.michael.coursegrades;

public class Essay extends GradedActivity {
  /**
   * Creates an Essay with the specified score
   *
   * @param score The initial score
   */
  public Essay(double score) {
    this.setScore(score);
  }

  public String toString() {
    return "Essay: " + this.getGrade() + " (" + this.getScore() + ")";
  }
}
