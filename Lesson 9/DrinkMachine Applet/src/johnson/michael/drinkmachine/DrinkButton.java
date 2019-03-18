package johnson.michael.drinkmachine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 * DrinkButton provides a clickable button that keeps track of its drink inventory. It informs a
 * parent {@see ChooseDrinkPanel} whenever a selection is made.
 */
public class DrinkButton extends JButton {
  private static final int STARTING_INVENTORY = 20;

  private final ChooseDrinkPanel parent;
  private int inventory;

  /**
   * Constructs a {@code DrinkButton}.
   * @param parent The parent {@see ChooseDrinkPanel}.
   * @param name The name of the drink.
   */
  public DrinkButton(final ChooseDrinkPanel parent, final String name) {
    super(name);

    this.parent = parent;

    this.inventory = STARTING_INVENTORY;

    this.addActionListener(new ButtonPressed());
  }

  private void chosen() {
    if (this.inventory <= 0) {
      JOptionPane.showMessageDialog(
          this, "The machine is out of " + this.getText() + ". Please choose another drink.");
      return;
    }

    this.inventory -= 1;
    this.parent.drinkChosen(this.getText());
  }

  private class ButtonPressed implements ActionListener {
    @Override
    public void actionPerformed(final ActionEvent e) {
      DrinkButton.this.chosen();
    }
  }
}
