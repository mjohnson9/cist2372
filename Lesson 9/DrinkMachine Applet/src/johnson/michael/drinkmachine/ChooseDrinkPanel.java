package johnson.michael.drinkmachine;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;

/**
 * ChooseDrinkPanel allows the user to choose a drink. It then informs the parent {@see
 * DrinkMachine} of the selection.
 */
public class ChooseDrinkPanel extends ViewPanel {
  private final DrinkMachine parent;

  private final JLabel moneyInsertedLabel;
  private final DrinkButton colaButton;
  private final DrinkButton lemonLimeButton;
  private final DrinkButton grapeButton;
  private final DrinkButton rootBeerButton;
  private final DrinkButton waterButton;

  /**
   * Constructs a {@code ChooseDrinkPanel}.
   * @param parent The parent {@see DrinkMachine}.
   */
  public ChooseDrinkPanel(final DrinkMachine parent) {
    super();

    this.parent = parent;

    this.setLayout(new GridBagLayout());

    final GridBagConstraints constraints = new GridBagConstraints();

    constraints.insets = new Insets(5, 5, 5, 5); // Padding of 5px on each side

    constraints.anchor = GridBagConstraints.CENTER;
    constraints.gridwidth = GridBagConstraints.REMAINDER;

    this.moneyInsertedLabel = new JLabel("You have inserted: $0.00");
    this.add(this.moneyInsertedLabel, constraints);

    constraints.gridwidth = 1; // Reset gridwidth

    constraints.gridy = 1; // Move to the second row
    constraints.gridx = 0; // Move to the first column

    this.colaButton = new DrinkButton(this, "Cola");
    this.add(this.colaButton, constraints);

    constraints.gridx += 1; // Move to the second column
    this.lemonLimeButton = new DrinkButton(this, "Lemon-Lime Soda");
    this.add(this.lemonLimeButton, constraints);

    constraints.gridx += 1; // Move to the third column
    this.grapeButton = new DrinkButton(this, "Grape Soda");
    this.add(this.grapeButton, constraints);

    constraints.gridx += 1; // Move to the fourth column
    this.rootBeerButton = new DrinkButton(this, "Root Beer");
    this.add(this.rootBeerButton, constraints);

    constraints.gridx += 1; // Move to the fifth column
    this.waterButton = new DrinkButton(this, "Bottled Water");
    this.add(this.waterButton, constraints);
  }

  @Override
  public void displaying() {
    this.updateMoneyInsertedLabel();
  }

  /**
   * Updates the {@code moneyInsertedLabel} with the current money value from the {@code parent}.
   */
  private void updateMoneyInsertedLabel() {
    this.moneyInsertedLabel.setText(
        String.format("You have inserted: $%,.2f", this.parent.getCurrentMoney()));
  }

  /**
   * Is called by a {@see DrinkButton} whenever a drink is chosen.
   * @param name The name of the drink that was chosen.
   */
  public void drinkChosen(final String name) {
    this.parent.drinkChosen(name);
  }
}
