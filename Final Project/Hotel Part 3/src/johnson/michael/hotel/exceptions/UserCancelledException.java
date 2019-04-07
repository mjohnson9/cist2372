package johnson.michael.hotel.exceptions;

/**
 * An {@see Exception} thrown whenever the user presses the cancel button on an input dialog.
 */
public class UserCancelledException extends Exception {
  /**
   * Constructs an instance of the class with the default message "User cancelled interaction".
   */
  public UserCancelledException() {
    super("User cancelled interaction");
  }
}
