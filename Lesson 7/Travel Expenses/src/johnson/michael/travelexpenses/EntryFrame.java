package johnson.michael.travelexpenses;

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

  private JTextField daysField = new JTextField(8);
  private JTextField airfareField = new JTextField("0.00", 8);
  private JTextField rentalFeesField = new JTextField("0.00", 8);
  private JTextField milesDrivenField = new JTextField("0.0", 8);
  private JTextField parkingFeesField = new JTextField("0.00", 8);
  private JTextField taxiFeesField = new JTextField("0.00", 8);
  private JTextField conferenceFeesField = new JTextField("0.00", 8);
  private JTextField lodgingFeesField = new JTextField("0.00", 8);
  private JButton submitButton = new JButton("Submit");

  public EntryFrame() {
    super();

    this.setSize(500, 300);
    this.setMinimumSize(new Dimension(500, 300));
    this.setLayout(new GridLayout(9, 2, 5, 5));

    this.add(new JLabel("How many days was the trip?"));
    this.add(this.daysField);

    this.add(new JLabel("Airfare:"));
    this.add(this.airfareField);

    this.add(new JLabel("Rental fees:"));
    this.add(this.rentalFeesField);

    this.add(new JLabel("Miles driven in a personal vehicle:"));
    this.add(this.milesDrivenField);

    this.add(new JLabel("Parking fees:"));
    this.add(this.parkingFeesField);

    this.add(new JLabel("Taxi fees:"));
    this.add(this.taxiFeesField);

    this.add(new JLabel("Conference fees:"));
    this.add(this.conferenceFeesField);

    this.add(new JLabel("Lodging fees:"));
    this.add(this.lodgingFeesField);

    this.add(new JLabel());
    this.add(this.submitButton);
    this.submitButton.addActionListener(new SubmitHandler());
  }

  private class SubmitHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
      Expenses expenses = new Expenses();

      if (!this.handleDays(expenses)) {
        return;
      }
      if (!this.handleAirfare(expenses)) {
        return;
      }
      if (!this.handleRentalFees(expenses)) {
        return;
      }
      if (!this.handleMilesDriven(expenses)) {
        return;
      }
      if (!this.handleParkingFees(expenses)) {
        return;
      }
      if (!this.handleTaxiFees(expenses)) {
        return;
      }
      if (!this.handleConferenceFees(expenses)) {
        return;
      }
      if (!this.handleLodgingFees(expenses)) {
        return;
      }

      setVisible(false);

      ResultsFrame resultsFrame = new ResultsFrame(expenses);
      resultsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      resultsFrame.setVisible(true);
    }

    private boolean handleDays(Expenses expenses) {
      try {
        final int numDays = Integer.parseInt(daysField.getText());

        if (numDays < 0) {
          JOptionPane.showMessageDialog(EntryFrame.this, "The number of days cannot be negative.");
          return false;
        }
        if (numDays == 0) {
          JOptionPane.showMessageDialog(EntryFrame.this, "The number of days cannot be zero.");
          return false;
        }

        expenses.setDays(numDays);
        return true;
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(EntryFrame.this,
            "The number of days that you entered is not a valid number. Please enter a number.");
        return false;
      }
    }

    private boolean handleAirfare(Expenses expenses) {
      try {
        expenses.setAirfare(this.parseField(airfareField, "airfare"));
        return true;
      } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(EntryFrame.this, e.getMessage());
        return false;
      }
    }

    private boolean handleRentalFees(Expenses expenses) {
      try {
        expenses.setRentalFees(this.parseField(rentalFeesField, "rental fees"));
        return true;
      } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(EntryFrame.this, e.getMessage());
        return false;
      }
    }

    private boolean handleMilesDriven(Expenses expenses) {
      try {
        expenses.setMilesDriven(this.parseField(milesDrivenField, "miles driven"));
        return true;
      } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(EntryFrame.this, e.getMessage());
        return false;
      }
    }

    private boolean handleParkingFees(Expenses expenses) {
      try {
        expenses.setParkingFees(this.parseField(parkingFeesField, "parking fees"));
        return true;
      } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(EntryFrame.this, e.getMessage());
        return false;
      }
    }

    private boolean handleTaxiFees(Expenses expenses) {
      try {
        expenses.setTaxiFees(this.parseField(taxiFeesField, "taxi fees"));
        return true;
      } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(EntryFrame.this, e.getMessage());
        return false;
      }
    }

    private boolean handleConferenceFees(Expenses expenses) {
      try {
        expenses.setConferenceFees(this.parseField(conferenceFeesField, "conference fees"));
        return true;
      } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(EntryFrame.this, e.getMessage());
        return false;
      }
    }

    private boolean handleLodgingFees(Expenses expenses) {
      try {
        expenses.setLodgingCost(this.parseField(lodgingFeesField, "lodging fees"));
        return true;
      } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(EntryFrame.this, e.getMessage());
        return false;
      }
    }

    private double parseField(JTextField field, String fieldName) throws IllegalArgumentException {
      try {
        final double parsed = Double.parseDouble(field.getText());
        if (parsed < 0) {
          throw new IllegalArgumentException("The " + fieldName + " cannot be negative.");
        }

        return parsed;
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException(
            "The " + fieldName + " must be a decimal number. You did not enter a decimal number.");
      }
    }
  }
}
