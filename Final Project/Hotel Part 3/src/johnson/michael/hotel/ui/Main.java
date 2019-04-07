package johnson.michael.hotel.ui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import johnson.michael.hotel.Hotel;
import johnson.michael.hotel.Reservation;

/**
 * The class containing the program entrypoint and handling user interaction.
 */
public final class Main {
  /**
   * Don't allow Main to be instantiated.
   */
  private Main() {}

  /**
   * The entrypoint for the program.
   * @param args The command line arguments.
   */
  public static void main(final String[] args) {
    final Hotel hotel = readHotel();

    final MainMenu mainMenu = new MainMenu(hotel);
    mainMenu.setLocationByPlatform(true);
    mainMenu.setVisible(true);
    mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    mainMenu.addWindowListener(new WindowListener() {
      @Override
      public void windowClosing(final WindowEvent e) {
        // The window is closing and the program exits when it's done closing. Save the hotel data.
        saveHotel(hotel);
      }

      @Override
      public void windowOpened(final WindowEvent e) {}

      @Override
      public void windowClosed(final WindowEvent e) {}

      @Override
      public void windowIconified(final WindowEvent e) {}

      @Override
      public void windowDeiconified(final WindowEvent e) {}

      @Override
      public void windowActivated(final WindowEvent e) {}

      @Override
      public void windowDeactivated(final WindowEvent e) {}
    });
  }

  /**
   * Attempts to read a {@see Hotel} from {@code hotelRooms.dat}.
   * @return The {@see Hotel} that was read from hotelRooms.dat or a new {@see Hotel} if there was
   *     an issue reading from hotelRooms.dat.
   */
  private static Hotel readHotel() {
    try (final FileInputStream file = new FileInputStream("hotelRooms.dat");
         final ObjectInputStream objectStream = new ObjectInputStream(file)) {
      final Hotel hotel = (Hotel) objectStream.readObject();

      final int reservationCounter = objectStream.readInt();
      Reservation.setStaticReservationNumber(reservationCounter);

      System.out.println("Successfully deserialized the hotel.");
      return hotel;
    } catch (final FileNotFoundException e) {
      // We won't even print a stack trace when the file isn't found; that's a normal first run
      // condition
    } catch (final IOException ex) {
      // There was an exception reading in the Hotel. We'll print the stack trace for debugging and
      // return a new hotel.
      System.err.println("IO error reading hotelRooms.dat:");
      ex.printStackTrace();
    } catch (final ClassNotFoundException ex) {
      // The object stream couldn't find a class while it was decoding. We'll print the stack trace
      // for debugging and return a new hotel.
      System.err.println("Object error reading from hotelRooms.dat:");
      ex.printStackTrace();
    }

    // Whatever the reason is for the failed decoding, return a new Hotel instance for the program
    // to use.
    return new Hotel();
  }

  /**
   * Saves a {@see Hotel} to {@code hotelRooms.dat}.
   * @param hotel The {@see Hotel} to save.
   */
  private static void saveHotel(final Hotel hotel) {
    try (final FileOutputStream file = new FileOutputStream("hotelRooms.dat");
         final ObjectOutputStream objectStream = new ObjectOutputStream(file)) {
      objectStream.writeObject(hotel);
      objectStream.writeInt(Reservation.getStaticReservationNumber());
    } catch (final IOException ex) {
      // There was an exception reading in the Hotel. We'll print the stack trace for debugging and
      // return a new hotel.
      System.err.println("IO error writing hotelRooms.dat:");
      ex.printStackTrace();
    }
  }
}
