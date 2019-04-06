package johnson.michael.radiobuttons;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * RadioButtons is an application that displays two radio buttons, one labeled "White" and the other
 * labeled "Yellow". When the a radio button is selected, the background of the primary stage
 * changes to match the color name.
 */
public class RadioButtons extends Application {
  /**
   * The default width and height of the scene.
   */
  private static final int DEFAULT_SIZE = 300;

  public static void main(final String[] args) {
    launch(args);
  }

  @Override
  public void start(final Stage primaryStage) {
    final HBox box = new HBox();
    box.setAlignment(Pos.TOP_CENTER); // Put the HBox in the top center of the stage
    box.setPrefSize(DEFAULT_SIZE, DEFAULT_SIZE); // Set the preferred size to our default size
    box.setSpacing(15); // Set the spacing between children to 15px
    box.setPadding(new Insets(15, 0, 0, 0)); // Set the padding on top to 15px

    final ToggleGroup group = new ToggleGroup();

    final RadioButton whiteButton = new RadioButton("White");
    whiteButton.setToggleGroup(group);
    whiteButton.setOnAction(event -> this.setBackground(box, Color.WHITE));
    // Set the initial background color and radio button selection
    whiteButton.setSelected(true);
    this.setBackground(box, Color.WHITE);

    final RadioButton yellowButton = new RadioButton("Yellow");
    yellowButton.setToggleGroup(group);
    yellowButton.setOnAction(event -> this.setBackground(box, Color.YELLOW));

    // Add both of the radio buttons as children
    box.getChildren().addAll(whiteButton, yellowButton);

    primaryStage.setScene(new Scene(box));
    primaryStage.show();
  }

  /**
   * Changes the background color of a {@see Pane}.
   * @param pane The Pane to change the background color of.
   * @param color The color to change the background to.
   */
  private void setBackground(final Pane pane, final Color color) {
    pane.setBackground(new Background(new BackgroundFill(color, null, null)));
  }
}
