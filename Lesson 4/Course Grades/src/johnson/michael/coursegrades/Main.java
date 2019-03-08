package johnson.michael.coursegrades;

public class Main {
  public static void main(String[] args) {
    final CourseGrades grades = UI.promptGrades();
    UI.displayCourseGrades(grades);
  }
}
