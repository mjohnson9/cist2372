package johnson.michael.drinkmachine;

import javax.swing.JPanel;

/**
 * ViewPanel adds an abstract {@code displaying} method to JPanel to be called when the panel is
 * being displayed.
 */
public abstract class ViewPanel extends JPanel {
  /**
   * displaying is called when a ViewPanel is being displayed to the user.
   */
  public abstract void displaying();
}
