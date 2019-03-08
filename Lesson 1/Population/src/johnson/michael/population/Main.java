package johnson.michael.population;

import javax.swing.JOptionPane;

public class Main {
  public static void main(String[] args) {
    final int numOrganisms = promptNumberOfOrganisms();
    final double dailyIncrease = promptDailyIncrease();
    final int numDays = promptNumberOfDays();

    final String report = preparePopulationReport(numOrganisms, numDays, dailyIncrease);
    JOptionPane.showMessageDialog(null, report);
  }

  /**
   * preparePopulationReport creates a line-separated daily report of organism growth
   *
   * @param numOrganisms The number of organisms to start with
   * @param numDays The number of days the organisms will multiply
   * @param dailyIncrease The daily percentage increase in organisms
   * @return A report for each day of organism growth
   */
  public static String preparePopulationReport(
      final int numOrganisms, final int numDays, final double dailyIncrease) {
    StringBuilder report = new StringBuilder();
    double population =
        numOrganisms; // We use a double to allow slow growth that takes multiple days
    for (int i = 0; i < numDays; i++) {
      if (i > 0) {
        report.append('\n');
      }
      report.append(String.format("Day %d: %.0f", (i + 1), Math.floor(population)));

      population *= 1d + dailyIncrease;
    }
    return report.toString();
  }

  /**
   * promptNumberOfOrganisms prompts the user for a validated number of organisms
   *
   * @return A number of organisms within business rules
   */
  public static int promptNumberOfOrganisms() {
    return promptInt(
        "How many organisms are you starting with?", "number of organisms", 2, Integer.MAX_VALUE);
  }

  /**
   * promptNumberOfDays prompts the user for a validated number of days
   *
   * @return A number of days within business rules
   */
  public static int promptNumberOfDays() {
    return promptInt(
        "How many days will the organisms multiply?", "number of days", 1, Integer.MAX_VALUE);
  }

  /**
   * promptDailyIncrease prompts the user for a validated percentage increase
   *
   * @return The daily population growth as a percentage
   */
  public static double promptDailyIncrease() {
    while (true) { // Continue to loop until we have a reason to exit the loop
      final String answer =
          JOptionPane.showInputDialog("What is the daily percentage increase of the organisms?");
      if (answer == null) { // The user cancelled the dialog
        System.exit(0); // Exit the program
      }
      if (answer.trim().equals("")) {
        JOptionPane.showMessageDialog(null, "The daily percentage increase cannot be blank.");
        continue; // Repeat the loop
      }
      try {
        // Attempt to parse the given input as an integer
        final double answerParsed = Double.parseDouble(answer);
        if (!(answerParsed > 0)) {
          JOptionPane.showMessageDialog(
              null, "The daily percentage increase must be greater than 0.");
          continue; // Repeat the loop
        }
        return answerParsed / 100d; // Divide by 100 to convert to a usable percentage
      } catch (NumberFormatException e) {
        // The given input was not an integer
        JOptionPane.showMessageDialog(
            null, "The given percentage increase was not a valid number.");
        continue; // Repeat the loop
      }
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
  public static int promptInt(String prompt, String name, int minimum, int maximum) {
    while (true) { // Continue to loop until we have a reason to exit the loop
      final String answer = JOptionPane.showInputDialog(prompt);
      if (answer == null) { // The user cancelled the dialog
        System.exit(0); // Exit the program
      }
      if (answer.trim().equals("")) {
        JOptionPane.showMessageDialog(null, "The " + name + " cannot be blank.");
        continue; // Repeat the loop
      }
      try {
        // Attempt to parse the given input as an integer
        final int answerParsed = Integer.parseInt(answer);
        if (answerParsed < minimum) {
          JOptionPane.showMessageDialog(
              null, String.format("The %s cannot be less than %d.", name, minimum));
          continue; // Repeat the loop
        } else if (answerParsed > maximum) {
          JOptionPane.showMessageDialog(
              null, String.format("The %s cannot be more than %d.", name, maximum));
        }
        return answerParsed;
      } catch (NumberFormatException e) {
        // The given input was not an integer
        JOptionPane.showMessageDialog(null, "The given " + name + " was not a valid integer.");
        continue; // Repeat the loop
      }
    }
  }
}
