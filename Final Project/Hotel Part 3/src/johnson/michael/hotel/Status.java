package johnson.michael.hotel;

/**
 * Represents the status of a {@see Reservation}.
 */
public enum Status {
  /**
   * The {@see Guest} has not checked in yet.
   */
  INVALID,
  /**
   * The {@see Guest} has checked in.
   */
  VALID;

  @Override
  public String toString() {
    switch (this) {
      case INVALID:
        return "Invalid";
      case VALID:
        return "Valid";
      default:
        return "Unknown";
    }
  }

  // the default equals has the correct behavior for an enum
}
