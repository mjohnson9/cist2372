package johnson.michael.hotel;

import java.util.LinkedHashMap;
import javax.swing.JOptionPane;

/**
 * The class containing the program entrypoint and handling user interaction.
 */
@SuppressWarnings("NewClassNamingConvention")
public final class Main {
  /**
   * The room options available to the user. A {@see LinkedHashMap} is used to preserve ordering.
   */
  static final LinkedHashMap<String, RoomType> ROOM_OPTIONS = new LinkedHashMap<>();

  static {
    // Initialize ROOM_OPTIONS
    ROOM_OPTIONS.put("King Suite", RoomType.KING_SUITE_HOTEL_ROOMS);
    ROOM_OPTIONS.put("King", RoomType.KING_HOTEL_ROOMS);
    ROOM_OPTIONS.put("Double Suite", RoomType.DOUBLE_SUITE_HOTEL_ROOMS);
    ROOM_OPTIONS.put("Double", RoomType.DOUBLE_HOTEL_ROOMS);
  }

  /**
   * Don't allow Main to be instantiated.
   */
  private Main() {}

  /**
   * The entrypoint for the program.
   * @param args The command line arguments.
   */
  public static void main(final String[] args) {
    final Hotel hotel = new Hotel();

    final RoomType roomType = promptRoomType();
    final int numNights = promptNumberOfNights();
    bookRoom(hotel, roomType, numNights);
  }

  /**
   * Prompts the user for a room type. If the users chooses cancel, this method will exit the
   * program.
   * @return The {@see RoomType} that the user chose.
   */
  private static RoomType promptRoomType() {
    final Object[] options = ROOM_OPTIONS.keySet().toArray();
    final Object choice = JOptionPane.showInputDialog(null, "Choose a room type:", "Hotel",
        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

    if (choice == null) {
      // User chose cancel
      System.exit(0);
    }

    // noinspection SuspiciousMethodCalls
    return ROOM_OPTIONS.get(choice);
  }

  /**
   * Prompt the user for the number of nights they'll be staying. If the user chooses cancel, this
   * method will exit the program.
   * @return The number of nights that the user chose.
   */
  private static int promptNumberOfNights() {
    final Integer[] options = new Integer[365];
    for (int i = 0; i < options.length; i++) {
      options[i] = i + 1;
    }

    final Object objChoice =
        JOptionPane.showInputDialog(null, "How many nights will you be staying?", "Hotel",
            JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

    if (objChoice == null) {
      // User chose cancel
      System.exit(0);
    }

    // objChoice will always be castable to String because we input an array of Strings
    return (int) objChoice;
  }

  /**
   * Books a room and shows the user their total price.
   * @param hotel The {@see Hotel} instance to book a room from.
   * @param roomType The type of room to book.
   * @param numNights The number of nights that the user is staying.
   */
  private static void bookRoom(final Hotel hotel, final RoomType roomType, final int numNights) {
    final double nightlyCost;
    switch (roomType) {
      case DOUBLE_HOTEL_ROOMS:
        nightlyCost = hotel.bookDoubleHotelRoom();
        break;
      case DOUBLE_SUITE_HOTEL_ROOMS:
        nightlyCost = hotel.bookDoubleSuiteHotelRoom();
        break;
      case KING_HOTEL_ROOMS:
        nightlyCost = hotel.bookKingHotelRoom();
        break;
      case KING_SUITE_HOTEL_ROOMS:
        nightlyCost = hotel.bookKingSuiteHotelRoom();
        break;
      default:
        System.err.println("Unknown room type: \"" + roomType + "\"");
        System.exit(1);
        return; // Make the compiler happy about nightlyCost always being initialized
    }

    final double numNightsDouble = (double) numNights;
    final double totalCost = nightlyCost * numNightsDouble;

    JOptionPane.showMessageDialog(null,
        String.format(
            "Your stay would cost $%.2f per night, totalling $%.2f.", nightlyCost, totalCost),
        "Hotel", JOptionPane.INFORMATION_MESSAGE);
  }
}
