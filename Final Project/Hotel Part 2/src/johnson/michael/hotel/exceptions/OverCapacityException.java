package johnson.michael.hotel.exceptions;

/**
 * An {@see Exception} thrown whenever the guest's party is too large for the selected room.
 */
public class OverCapacityException extends Exception {
  /**
   * Constructs an instance of this class with a formatted message.
   * @param roomCapacity The capacity of the room chosen.
   * @param occupants How many members of the party there were.
   */
  public OverCapacityException(final int roomCapacity, final int occupants) {
    super("The room is only large enough for " + roomCapacity + ", not " + occupants);
  }

  // the default Exception toString is adequate for this class

  // the default equals behavior is correct for this class
}
