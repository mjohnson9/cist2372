package johnson.michael.gridfiller;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JApplet;

/**
 * The GridFiller applet contains four {@code GridItem}s that draw circles when clicked.
 */
public class GridFiller extends JApplet {
  /**
   * squares are the GridItems that fill the GridFiller.
   */
  private GridItem[] squares;

  @Override
  public void init() {
    super.init();

    this.squares = new GridItem[] {new GridItem(new Color(255, 100, 100), new Color(100, 255, 100)),
        new GridItem(new Color(100, 100, 255), new Color(255, 100, 100)),
        new GridItem(new Color(100, 100, 255), new Color(255, 100, 100)),
        new GridItem(new Color(255, 100, 100), new Color(100, 255, 100))};

    final Container contentPane = this.getContentPane();
    contentPane.setLayout(new GridLayout(2, 2)); // Give the content pane a 2x2 grid layout
    for (final GridItem item : this.squares) { // Add each of the squares to the content pane
      contentPane.add(item);
    }
  }
}
