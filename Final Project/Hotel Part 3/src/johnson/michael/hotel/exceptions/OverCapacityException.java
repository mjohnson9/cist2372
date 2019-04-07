package johnson.michael.hotel.exceptions;

/**
 * An {@see Exception} thrown whenever the guest's party is too large for the selected room.
 */
public class OverCapacityException extends Exception {
  /**
   * The maximum capacity of the room.
   */
  private final int roomCapacity;
  /**
   * The size of the party that tried to book the room.
   */
  private final int partySize;

  /**
   * Constructs an instance of this class with a formatted message.
   * @param roomCapacity The capacity of the room chosen.
   * @param partySize How many members of the party there were.
   */
  public OverCapacityException(final int roomCapacity, final int partySize) {
    super("The room is only large enough for " + roomCapacity + ", not " + partySize);

    this.roomCapacity = roomCapacity;
    this.partySize = partySize;
  }

  /**
   * Retrieves the capacity of the room that was being booked when this exception was thrown.
   * @return The capacity of the room.
   */
  public int getRoomCapacity() {
    return this.roomCapacity;
  }

  /**
   * Retrieves the party size attempting to book the room that caused this exception.
   * @return The party size.
   */
  public int getPartySize() {
    return this.partySize;
  }

  // the default Exception toString is adequate for this class

  // the default equals behavior is correct for this class
}
