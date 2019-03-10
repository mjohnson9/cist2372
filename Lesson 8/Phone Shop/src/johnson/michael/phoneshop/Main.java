package johnson.michael.phoneshop;

import javax.swing.JFrame;

/**
 * Entry point class.
 */
public final class Main {
  /**
   * Prevent Main from being instantiated.
   */
  private Main() {}

  /**
   * Program entry point.
   * @param args The command line arguments.
   */
  public static void main(final String[] args) {
    final ShopFrame frame = new ShopFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
