package johnson.michael.bankcharges;

import javax.swing.JOptionPane;

public class Main {

  public static void main(String[] args) {
    final int numChecks = promptNumberOfChecks();
    final double fees = calculateServiceFees(numChecks);
    JOptionPane.showMessageDialog(null,
        String.format("Your service fees for %d checks is $%.2f.", numChecks, fees));
  }

  /**
   * calculateServiceFees calculates the service fees from the bank for a given number of checks.
   *
   * @param numChecks The number of checks written
   * @return The service fees in dollars
   */
  public static double calculateServiceFees(final int numChecks) {
    if (numChecks < 20) {
      // Less than 20 checks
      return numChecks * 0.10d;
    } else if (numChecks < 40) {
      // 20-39 checks
      return numChecks * 0.08d;
    } else if (numChecks < 60) {
      // 40-59 checks
      return numChecks * 0.06d;
    } else {
      // 60+ checks
      return numChecks * 0.04d;
    }
  }

  /**
   * promptNumberOfChecks prompts the user to enter a number of checks. It validates input from the
   * user and continues to prompt them until they cancel or provide a valid answer.
   *
   * @return The number of checks given by the user
   */
  public static int promptNumberOfChecks() {
    while (true) { // Continue to loop until we have a reason to exit the loop
      final String input = JOptionPane.showInputDialog("How many checks were written this month?");
      if (input == null) { // The user cancelled the dialog
        System.exit(0); // Exit the program
      }
      if (input.trim().equals("")) {
        JOptionPane.showMessageDialog(null,
            "The number of checks written cannot be blank.");
        continue; // Repeat the loop
      }
      try {
        // Attempt to parse the given input as an integer
        final int numChecks = Integer.parseInt(input);
        if (numChecks < 0) {
          JOptionPane.showMessageDialog(null,
              "The number of checks written cannot be negative.");
          continue; // Repeat the loop
        }
        return numChecks;
      } catch (NumberFormatException e) {
        // The given input was not an integer
        JOptionPane.showMessageDialog(null,
            "The given number of checks was not a valid integer.");
        continue; // Repeat the loop
      }
    }
  }
}
