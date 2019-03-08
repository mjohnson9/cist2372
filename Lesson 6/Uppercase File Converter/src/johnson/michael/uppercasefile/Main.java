package johnson.michael.uppercasefile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    // Create a Scanner on System.in for keyboard input
    final Scanner keyboard = new Scanner(System.in);

    // Retrieve a source file. Require that it exists.
    File source = promptForFile(keyboard, "Enter a filename to be turned into uppercase: ", true);
    // Retrieve a destination file. It doesn't matter if it exists.
    File destination =
        promptForFile(keyboard, "Enter a filename to store the uppercase results: ", false);

    while (true) { // Loop until success
      final ConvertResult convertResult = convertFile(source, destination);
      if (convertResult == ConvertResult.SUCCESS) { // The conversion was successful
        System.out.println(
            source.getPath() + " converted to uppercase and stored in " + destination.getPath());
        break; // Exit the loop to end the program
      } else if (convertResult == ConvertResult.FAILED_DESTINATION_NOT_FOUND) {
        // Ask for a new destination file
        destination =
            promptForFile(keyboard, "Enter a filename to store the uppercase results: ", false);
      } else if (convertResult == ConvertResult.FAILED_SOURCE_NOT_FOUND) {
        // Ask for a new source file
        source = promptForFile(keyboard, "Enter a filename to be turned into uppercase: ", true);
      }
    }
  }

  /**
   * Prompts the user for a file and optionally verifies that it exists.
   *
   * @param keyboard A Scanner on System.in to retrieve input from.
   * @param prompt A textual prompt to show the user before asking for their input.
   * @param requireExisting Whether or not to check for existence. If this is true, the function
   * will not return a File that doesn't exist. If this is false, the function does no checking.
   * @return A validated File.
   */
  public static File promptForFile(
      final Scanner keyboard, final String prompt, final boolean requireExisting) {
    boolean valid = false;
    File chosenFile = null;

    do {
      System.out.print(prompt);
      final String fileName = keyboard.nextLine();
      chosenFile = new File(fileName);

      if (requireExisting && !chosenFile.exists()) {
        System.out.println(
            fileName + " does not exist. Please enter the name of a file that exists.");
        System.out.println();
        valid = false;
        continue;
      }

      valid = true;
    } while (!valid);

    return chosenFile;
  }

  /**
   * Converts a source File to uppercase and stores it in a destination File.
   *
   * @param source The file to be converted.
   * @param destination The file where the uppercase results should be saved.
   * @return A result indicating the outcome of the conversion.
   */
  public static ConvertResult convertFile(final File source, final File destination) {
    try (final Scanner sourceReader = new Scanner(source)) {
      try (final PrintWriter destinationWriter = new PrintWriter(destination)) {
        while (sourceReader.hasNext()) {
          final String line = sourceReader.nextLine();
          destinationWriter.println(line.toUpperCase());
        }
      } catch (FileNotFoundException e) {
        System.out.println(
            "Unable to create the destination file. Does the directory it should be in exist? Please enter a new destination file.");
        System.out.println();
        return ConvertResult.FAILED_DESTINATION_NOT_FOUND;
      }
    } catch (FileNotFoundException e) {
      System.out.println(
          "The source file has been removed since entering it. Please enter a new source file.");
      System.out.println();
      return ConvertResult.FAILED_SOURCE_NOT_FOUND;
    }

    return ConvertResult.SUCCESS;
  }

  enum ConvertResult { SUCCESS, FAILED_DESTINATION_NOT_FOUND, FAILED_SOURCE_NOT_FOUND }
}
