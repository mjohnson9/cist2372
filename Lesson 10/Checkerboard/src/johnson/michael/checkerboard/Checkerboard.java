package johnson.michael.checkerboard;

import java.util.Optional;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Checkerboard displays a checkerboard whose cells are alternating colors. It allows the user to
 * input any size they desire for the checkerboard.
 */
public class Checkerboard extends Application {
  /**
   * The initial size of the checkerboard.
   */
  private static final int STARTING_SIZE = 5;

  /**
   * The size (in pixels) of each checkerboard square.
   */
  private static final int SQUARE_SIZE = 50;

  public static void main(final String[] args) {
    launch(args);
  }

  @Override
  public void start(final Stage primaryStage) {
    primaryStage.setTitle("Checkerboard");

    // Start by showing a default checkerboard
    Scene checkerboard = this.createCheckerboardScene(STARTING_SIZE);
    primaryStage.setScene(checkerboard);

    primaryStage.show();

    // Request a new size from the user
    final int n = this.requestN();
    if (n == STARTING_SIZE) {
      // We already have a checkerboard that size; don't bother changing the scene
      return;
    }

    // Create a new checkerboard from the user's specified size.
    checkerboard = this.createCheckerboardScene(n);
    primaryStage.setScene(checkerboard); // Make the new checkerboard the displayed scene.
  }

  /**
   * Requests an N value from the user. Returns STARTING_SIZE if the user cancels.
   * @return The size specified by the user or {@code STARTING_SIZE} if the user cancels the dialog.
   */
  private int requestN() {
    final TextInputDialog dialog = new TextInputDialog("5");
    dialog.initStyle(StageStyle.UTILITY);
    dialog.setTitle("Checkerboard Size");
    dialog.setHeaderText("Set the size of the checkerboard");
    dialog.setContentText("n =");

    while (true) {
      final Optional<String> choice = dialog.showAndWait();
      if (!choice.isPresent()) {
        // The user cancelled
        return STARTING_SIZE;
      }

      final int n;
      try {
        n = Integer.parseInt(choice.get());
      } catch (final NumberFormatException ex) {
        dialog.setHeaderText(
            "Set the size of the checkerboard\nERROR: It must be a positive integer.");
        continue;
      }
      if (n <= 0) {
        dialog.setHeaderText(
            "Set the size of the checkerboard\nERROR: It must be a positive integer.");
        continue;
      }

      return n;
    }
  }

  /**
   * Creates an n*n checkerboard with alternating colors.
   * @param n The width and height of the checkerboard to be created.
   * @return A Scene with the checkerboard built on it.
   */
  private Scene createCheckerboardScene(final int n) {
    final GridPane pane = new GridPane();
    pane.setPrefSize(SQUARE_SIZE * n, SQUARE_SIZE * n);

    for (int x = 0; x < n; x++) {
      for (int y = 0; y < n; y++) {
        final Rectangle square = new Rectangle(SQUARE_SIZE, SQUARE_SIZE);

        if (((x + y) % 2) == 0) {
          // Even numbered square
          square.setFill(Color.LIGHTGRAY);
        } else {
          // Odd numbered square
          square.setFill(Color.DARKGRAY);
        }

        pane.add(square, x, y);
      }
    }

    return new Scene(pane);
  }
}
