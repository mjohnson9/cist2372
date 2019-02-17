package johnson.michael.coursegrades;

public class PassFailExam extends GradedActivity {

  /**
   * Creates a PassFailExam with the specified score
   *
   * @param score The initial score
   */
  public PassFailExam(double score) {
    this.setScore(score);
  }

  /**
   * @return Whether or not the exam was passed
   */
  public boolean didPass() {
    return this.getScore() >= 70;
  }

  public String toString() {
    return "Pass/Fail Exam: " + (this.didPass() ? "Passed" : "Failed") + " (" + this.getScore()
        + ")";
  }
}
