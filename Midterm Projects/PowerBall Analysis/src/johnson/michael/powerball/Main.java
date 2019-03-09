package johnson.michael.powerball;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public final class Main {
  /**
   * Prevent Main from being instantiated.
   */
  private Main() {}

  public static void main(String[] args) {
    // Get the raw rolls
    PowerBallDrawing[] rolls;
    while (true) {
      File chosenFile = chooseFile();
      if (chosenFile == null) {
        // The user cancelled
        return;
      }

      try {
        rolls = PowerBallDrawing.readFromFile(chosenFile);
        break;
      } catch (FileNotFoundException ex) {
        JOptionPane.showMessageDialog(
            null, "The chosen file no longer exists.", "Error", JOptionPane.ERROR_MESSAGE);
      } catch (InputMismatchException ex) {
        JOptionPane.showMessageDialog(null, "The chosen file was not in the correct format.",
            "Error", JOptionPane.ERROR_MESSAGE);
      }
    }

    // Analyze the rolls
    PowerBallAnalysis analysis = new PowerBallAnalysis(rolls);

    // Display the analysis
    AnalysisFrame frame = new AnalysisFrame(analysis);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  private static File chooseFile() {
    JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
    chooser.setDialogTitle("Choose lottery file");
    chooser.setMultiSelectionEnabled(false);
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    int chosenOption = chooser.showOpenDialog(null);
    if (chosenOption != JFileChooser.APPROVE_OPTION) {
      return null;
    }

    return chooser.getSelectedFile();
  }
}
