package johnson.michael.sumdigits;

import javax.swing.JOptionPane;

public class Main {
  public static void main(String[] args) {
    while (true) { // Loop until the user presses cancel on the prompt dialog
      final String digits = promptDigits();

      final long sum = sumDigits(digits);
      JOptionPane.showMessageDialog(null, "The sum of " + digits + " is: " + sum);
    }
  }

  /**
   * Prompts the user for a string of digits to be summed
   *
   * @return Validated string of digits to be summed; will only contain digit characters
   */
  public static String promptDigits() {
    while (true) {
      String digits = JOptionPane.showInputDialog("Please enter digits to be summed:");
      if (digits == null) {
        // User pressed cancel
        System.exit(0);
      }

      digits = digits.trim();
      if (!isDigits(digits)) {
        JOptionPane.showMessageDialog(null,
            "Your input may only contain digits.\n" + digits + " contains non-digit characters.");
        continue;
      }

      return digits;
    }
  }

  /**
   * Checks if a string consists only of digits
   *
   * @param str String to be checked
   * @return true if {@code str} only contains digits; false otherwise
   */
  public static boolean isDigits(final String str) {
    for (char c : str.toCharArray()) {
      if (Character.isDigit(c)) {
        // Is a digit; continue searching
        continue;
      }

      return false;
    }

    return true;
  }

  /**
   * Sums a string of digits
   *
   * @param str String to be summed
   * @return Sum of the digits contained in the string
   */
  public static long sumDigits(final String str) {
    long sum = 0;
    for (char c : str.toCharArray()) {
      // In the ASCII table, the numbers start at '0' and end at '9', meaning we can do this little
      // bit of math to figure out a char's digit value
      int value = 9 - ('9' - c);
      if (value < 0 || value > 9) {
        // The character was not a digit
        throw new NumberFormatException();
      }
      sum += value;
    }

    return sum;
  }
}
