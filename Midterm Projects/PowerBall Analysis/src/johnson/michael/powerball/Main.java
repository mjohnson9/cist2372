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

  public static void main(final String[] args) {
    // Get the raw rolls
    Drawing[] rolls;
    while (true) {
      final File chosenFile = chooseFile();
      if (chosenFile == null) {
        // The user cancelled
        return;
      }

      try {
        rolls = Drawing.readFromFile(chosenFile);
        break;
      } catch (final FileNotFoundException ex) {
        JOptionPane.showMessageDialog(
            null, "The chosen file no longer exists.", "Error", JOptionPane.ERROR_MESSAGE);
      } catch (final InputMismatchException ex) {
        JOptionPane.showMessageDialog(null, "The chosen file was not in the correct format.",
            "Error", JOptionPane.ERROR_MESSAGE);
      }
    }

    // Analyze the rolls
    final Analysis analysis = new Analysis(rolls);

    // Display the analysis
    final AnalysisFrame frame = new AnalysisFrame(analysis);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }

  /**
   * Prompts the user to choose a lottery file, starting in the current working directory.
   * @return The File chosen or null if the user cancelled the interaction.
   */
  private static File chooseFile() {
    final JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
    chooser.setDialogTitle("Choose lottery file");
    chooser.setMultiSelectionEnabled(false);
    chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    final int chosenOption = chooser.showOpenDialog(null);
    if (chosenOption != JFileChooser.APPROVE_OPTION) {
      return null;
    }

    return chooser.getSelectedFile();
  }
}
