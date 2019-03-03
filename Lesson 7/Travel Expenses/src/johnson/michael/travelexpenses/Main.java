package johnson.michael.travelexpenses;

import javax.swing.JFrame;

public final class Main {

  private Main() {
  }

  public static void main(final String[] args) {
    // Construct and display an EntryFrame. The EntryFrame takes responsibility for the continuation of the program after it's visible.
    final EntryFrame entryFrame = new EntryFrame();
    entryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    entryFrame.setVisible(true);
  }
}
