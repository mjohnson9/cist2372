package johnson.michael.drinkmachine;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JApplet;
import javax.swing.JOptionPane;

public class DrinkMachine extends JApplet {
  public static final double DRINK_COST = 0.75d;

  private InsertMoneyPanel insertMoneyPanel;
  private ChooseDrinkPanel chooseDrinkPanel;

  private double currentMoney;

  @Override
  public void init() {
    super.init();

    this.currentMoney = 0.00d;

    // Set the content pane's layout to a 1x1 grid so that whatever is in it is sized to fit.
    this.getContentPane().setLayout(new GridLayout(1, 1));

    this.insertMoneyPanel = new InsertMoneyPanel(this);
    this.chooseDrinkPanel = new ChooseDrinkPanel(this);

    this.switchTo(this.insertMoneyPanel);
  }

  private void switchTo(final ViewPanel panel) {
    final Container contentPane = this.getContentPane();

    // Remove all of the existing content.
    contentPane.removeAll();
    // Add the new panel.
    contentPane.add(panel);
    // Repaint so that the background covers any leftovers from the old content.
    contentPane.repaint();
    // Tell the new panel that it's being displayed.
    panel.displaying();
  }

  /**
   * moneyInserted is to be called by the {@see InsertMoneyPanel} to inform {@code DrinkMachine}
   * that money has been inserted.
   * @param money The amount of money inserted.
   */
  public void moneyInserted(final double money) {
    this.currentMoney += money;
    if (this.currentMoney >= DRINK_COST) {
      this.switchTo(this.chooseDrinkPanel);
    }
  }

  /**
   * drinkChosen is to be called by the {@see ChooseDrinkPanel} to inform {@code DrinkMachine} that
   * a drink has been chosen.
   * @param name The name of the drink chosen.
   */
  public void drinkChosen(final String name) {
    final double change = this.currentMoney - DRINK_COST;
    JOptionPane.showMessageDialog(
        this, String.format("Enjoy your %s! You get $%,.2f back in change.", name, change));

    this.currentMoney = 0.00d;
    this.switchTo(this.insertMoneyPanel);
  }

  public double getCurrentMoney() {
    return this.currentMoney;
  }
}
