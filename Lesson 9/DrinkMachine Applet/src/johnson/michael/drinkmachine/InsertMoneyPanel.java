package johnson.michael.drinkmachine;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.NumberFormatter;

/**
 * InsertMoneyPanel provides the user with an interface to insert money into the drink machine.
 */
public class InsertMoneyPanel extends ViewPanel {
  private final DrinkMachine parent;
  private final JFormattedTextField moneyField;
  private final JLabel moneyInsertedLabel;
  private final JButton insertButton;

  /**
   * Constructs an {@code InsertMoneyPanel}.
   * @param parent The parent {@see DrinkMachine}.
   */
  public InsertMoneyPanel(final DrinkMachine parent) {
    super();

    this.parent = parent;

    this.setLayout(new GridBagLayout());

    final GridBagConstraints constraints = new GridBagConstraints();

    constraints.insets = new Insets(5, 5, 5, 5); // Padding of 5px on each side

    constraints.anchor = GridBagConstraints.CENTER; // Anchor in the center of the parent container
    constraints.fill = GridBagConstraints.NONE; // Don't attempt to stretch to fill

    this.add(new JLabel("How much money would you like to insert?"), constraints);

    constraints.gridy = 1; // Move to the second row

    this.moneyField = new JFormattedTextField(new NumberFormatter(new DecimalFormat("0.00")));
    this.moneyField.setText("0.00");
    this.moneyField.setPreferredSize(new Dimension(60, 20));
    this.add(this.moneyField, constraints);

    constraints.gridy = 2; // Move to the third row

    this.moneyInsertedLabel = new JLabel("You have inserted: $0.00");
    this.add(this.moneyInsertedLabel, constraints);

    constraints.gridy = 3; // Move to the fourth row

    this.insertButton = new JButton("Insert money");
    this.insertButton.addActionListener(new ButtonClicked());
    this.add(this.insertButton, constraints);
  }

  @Override
  public void displaying() {
    this.updateMoneyInsertedLabel();
    this.moneyField.setText("0.00");
  }

  /**
   * Updates the {@code moneyInsertedLabel} to match the current value from the {@code parent}.
   */
  private void updateMoneyInsertedLabel() {
    this.moneyInsertedLabel.setText(
        String.format("You have inserted: $%,.2f", this.parent.getCurrentMoney()));
  }

  /**
   * ButtonClicked handles the insert button of the {@see InsertMoneyPanel} being clicked.
   */
  private class ButtonClicked implements ActionListener {
    @Override
    public void actionPerformed(final ActionEvent e) {
      final double money;
      try {
        money = Double.parseDouble(InsertMoneyPanel.this.moneyField.getText());
      } catch (final NumberFormatException ex) {
        JOptionPane.showMessageDialog(InsertMoneyPanel.this,
            "The amount of money you entered is not a valid number.", "Error",
            JOptionPane.ERROR_MESSAGE);
        return;
      }

      InsertMoneyPanel.this.parent.moneyInserted(money);
      InsertMoneyPanel.this.updateMoneyInsertedLabel();
    }
  }
}
