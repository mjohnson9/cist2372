package johnson.michael.theaterrevenue;

import javax.swing.JFrame;

public final class Main {

  /**
   * Prevents {@code Main} from being instantiated
   */
  private Main() {
  }

  public static void main(String[] args) {
    final EntryFrame entryFrame = new EntryFrame();
    entryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    entryFrame.setVisible(true);
  }
}
