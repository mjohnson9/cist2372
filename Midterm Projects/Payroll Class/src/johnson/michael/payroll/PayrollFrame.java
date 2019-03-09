package johnson.michael.payroll;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class PayrollFrame extends JFrame {
  private static final int MARGIN = 20;

  private JTextField idField;
  private JTextField firstNameField;
  private JTextField lastNameField;
  private JTextField hourlyPayField;
  private JTextField hoursWorkedField;
  private JButton submitButton;

  public PayrollFrame() {
    super();

    this.setLayout(new GridLayout(6, 2, 10, 10));

    this.add(new JLabel("Employee ID:", JLabel.RIGHT));
    this.idField = new JTextField(10);
    this.add(this.idField);

    this.add(new JLabel("First name:", JLabel.RIGHT));
    this.firstNameField = new JTextField(10);
    this.add(this.firstNameField);

    this.add(new JLabel("Last name:", JLabel.RIGHT));
    this.lastNameField = new JTextField(10);
    this.add(this.lastNameField);

    this.add(new JLabel("Hourly pay rate:", JLabel.RIGHT));
    this.hourlyPayField = new JTextField(10);
    this.add(this.hourlyPayField);

    this.add(new JLabel("Hours worked:", JLabel.RIGHT));
    this.hoursWorkedField = new JTextField(10);
    this.add(this.hoursWorkedField);

    this.add(new JLabel()); // Add a blank label so that our button is in the right column

    this.submitButton = new JButton("Submit");
    this.submitButton.addActionListener(new SubmitButtonClicked());
    this.add(this.submitButton);

    this.pack();
    final Dimension packedSize = this.getSize();
    this.setMinimumSize(packedSize);
    this.setSize(packedSize.width + MARGIN, packedSize.height + MARGIN);
  }

  private class SubmitButtonClicked implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      ArrayList<String> errorMessages = new ArrayList<>();

      int id = 0;
      try {
        id = this.getId();
      } catch (IllegalArgumentException ex) {
        errorMessages.add(ex.getMessage());
      }

      String firstName = "";
      try {
        firstName = this.getName(PayrollFrame.this.firstNameField, "first");
      } catch (IllegalArgumentException ex) {
        errorMessages.add(ex.getMessage());
      }

      String lastName = "";
      try {
        lastName = this.getName(PayrollFrame.this.lastNameField, "last");
      } catch (IllegalArgumentException ex) {
        errorMessages.add(ex.getMessage());
      }

      if (errorMessages.size() > 0) {
        this.displayErrorMessage(errorMessages);
        return;
      }

      Payroll payroll;
      try {
        payroll = new Payroll(id, firstName, lastName);
      } catch (IllegalArgumentException ex) {
        errorMessages.add(ex.getMessage());
        this.displayErrorMessage(errorMessages);
        return;
      }

      try {
        this.setHourlyPay(payroll);
      } catch (IllegalArgumentException ex) {
        errorMessages.add(ex.getMessage());
      }

      try {
        this.setHoursWorked(payroll);
      } catch (IllegalArgumentException ex) {
        errorMessages.add(ex.getMessage());
      }

      if (errorMessages.size() > 0) {
        this.displayErrorMessage(errorMessages);
        return;
      }

      final String displayMessage = String.format("%s %s's gross wages are $%,.2f.",
          payroll.getFirstName(), payroll.getLastName(), payroll.getGrossPay());
      JOptionPane.showMessageDialog(
          PayrollFrame.this, displayMessage, "Gross wages", JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayErrorMessage(ArrayList<String> errorMessages) {
      StringBuilder stringBuilder = new StringBuilder();
      for (int i = 0; i < errorMessages.size(); i++) {
        if (i != 0) {
          stringBuilder.append('\n');
        }
        stringBuilder.append(errorMessages.get(i));
      }

      JOptionPane.showMessageDialog(
          PayrollFrame.this, stringBuilder.toString(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    private int getId() throws IllegalArgumentException {
      final String idText = PayrollFrame.this.idField.getText().trim();
      if (idText.length() == 0) {
        throw new IllegalArgumentException("The employee ID cannot be blank");
      }
      try {
        return Integer.parseInt(idText);
      } catch (NumberFormatException ex) {
        throw new IllegalArgumentException("The employee's ID is not a number", ex);
      }
    }

    private String getName(final JTextField nameField, final String whichName)
        throws IllegalArgumentException {
      final String nameText = nameField.getText().trim();
      if (nameText.length() == 0) {
        throw new IllegalArgumentException("The employee's " + whichName + " name cannot be blank");
      }
      return nameText;
    }

    private void setHourlyPay(final Payroll payroll) throws IllegalArgumentException {
      final String hourlyPayText = PayrollFrame.this.hourlyPayField.getText().trim();
      if (hourlyPayText.length() == 0) {
        throw new IllegalArgumentException("The hourly pay cannot be blank");
      }

      double hourlyPay;
      try {
        hourlyPay = Double.parseDouble(hourlyPayText);
      } catch (NumberFormatException ex) {
        throw new IllegalArgumentException("The entered hourly pay is not a number", ex);
      }

      payroll.setHourlyPayRate(hourlyPay);
    }

    private void setHoursWorked(final Payroll payroll) throws IllegalArgumentException {
      final String hoursWorkedText = PayrollFrame.this.hoursWorkedField.getText().trim();
      if (hoursWorkedText.length() == 0) {
        throw new IllegalArgumentException("The hours worked cannot be blank");
      }

      double hoursWorked;
      try {
        hoursWorked = Double.parseDouble(hoursWorkedText);
      } catch (NumberFormatException ex) {
        throw new IllegalArgumentException("The entered hours worked is not a number", ex);
      }

      payroll.setHoursWorked(hoursWorked);
    }
  }
}
