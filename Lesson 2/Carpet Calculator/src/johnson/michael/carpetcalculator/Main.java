package johnson.michael.carpetcalculator;

public class Main {

  public static void main(String[] args) {
    final RoomCarpet room = UI.promptCarpet();
    UI.displayRoomDetails(room);
  }
}
