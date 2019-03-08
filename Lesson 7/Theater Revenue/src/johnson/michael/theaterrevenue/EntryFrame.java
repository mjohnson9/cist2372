package johnson.michael.theaterrevenue;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EntryFrame extends JFrame {
  private JTextField adultTicketPriceField = new JTextField(5);
  private JTextField childTicketPriceField = new JTextField(5);
  private JTextField numAdultTicketsField = new JTextField(5);
  private JTextField numChildTicketsField = new JTextField(5);
  private JButton submitButton = new JButton("Submit");

  public EntryFrame() {
    super();

    this.setTitle("Theater Revenue Calculator");

    this.setLayout(new GridLayout(5, 2, 5, 5));

    this.add(new JLabel("Adult ticket price:", JLabel.RIGHT));
    this.add(this.adultTicketPriceField);

    this.add(new JLabel("Child ticket price:", JLabel.RIGHT));
    this.add(this.childTicketPriceField);

    this.add(new JLabel("Number of adult tickets sold:", JLabel.RIGHT));
    this.add(this.numAdultTicketsField);

    this.add(new JLabel("Number of child tickets sold:", JLabel.RIGHT));
    this.add(this.numChildTicketsField);

    this.add(new JLabel());
    this.add(this.submitButton);
    this.submitButton.addActionListener(new SubmitListener());

    this.pack();
    final Dimension packedSize = this.getSize();
    this.setMinimumSize(packedSize);
    this.setSize(packedSize.width + 20, packedSize.height + 20);
  }

  private class SubmitListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      double adultPrice;
      try {
        adultPrice = this.parseAdultPrice();
      } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(EntryFrame.this, ex.getMessage());
        return;
      }

      double childPrice;
      try {
        childPrice = this.parseChildPrice();
      } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(EntryFrame.this, ex.getMessage());
        return;
      }

      int adultTicketsSold;
      try {
        adultTicketsSold =
            this.parseIntegerField(EntryFrame.this.numAdultTicketsField, "adult tickets sold");
      } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(EntryFrame.this, ex.getMessage());
        return;
      }

      int childTicketsSold;
      try {
        childTicketsSold =
            this.parseIntegerField(EntryFrame.this.numChildTicketsField, "child tickets sold");
      } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(EntryFrame.this, ex.getMessage());
        return;
      }

      if ((adultTicketsSold + childTicketsSold) == 0) {
        JOptionPane.showMessageDialog(EntryFrame.this,
            "Are you sure that you sold zero tickets? Please enter some ticket sales.");
        return;
      }

      EntryFrame.this.setVisible(false);

      final ResultsFrame resultsFrame =
          new ResultsFrame(adultPrice, adultTicketsSold, childPrice, childTicketsSold);
      resultsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      resultsFrame.setVisible(true);
    }

    /**
     * Parses the {@code adultTicketPriceField} text field and validates it.
     * @return A parsed, validated adult ticket price.
     * @throws IllegalArgumentException When the text field contains an invalid value. The message
     *     of the exception is a user-friendly message explaining why it's invalid.
     */
    private double parseAdultPrice() throws IllegalArgumentException {
      double adultPrice =
          this.parseDollarField(EntryFrame.this.adultTicketPriceField, "adult ticket price");

      if (adultPrice == 0.00d) {
        throw new IllegalArgumentException("Your adult tickets cannot be free.");
      }

      return adultPrice;
    }

    /**
     * Parses the {@code childTicketPriceField} text field and validates it.
     * @return A parsed, validated child ticket price.
     * @throws IllegalArgumentException When the text field contains an invalid value. The message
     *     of the exception is a user-friendly message explaining why it's invalid.
     */
    private double parseChildPrice() throws IllegalArgumentException {
      double childPrice =
          this.parseDollarField(EntryFrame.this.childTicketPriceField, "child ticket price");

      if (childPrice == 0.00d) {
        throw new IllegalArgumentException("Your child tickets cannot be free.");
      }

      return childPrice;
    }

    /**
     * Parses a JTextField that's expected to be a dollar amount.
     *
     * @param field The field to parse.
     * @param fieldName The name of the field for error messages.
     * @return The parsed, validated value.
     * @throws IllegalArgumentException If the JTextField does not contain a valid dollar amount,
     * this exception is thrown with a user-friendly message.
     */
    private double parseDollarField(final JTextField field, final String fieldName)
        throws IllegalArgumentException {
      try {
        final double parsed = Double.parseDouble(field.getText());
        if (parsed < 0) {
          throw new IllegalArgumentException("The " + fieldName + " cannot be negative.");
        }

        return parsed;
      } catch (final NumberFormatException e) {
        throw new IllegalArgumentException(
            "The " + fieldName + " must be a decimal number. You did not enter a decimal number.",
            e);
      }
    }

    /**
     * Parses a JTextField that's expected to be a dollar amount.
     *
     * @param field The field to parse.
     * @param fieldName The name of the field for error messages.
     * @return The parsed, validated value.
     * @throws IllegalArgumentException If the JTextField does not contain a valid dollar amount,
     * this exception is thrown with a user-friendly message.
     */
    private int parseIntegerField(final JTextField field, final String fieldName)
        throws IllegalArgumentException {
      try {
        final int parsed = Integer.parseInt(field.getText());
        if (parsed < 0) {
          throw new IllegalArgumentException("The " + fieldName + " cannot be negative.");
        }

        return parsed;
      } catch (final NumberFormatException e) {
        throw new IllegalArgumentException(
            "The " + fieldName + " must be a number. You did not enter a decimal number.", e);
      }
    }
  }
}
