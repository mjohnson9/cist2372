package johnson.michael.hotel.exceptions;

/**
 * An {@see Exception} thrown whenever there are no vacancies for a given room type in the hotel.
 */
public class NoVacancyException extends Exception {
  /**
   * Constructs an instance of this class with a pre-specified exception message of "The hotel has
   * no rooms left".
   */
  public NoVacancyException() {
    super("The hotel has no rooms left");
  }

  // the default Exception toString is adequate for this class

  // the default equals behavior is correct for this class
}
