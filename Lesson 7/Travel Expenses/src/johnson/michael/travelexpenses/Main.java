package johnson.michael.travelexpenses;

import javax.swing.JFrame;

/**
 * The class in which the program entry point is housed.
 */
public final class Main {
  /**
   * A private constructor to prevent Main being instantiated.
   */
  private Main() {}

  /**
   * The program entry point.
   * @param args The command line arguments given to the program.
   */
  public static void main(final String[] args) {
    // Construct and display an EntryFrame. The EntryFrame takes responsibility for the continuation
    // of the program after it's visible.
    final EntryFrame entryFrame = new EntryFrame();
    entryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    entryFrame.setVisible(true);
  }
}
