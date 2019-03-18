package johnson.michael.gridfiller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 * GridItem is a {@code JPanel} that draws a background and will toggle a circle when clicked.
 */
public class GridItem extends JPanel {
  /**
   * circleColor is the color of the circle, when it is displayed.
   */
  private final Color circleColor;
  /**
   * circleDisplayed is a boolean representing whether or not the circle should be displayed.
   */
  private boolean circleDisplayed;

  /**
   * Constructs a new {@code GridItem} with the specified background color and circle color.
   * @param backgroundColor The background color.
   * @param circleColor The circle color.
   */
  public GridItem(final Color backgroundColor, final Color circleColor) {
    super();

    this.circleDisplayed = false;
    this.circleColor = circleColor;
    this.setBackground(backgroundColor);
    this.addMouseListener(new MouseHandler());
  }

  @Override
  public void paintComponent(final Graphics g) {
    super.paintComponent(g);

    if (this.isCircleDisplayed()) {
      // Set the drawing color to circleColor
      g.setColor(this.circleColor);
      // Draw a filled oval over the whole panel
      g.fillOval(0, 0, this.getWidth(), this.getHeight());
    }
  }

  /**
   * Gets whether or not the circle is displayed.
   * @return Whether or not the circle is displayed.
   */
  public boolean isCircleDisplayed() {
    return this.circleDisplayed;
  }

  /**
   * Sets whether or not the circle is displayed.
   * @param displayed Whether or not the circle is displayed.
   */
  private void setCircleDisplayed(final boolean displayed) {
    this.circleDisplayed = displayed;
  }

  /**
   * MouseHandler handles mouse events for GridItem.
   */
  private class MouseHandler implements MouseListener {
    @Override
    public void mouseReleased(final MouseEvent e) {
      // mouseClicked is unreliable because it won't fire if the mouse is moved at all between
      // mousePressed and mouseReleased

      if (GridItem.this.contains(e.getPoint())) { // If the mouse release is inside of the panel
        GridItem.this.setCircleDisplayed(!GridItem.this.isCircleDisplayed()); // Flip the boolean
        GridItem.this.repaint(); // Tell the panel to repaint itself
      }
    }

    @Override
    public void mouseClicked(final MouseEvent e) {}

    @Override
    public void mousePressed(final MouseEvent e) {}

    @Override
    public void mouseEntered(final MouseEvent e) {}

    @Override
    public void mouseExited(final MouseEvent e) {}
  }
}
