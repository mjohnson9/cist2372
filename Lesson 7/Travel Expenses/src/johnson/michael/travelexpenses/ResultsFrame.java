package johnson.michael.travelexpenses;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * ResultsFrame displays the results of an EntryFrame.
 */
public class ResultsFrame extends JFrame {
  /**
   * Construct a ResultsFrame with the specified expenses.
   *
   * @param expenses The expenses to base the displayed results on.
   */
  public ResultsFrame(final Expenses expenses) {
    super();

    this.setTitle("Calculated Travel Expenses");

    this.setLayout(new GridLayout(3, 1));

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

    this.pack();
    final Dimension packedSize = this.getSize();
    this.setMinimumSize(packedSize);
    this.setSize(packedSize.width + 20, packedSize.height + 20);
  }
}
