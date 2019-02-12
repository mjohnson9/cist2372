package johnson.michael.coursegrades;

public class FinalExam extends GradedActivity {

  /**
   * Creates a FinalExam with the specified score
   *
   * @param score The initial score
   */
  public FinalExam(double score) {
    this.setScore(score);
  }

  public String toString() {
    return "Final Exam: " + this.getGrade() + " (" + this.getScore() + ")";
  }
}
