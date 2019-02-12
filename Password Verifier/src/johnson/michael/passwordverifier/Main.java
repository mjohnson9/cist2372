package johnson.michael.passwordverifier;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Main {

  public static void main(String[] args) {
    while (true) { // Loop until the user pushes the cancel button in the password prompt
      final String password = promptPassword();

      String[] errors = verifyPassword(password);

      if (errors.length == 0) {
        JOptionPane.showMessageDialog(null, "Your password is valid.");
        continue;
      }

      JOptionPane.showMessageDialog(null,
          "Your password contains the following errors:\n" + String.join("\n", errors));
    }
  }

  /**
   * Prompt the user for a password
   *
   * @return A non-empty string
   */
  public static String promptPassword() {
    do {
      String password = JOptionPane.showInputDialog("Enter your password to be verified:");

      if (password == null) {
        // The user cancelled the option pane
        System.exit(0);
      }

      if (password.isEmpty()) {
        continue;
      }

      return password;
    } while (true);
  }

  /**
   * Verify that a password meets the rules
   *
   * @param password The password to be checked
   * @return An array of errors indicating why the password doesn't meet the requirements
   */
  public static String[] verifyPassword(final String password) {
    List<String> errors = new ArrayList<>();

    if (password.length() < 6) {
      errors.add("The password should be six characters long.");
    }

    if (!hasLetter(password)) {
      errors.add(
          "The password should contain at least one uppercase and at least one lowercase letter.");
    } else {
      if (!hasCase(password, true)) {
        errors.add("The password should contain at least one lowercase letter.");
      }
      if (!hasCase(password, false)) {
        errors.add("The password should contain at least one uppercase letter.");
      }
    }

    if (!hasDigit(password)) {
      errors.add("The password should have at least one digit.");
    }

    String[] errorsArray = new String[errors.size()];
    errorsArray = errors.toArray(errorsArray);

    return errorsArray;
  }

  /**
   * Checks if a string contains any digits
   *
   * @param password The string to be checked
   * @return true if there are any digits in the string; false otherwise
   */
  public static boolean hasDigit(final String password) {
    for (char c : password.toCharArray()) {
      if (!Character.isDigit(c)) {
        // Not a letter; ignore it
        continue;
      }

      return true;
    }

    return false;
  }

  /**
   * Checks if a string contains any letters
   *
   * @param password The string to be checked
   * @return true if there are letters in the string; false otherwise
   */
  public static boolean hasLetter(final String password) {
    for (char c : password.toCharArray()) {
      if (!Character.isAlphabetic(c)) {
        // Not a letter; ignore it
        continue;
      }

      return true;
    }

    return false;
  }

  /**
   * Checks if a string contains any characters of a certain case
   *
   * @param password The string to be checked
   * @param lowercase true if {@code password} is to be checked for lowercase characters; false
   * otherwise
   * @return true if {@code password} contains characters of the specified case; false otherwise
   */
  public static boolean hasCase(final String password, boolean lowercase) {
    for (char c : password.toCharArray()) {
      if (!Character.isAlphabetic(c)) {
        // This is not a letter; ignore it
        continue;
      }

      if (lowercase) {
        if (!Character.isLowerCase(c)) {
          // This character should be lowercase and isn't; continue searching
          continue;
        }
      } else {
        if (!Character.isUpperCase(c)) {
          // This character should be uppercase and isn't; continue searching
          continue;
        }
      }

      return true;
    }

    return false;
  }
}
