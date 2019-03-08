package johnson.michael.temperature;

import javax.swing.JOptionPane;

public class Main {
  public static void main(String[] args) {
    final Temperature temperature = promptTemperature();
    JOptionPane.showMessageDialog(null,
        String.format("The temperature you entered:\nFahrenheit: %.1f\nCelsius: %.1f\nKelvin: %.1f",
            temperature.getFahrenheit(), temperature.getCelsius(), temperature.getKelvin()));
  }

  /**
   * promptTemperature prompts the user for a temperature
   *
   * @return The temperature input by the user
   */
  public static Temperature promptTemperature() {
    while (true) { // Continue to loop until we have a reason to exit the loop
      String answer =
          showPrompt("Enter a temperature followed by F (Fahrenheit), C (Celsius), or K (Kelvin).",
              "temperature");
      answer = answer.toLowerCase(); // lowercase for easier parsing
      final char endingLetter = answer.charAt(answer.length() - 1);
      answer = answer.substring(0, answer.length() - 1);
      try {
        // Attempt to parse the given input as an integer
        final double answerParsed = Double.parseDouble(answer);

        final Temperature temperature = new Temperature(0);
        switch (endingLetter) {
          case 'f':
            temperature.setFahrenheit(answerParsed);
            break;
          case 'c':
            temperature.setCelsius(answerParsed);
            break;
          case 'k':
            temperature.setKelvin(answerParsed);
            break;
          default:
            throw new NumberFormatException();
        }

        return temperature;
      } catch (NumberFormatException e) {
        // The given input was not an integer
        JOptionPane.showMessageDialog(null,
            "The given temperature was not a valid temperature.\nYour temperature must be a number followed by F, C, or K.\nFor example: 68.9F, 20.5C, or 293.5K");
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
