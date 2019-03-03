package johnson.michael.travelexpenses;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ResultsFrame extends JFrame {

  public ResultsFrame(Expenses expenses) {
    this.setSize(380, 100);
    this.setLayout(new GridLayout(3, 1));
    this.setMinimumSize(new Dimension(380, 100));

    this.add(new JLabel(String.format("Your total expenses: $%,.2f", expenses.getTotal())));
    this.add(new JLabel(String.format("Your total allowance: $%,.2f", expenses.getAllowable())));

    final double owed = expenses.getAmountOwed();
    if (owed == 0) {
      this.add(new JLabel("You were exactly on budget. You owe nothing."));
    } else if (owed > 0) {
      this.add(
          new JLabel(String.format("You were over budget. You owe the company $%,.2f.", owed)));
    } else if (owed < 0) {
      this.add(
          new JLabel(String.format("You were under budget by $%,.2f. You owe nothing.", -owed)));
    }
  }
}
