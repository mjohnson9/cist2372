package johnson.michael.coursegrades;

public class CourseGrades {
  /**
   * The array index at which the lab is stored
   */
  static private final int GRADE_LAB = 0;
  /**
   * The array index at which the pass/fail exam is stored
   */
  static private final int GRADE_PASS_FAIL_EXAM = 1;
  /**
   * The array index at which the essay is stored
   */
  static private final int GRADE_ESSAY = 2;
  /**
   * The array index at which the final exam is stored
   */
  static private final int GRADE_FINAL_EXAM = 3;

  /**
   * This student's grades
   */
  private final GradedActivity[] grades = new GradedActivity[4];

  /**
   * Instantiates a new CourseGrades object with no grades stored
   */
  public CourseGrades() {}

  public GradedActivity getLab() {
    return this.grades[CourseGrades.GRADE_LAB];
  }

  public void setLab(final GradedActivity activity) {
    this.grades[CourseGrades.GRADE_LAB] = activity;
  }

  public PassFailExam getPassFailExam() {
    final GradedActivity activity = this.grades[CourseGrades.GRADE_PASS_FAIL_EXAM];
    if (activity == null) {
      return null;
    }

    return (PassFailExam) activity;
  }

  public void setPassFailExam(final PassFailExam exam) {
    this.grades[CourseGrades.GRADE_PASS_FAIL_EXAM] = exam;
  }

  public Essay getEssay() {
    final GradedActivity activity = this.grades[CourseGrades.GRADE_ESSAY];
    if (activity == null) {
      return null;
    }

    return (Essay) activity;
  }

  public void setEssay(final Essay essay) {
    this.grades[CourseGrades.GRADE_ESSAY] = essay;
  }

  public FinalExam getFinalExam() {
    final GradedActivity activity = this.grades[CourseGrades.GRADE_FINAL_EXAM];
    if (activity == null) {
      return null;
    }

    return (FinalExam) activity;
  }

  public void setFinalExam(final FinalExam exam) {
    this.grades[CourseGrades.GRADE_FINAL_EXAM] = exam;
  }

  public String toString() {
    return "Grades:\n" + this.getLab() + "\n" + this.getPassFailExam() + "\n" + this.getEssay()
        + "\n" + this.getFinalExam();
  }
}
