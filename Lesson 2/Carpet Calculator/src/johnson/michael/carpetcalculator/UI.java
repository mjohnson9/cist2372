package johnson.michael.carpetcalculator;

import javax.swing.JOptionPane;

public class UI {

  private UI() {
  } // Don't allow instances of this class to be created

  public static void displayRoomDetails(final RoomCarpet room) {
    final RoomDimension dimensions = room.getDimensions();
    final String message = String.format(
        "To carpet a %,.0fftx%,.0fft room at a rate of $%,.2f per square foot, it would cost $%,.2f.",
        dimensions.getWidth(), dimensions.getLength(), room.getCost(), room.getTotalCost());
    JOptionPane.showMessageDialog(null, message);
  }

  public static RoomCarpet promptCarpet() {
    final RoomDimension dimensions = promptRoom("room to be carpeted");

    final double cost = promptDouble(
        "Please enter the cost per square foot of the carpet for the room.", Double.MIN_VALUE,
        Double.POSITIVE_INFINITY);

    return new RoomCarpet(dimensions, cost);
  }

  public static RoomDimension promptRoom(final String object) {
    final String promptBegin;
    final String promptEnd;
    if (!object.trim().isEmpty()) {
      promptBegin = "Please enter the ";
      promptEnd = " of the " + object + ".";
    } else {
      promptBegin = "Please enter the ";
      promptEnd = ".";
    }

    final double length = promptDouble(promptBegin + "width" + promptEnd, Double.MIN_VALUE,
        Double.POSITIVE_INFINITY);
    final double width = promptDouble(promptBegin + "length" + promptEnd, Double.MIN_VALUE,
        Double.POSITIVE_INFINITY);

    return new RoomDimension(length, width);
  }

  public static double promptDouble(final String prompt, final double min, final double max) {
    if (min == Double.POSITIVE_INFINITY) {
      throw new IllegalArgumentException("min");
    }
    if (max == Double.NEGATIVE_INFINITY) {
      throw new IllegalArgumentException("max");
    }

    final String detailPrompt;
    if (min == Double.MIN_VALUE
        && max == Double.POSITIVE_INFINITY) { // This is a special case that exists in our code
      detailPrompt = "\nValid answers are decimal numbers greater than 0.";
    } else if (min != Double.NEGATIVE_INFINITY && max != Double.POSITIVE_INFINITY) {
      detailPrompt = "\nValid answers are decimal numbers between " + min + " and " + max + ".";
    } else {
      StringBuilder builder = new StringBuilder("\nValid answers are decimal numbers");
      if (min == Double.NEGATIVE_INFINITY && max == Double.POSITIVE_INFINITY) {
        builder.append('.');
      } else if (min != Double.NEGATIVE_INFINITY) {
        builder.append(" greater than ");
        builder.append(min);
        builder.append('.');
      } else { // Max is not positive infinity
        builder.append(" less than ");
        builder.append(max);
        builder.append('.');
      }

      detailPrompt = builder.toString();
    }

    boolean valid = true;
    double result = 0d;
    String invalidMessage = "";

    do {
      String response = JOptionPane.showInputDialog(invalidMessage + prompt + detailPrompt);
      if (response == null) { // The cancel button was pushed
        System.exit(0);
      }

      try {
        result = Double.parseDouble(response);
      } catch (NumberFormatException e) {
        invalidMessage = "\"" + response + "\" is not a number. You must enter a number.\n";
        valid = false;
        continue;
      }

      valid = false;
      if (result < min) {
        if (min == Double.MIN_VALUE) {
          invalidMessage = result + " is not greater than zero.\n";
        } else {
          invalidMessage = result + " is less than the lower limit of " + min + ".\n";
        }
      } else if (result > max) {
        invalidMessage = result + " is greater than the upper limit of " + max + ".\n";
      } else {
        valid = true;
      }
    } while (!valid);

    return result;
  }
}
