package johnson.michael.powerball;

import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFrame;

public class Main {
  /**
   * Prevent Main from being instantiated
   */
  private Main() {}

  public static void main(String[] args) {
    // Get the raw rolls
    PowerBallDrawing[] rolls;
    try {
      rolls = PowerBallDrawing.readFromFile(new File("pbnumbers.txt"));
    } catch (FileNotFoundException ex) {
      System.out.println("The pbnumbers.txt file was not found in the current working directory.");
      System.exit(1);
      return;
    }

    // Analyze the rolls
    PowerBallAnalysis analysis = new PowerBallAnalysis(rolls);

    // Display the analysis
    AnalysisFrame frame = new AnalysisFrame(analysis);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
