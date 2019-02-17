package johnson.michael.payroll;

import javax.swing.JOptionPane;

public class Main {

  public static void main(String[] args) {
    final Payroll payroll = new Payroll();
    promptPayroll(payroll);
    displayPayroll(payroll);
  }

  /**
   * Displays the payroll to the user using a PayrollTable
   *
   * @param payroll The Payroll whose data to use
   */
  public static void displayPayroll(final Payroll payroll) {
    final PayrollTable table = new PayrollTable(payroll);
    table.show();
  }

  /**
   * Prompts a user to enter the data to fill out a Payroll
   *
   * @param payroll The Payroll to fill out
   */
  public static void promptPayroll(final Payroll payroll) {
    for (int i = 0; i < payroll.length; i++) {
      final int employeeId = payroll.getEmployeeId(i);
      final double payRate = promptDouble(
          String.format("What is the pay rate for employee %d?", employeeId), "pay rate", 6.0d,
          Double.MAX_VALUE);
      final int hoursWorked = promptInt(
          String.format("How many hours has employee %d worked?", employeeId),
          "number of hours worked", 0, Integer.MAX_VALUE);
      payroll.setPayRate(i, payRate);
      payroll.setHours(i, hoursWorked);
    }
  }

  /**
   * promptInt prompts the user for an integer and validates it against given parameters
   *
   * @param prompt The prompt for the user
   * @param name The name of the integer being prompted for. This will be used in error messages.
   * @param minimum The minimum integer allowed
   * @param maximum The maximum integer allowed
   * @return The validated integer provided by the user
   */
  public static int promptInt(final String prompt, final String name, final int minimum,
      final int maximum) {
    while (true) { // Continue to loop until we have a reason to exit the loop
      final String answer = showPrompt(prompt, name);
      try {
        // Attempt to parse the given input as an integer
        final int answerParsed = Integer.parseInt(answer);
        if (answerParsed < minimum) {
          JOptionPane.showMessageDialog(null,
              String.format("The %s cannot be less than %d.", name, minimum));
          continue; // Repeat the loop
        } else if (answerParsed > maximum) {
          JOptionPane.showMessageDialog(null,
              String.format("The %s cannot be more than %d.", name, maximum));
        }
        return answerParsed;
      } catch (NumberFormatException e) {
        // The given input was not an integer
        JOptionPane.showMessageDialog(null, "The given " + name + " was not a valid integer.");
        continue; // Repeat the loop
      }
    }
  }

  /**
   * promptDouble prompts the user for a double and validates it against given parameters
   *
   * @param prompt The prompt for the user
   * @param name The name of the integer being prompted for. This will be used in error messages.
   * @param minimum The minimum double allowed
   * @param maximum The maximum double allowed
   * @return The validated double provided by the user
   */
  public static double promptDouble(final String prompt, final String name, final double minimum,
      final double maximum) {
    while (true) { // Continue to loop until we have a reason to exit the loop
      final String answer = showPrompt(prompt, name);
      try {
        // Attempt to parse the given input as an integer
        final double answerParsed = Double.parseDouble(answer);
        if (answerParsed < minimum) {
          JOptionPane.showMessageDialog(null,
              String.format("The %s cannot be less than %f.", name, minimum));
          continue; // Repeat the loop
        } else if (answerParsed > maximum) {
          JOptionPane.showMessageDialog(null,
              String.format("The %s cannot be more than %f.", name, maximum));
        }
        return answerParsed;
      } catch (NumberFormatException e) {
        // The given input was not an integer
        JOptionPane.showMessageDialog(null, "The given " + name + " was not a valid number.");
        continue; // Repeat the loop
      }
    }
  }

  /**
   * showPrompt shows a basic prompt to the user and validates that they entered something
   *
   * @param prompt The prompt for the user
   * @param name The name of the thing being prompted, used in error messages
   * @return A string from the user
   */
  private static String showPrompt(final String prompt, final String name) {
    while (true) { // Continue to loop until we have a reason to exit the loop
      final String answer = JOptionPane.showInputDialog(prompt);
      if (answer == null) { // The user cancelled the dialog
        System.exit(0); // Exit the program
      }
      if (answer.trim().equals("")) {
        JOptionPane.showMessageDialog(null, "The " + name + " cannot be blank.");
        continue; // Repeat the loop
      }
      return answer;
    }
  }
}
