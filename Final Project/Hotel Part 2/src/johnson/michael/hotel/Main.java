package johnson.michael.hotel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.swing.JOptionPane;
import johnson.michael.hotel.exceptions.NoVacancyException;
import johnson.michael.hotel.exceptions.OverCapacityException;
import johnson.michael.hotel.exceptions.UserCancelledException;

/**
 * The class containing the program entrypoint and handling user interaction.
 */
public final class Main {
  /**
   * The room options available to the user. A {@see LinkedHashMap} is used to preserve ordering.
   */
  static final LinkedHashMap<String, RoomType> ROOM_OPTIONS = new LinkedHashMap<>();
  /**
   * The list of main menu options.
   */
  static final String[] MAIN_MENU_OPTIONS = {
      "Create a reservation", "See all current reservations", "Check in", "Check out"};
  /**
   * The maximum size of a party.
   */
  private static final int MAX_PARTY_SIZE = 4;
  /**
   * The maximum length of a stay in days.
   */
  private static final int MAX_STAY_LENGTH = 31;

  static {
    // Initialize ROOM_OPTIONS
    ROOM_OPTIONS.put("Double", RoomType.DOUBLE_HOTEL_ROOMS);
    ROOM_OPTIONS.put("Double Suite", RoomType.DOUBLE_SUITE_HOTEL_ROOMS);
    ROOM_OPTIONS.put("King", RoomType.KING_HOTEL_ROOMS);
    ROOM_OPTIONS.put("King Suite", RoomType.KING_SUITE_HOTEL_ROOMS);
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

    mainMenu(hotel);
  }

  /**
   * Displays the main menu repeatedly until the program ends.
   * @param hotel The {@see Hotel} that should be used for the menus.
   */
  private static void mainMenu(final Hotel hotel) {
    while (true) {
      final String mainMenuChoice = promptMainMenu();

      try {
        switch (mainMenuChoice) {
          case "Create a reservation":
            promptCreateReservation(hotel);
            break;
          case "See all current reservations":
            displayCurrentReservations(hotel);
            break;
          case "Check in":
            promptCheckIn(hotel);
            break;
          case "Check out":
            promptCheckOut(hotel);
            break;
          default:
            throw new IllegalArgumentException("Unknown main menu choice: " + mainMenuChoice);
        }
      } catch (final UserCancelledException ex) {
        // User cancelled in a non-main menu, continue the loop and show the main menu again
      }
    }
  }

  /**
   * Prompts the user for a main menu choice. If the users chooses cancel, this method will exit the
   * program.
   * @return The main menu option that the user chose.
   */
  private static String promptMainMenu() {
    final Object objChoice = JOptionPane.showInputDialog(null, "What would you like to do?",
        "Hotel", JOptionPane.QUESTION_MESSAGE, null, MAIN_MENU_OPTIONS, MAIN_MENU_OPTIONS[0]);
    if (objChoice == null) {
      // User chose cancel
      System.exit(0);
    }

    return (String) objChoice;
  }

  /**
   * Prompts the user to create a reservation.
   * @param hotel The {@see Hotel} to operate on.
   * @throws UserCancelledException Thrown when the user presses the cancel button.
   */
  private static void promptCreateReservation(final BookHotelRoom hotel)
      throws UserCancelledException {
    final RoomType roomType = promptRoomType();
    final int numNights = promptNumberOfNights();
    final Guest guest = promptGuestInfo();
    bookRoom(hotel, roomType, guest, numNights);
  }

  /**
   * Displays the current reservation list or a message stating that there are none.
   * @param hotel The {@see Hotel} to get the reservations from.
   */
  private static void displayCurrentReservations(final Hotel hotel) {
    final ArrayList<Reservation> reservations = hotel.getReservations();

    if (reservations.isEmpty()) {
      JOptionPane.showMessageDialog(
          null, "There are currently no reservations.", "Hotel", JOptionPane.INFORMATION_MESSAGE);
    } else {
      final StringBuilder stringBuilder = new StringBuilder("Current reservations:\n");
      for (final Reservation reservation : hotel.getReservations()) {
        final Guest guest = reservation.getGuest();

        stringBuilder.append(reservation);
        stringBuilder.append(" - ");
        stringBuilder.append(guest.getFirstName());
        stringBuilder.append(' ');
        stringBuilder.append(guest.getLastName());
        stringBuilder.append(" - ");
        stringBuilder.append(reservation.getRoomType());
        stringBuilder.append(" - ");
        stringBuilder.append(reservation.getNumberOfNights());
        stringBuilder.append(" night");
        if (reservation.getNumberOfNights() != 1) {
          stringBuilder.append('s');
        }
        stringBuilder.append(" - ");
        stringBuilder.append(
            (reservation.getStatus() == Status.VALID) ? "Checked in" : "Not checked in yet");
        stringBuilder.append('\n');
      }

      final String builtString = stringBuilder.toString().trim();
      JOptionPane.showMessageDialog(null, builtString, "Hotel", JOptionPane.INFORMATION_MESSAGE);
    }
  }

  /**
   * Displays a check-in prompt.
   * @param hotel The {@see Hotel} to pull the reservations from.
   * @throws UserCancelledException Thrown when the user presses the cancel button.
   */
  private static void promptCheckIn(final Hotel hotel) throws UserCancelledException {
    final Reservation reservation =
        promptReservation(hotel, Status.INVALID, "There are no reservations ready for check-in.");

    if (reservation == null) {
      // There were no choices
      return;
    }

    reservation.checkIn();

    final Guest guest = reservation.getGuest();

    JOptionPane.showMessageDialog(null,
        "Hello, " + guest.getFirstName() + "!\nYou are now checked in under reservation #"
            + reservation.getReservationNumber() + ".");
  }

  /**
   * Displays a check-out prompt.
   * @param hotel The {@see Hotel} to pull the reservations from.
   * @throws UserCancelledException Thrown when the user presses the cancel button.
   */
  private static void promptCheckOut(final Hotel hotel) throws UserCancelledException {
    final Reservation reservation =
        promptReservation(hotel, Status.VALID, "There are no reservations ready for check-out.");
    if (reservation == null) {
      // There were no reservation options
      return;
    }

    reservation.checkOut();

    final Guest guest = reservation.getGuest();

    JOptionPane.showMessageDialog(null,
        String.format("Good bye, %s.%nYour total is $%.2f%nWe hope you enjoyed your stay.",
            guest.getFirstName(), reservation.getTotalCostForTheStay()));
  }

  /**
   * Prompts the user to choose a reservation.
   * @param hotel The {@see Hotel} to pull the list of reservations from.
   * @param desiredStatus The {@see Status} to filter for in the reservations.
   * @param emptyMessage The message to display when there are no reservations to choose from.
   * @return A {@see Reservation} or null if there were no reservations to choose from.
   * @throws UserCancelledException Thrown when the user presses the cancel button.
   */
  private static Reservation promptReservation(final Hotel hotel, final Status desiredStatus,
      final String emptyMessage) throws UserCancelledException {
    final LinkedHashMap<String, Reservation> reservationMap =
        createCheckInOutOptions(hotel, desiredStatus);

    if (reservationMap.isEmpty()) {
      // There are no reservations waiting for check in
      JOptionPane.showMessageDialog(null, emptyMessage, "Hotel", JOptionPane.INFORMATION_MESSAGE);
      return null;
    }

    final Object[] choices = reservationMap.keySet().toArray();
    final Object choice = JOptionPane.showInputDialog(null, "Choose your reservation:", "Hotel",
        JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);

    if (choice == null) {
      // User pressed cancel button
      throw new UserCancelledException();
    }

    return reservationMap.get(choice);
  }

  /**
   * Creates a {@see LinkedHashMap} with the keys being options for the user to choose from and the
   * values being reservations.
   * @param hotel The {@see Hotel} to use for creating the list of reservations.
   * @param desiredStatus The status to filter for when selecting reservations.
   * @return A {@see LinkedHashMap} where the keys are options for the user to choose from and the
   *     values are reservations. If there were no reservations that matched the {@code
   *     desiredStatus}, this map will be empty.
   */
  private static LinkedHashMap<String, Reservation> createCheckInOutOptions(
      final Hotel hotel, final Status desiredStatus) {
    final ArrayList<Reservation> reservations = hotel.getReservations();

    // Create a LinkedHashMap with a capacity of the number of reservations
    final LinkedHashMap<String, Reservation> reservationMap =
        new LinkedHashMap<>(reservations.size());

    // Iterate through all of the reservations
    for (final Reservation reservation : reservations) {
      if (reservation.getStatus() != desiredStatus) {
        // The guest has already checked in
        continue;
      }

      final Guest guest = reservation.getGuest();

      final String option = reservation + " - " + guest.getFirstName() + " " + guest.getLastName();

      reservationMap.put(option, reservation);
    }

    return reservationMap;
  }

  /**
   * Prompts the user for a room type. If the users chooses cancel, this method will exit the
   * program.
   * @return The {@see RoomType} that the user chose or null if the user cancels.
   */
  private static RoomType promptRoomType() {
    final Object[] options = ROOM_OPTIONS.keySet().toArray();
    final Object choice = JOptionPane.showInputDialog(null, "Choose a room type:", "Hotel",
        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

    if (choice == null) {
      // User chose cancel. Tell the calling function.
      return null;
    }

    return ROOM_OPTIONS.get(choice);
  }

  /**
   * Prompt the user for the number of nights they'll be staying. If the user chooses cancel, this
   * method will exit the program.
   * @return The number of nights that the user chose.
   * @throws UserCancelledException Thrown when the user presses the cancel button.
   */
  private static int promptNumberOfNights() throws UserCancelledException {
    return promptInt(1, MAX_STAY_LENGTH, "How many nights will you be staying?");
  }

  /**
   * Prompts the user for all of a {@see Guest}'s information.
   * @return A fully initialized {@see Guest}.
   * @throws UserCancelledException Thrown when the user presses the cancel button.
   */
  private static Guest promptGuestInfo() throws UserCancelledException {
    final Guest guest = new Guest();
    promptGuestName(guest);
    promptAddress(guest);
    promptPartySize(guest);

    return guest;
  }

  /**
   * Prompts a guest for their first and last name.
   * @param guest The {@see Guest} to operate on.
   * @throws UserCancelledException Thrown when the user presses the cancel button.
   */
  private static void promptGuestName(final Guest guest) throws UserCancelledException {
    final String firstName =
        promptString("Please enter your first name:", false, "Your first name cannot be blank.");
    guest.setFirstName(firstName);

    final String lastName =
        promptString("Please enter your last name:", false, "Your last name cannot be blank.");
    guest.setLastName(lastName);
  }

  /**
   * Prompts a guest for their full address.
   * @param guest The {@see Guest} to operate on.
   * @throws UserCancelledException Thrown when the user presses the cancel button.
   */
  private static void promptAddress(final Guest guest) throws UserCancelledException {
    final String streetAddress = promptString(
        "Please enter your street address:", false, "Your street address cannot be blank.");
    final String city =
        promptString("Please enter your city:", false, "Your city cannot be blank.");
    final String state =
        promptString("Please enter your state:", false, "Your state cannot be blank.");
    String zip;
    while (true) { // Loop until we get a valid ZIP
      zip = promptString(
          "Please enter your 5-digit ZIP code:", false, "Your ZIP code cannot be blank.");

      if (zip.length() != 5) {
        // The ZIP is not 5 characters long
        JOptionPane.showMessageDialog(
            null, "You must enter a 5-digit ZIP code.", "Hotel", JOptionPane.ERROR_MESSAGE);
        continue;
      }

      try {
        Integer.parseInt(zip); // Check if it's a number
      } catch (final NumberFormatException ex) {
        // The ZIP is not just numbers
        JOptionPane.showMessageDialog(null, "Your ZIP code may only consist of numbers.", "Hotel",
            JOptionPane.ERROR_MESSAGE, null);
        continue;
      }

      // It's a valid ZIP
      break;
    }

    guest.setAddress(streetAddress + "\n" + city + ", " + state + "  " + zip);
  }

  /**
   * Prompts a guest for their party size. Only allows up to {@code MAX_PARTY_SIZE}.
   * @param guest The {@see Guest} to operate on.
   * @throws UserCancelledException Thrown when the user presses the cancel button.
   */
  private static void promptPartySize(final Guest guest) throws UserCancelledException {
    final int numAdults = promptInt(1, MAX_PARTY_SIZE, "How many adults are in your party?");
    guest.setNumberAdultsInParty(numAdults);

    final int remainingSlots = MAX_PARTY_SIZE - numAdults;
    int numChildren = 0;
    if (remainingSlots > 0) {
      numChildren = promptInt(0, remainingSlots, "How many children are in your party?");
    }
    guest.setNumberChildrenInParty(numChildren);
  }

  /**
   * Books a room and shows the user their total price.
   * @param hotel The {@see Hotel} instance to book a room from.
   * @param roomType The type of room to book.
   * @param guest The {@see Guest} to book the room for.
   * @param numNights The number of nights that the user is staying.
   */
  private static void bookRoom(
      final BookHotelRoom hotel, final RoomType roomType, final Guest guest, final int numNights) {
    try {
      final Reservation reservation = hotel.bookRoom(roomType, guest, numNights);

      JOptionPane.showMessageDialog(null,
          String.format(
              "Your reservation number is %d%nYour stay will cost $%.2f.%nPlease check in when you're ready.",
              reservation.getReservationNumber(), reservation.getTotalCostForTheStay()),
          "Hotel", JOptionPane.INFORMATION_MESSAGE);
    } catch (final NoVacancyException ex) {
      JOptionPane.showMessageDialog(null,
          "The hotel does not have any " + roomType + " rooms available.", "Hotel",
          JOptionPane.ERROR_MESSAGE);
    } catch (final OverCapacityException ex) {
      JOptionPane.showMessageDialog(null, "Your party is too large for a " + roomType + " room.",
          "Hotel", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * Prompts the user to enter a string. Optionally validates that the string isn't empty.
   * @param message The message to display to the user.
   * @param allowEmpty Whether or not to allow an empty string.
   * @param emptyErrorMessage The error message to display when the user enters an empty string,
   *     only if {@code allowEmpty} is false.
   * @return The String the user entered. If {@code allowEmpty} is true, this can be an empty
   *     string. It is always trimmed of excess whitespace.
   * @throws UserCancelledException Thrown when the user presses the cancel button.
   */
  private static String promptString(final String message, final boolean allowEmpty,
      final String emptyErrorMessage) throws UserCancelledException {
    // Loop until we get a satisfactory answer
    while (true) {
      String choice =
          JOptionPane.showInputDialog(null, message, "Hotel", JOptionPane.QUESTION_MESSAGE);
      if (choice == null) {
        throw new UserCancelledException();
      }

      choice = choice.trim();

      if (!allowEmpty && choice.isEmpty()) {
        JOptionPane.showMessageDialog(null, emptyErrorMessage, "Hotel", JOptionPane.ERROR_MESSAGE);
        continue;
      }

      return choice;
    }
  }

  /**
   * Prompts the user to select an integer from a list
   * @param min The minimum integer to display (inclusive).
   * @param max The maximum integer to display (inclusive).
   * @param message The message to show to the user.
   * @return The integer chosen by the user.
   * @throws UserCancelledException Thrown when the user presses the cancel button.
   */
  private static int promptInt(final int min, final int max, final String message)
      throws UserCancelledException {
    if (max < min) {
      throw new IllegalArgumentException("max (" + max + ") cannot be less than min (" + min + ")");
    }

    final Integer[] choices = new Integer[(max - min) + 1];
    for (int i = 0; i < choices.length; i++) {
      choices[i] = min + i;
    }

    final Object objChoice = JOptionPane.showInputDialog(
        null, message, "Hotel", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
    if (objChoice == null) {
      throw new UserCancelledException();
    }

    return (int) objChoice;
  }
}
