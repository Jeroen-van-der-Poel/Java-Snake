 package View;

import Controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class DashBoard extends HBox {

	// constants
	private final static int buttonWidth = 100;
	private final static int buttonHeight = 40;
	private final static int dashWidth = 760;
	private final static int dashHeight = 70;

	// instance variabelen
	private ToggleButton pauseButton;
	private Button exitButton;
	private Slider speedSlider;
	private Label playtimeLabel;
	private BorderPane bp1;
	private Controller c;

	// constructor
	public DashBoard(Controller controller) {
		this.c = controller;
		this.setPrefSize(dashWidth, dashHeight);
		this.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		createMenu();
		this.setSpacing(20);
		this.setAlignment(Pos.CENTER);
	}

	public void createMenu() {
		pauseButton = new ToggleButton();
		pauseButton.setText("start");
		pauseButton.setPrefSize(buttonWidth, buttonHeight);
		pauseButton.setMaxSize(buttonWidth, buttonHeight);
		pauseButton.setOnAction(e -> {
			if (pauseButton.isSelected()) {
				pauseButton.setText("pause");
				c.startGame();
			} else {
				pauseButton.setText("start");
				c.pauseGame();
			}
		});

		exitButton = new Button();
		exitButton.setText("Exit");
		exitButton.setPrefSize(buttonWidth, buttonHeight);
		exitButton.setOnMouseClicked(e -> c.Exit());

		bp1 = new BorderPane();
		speedSlider = new Slider(1, 12, 1);
		speedSlider.setMinorTickCount(1);
		speedSlider.setMajorTickUnit(1);
		speedSlider.setShowTickMarks(true);
		speedSlider.setShowTickLabels(true);
		speedSlider.setMinWidth(120);
		speedSlider.setMinHeight(45);
		speedSlider.setDisable(true);
		speedSlider.setOpacity(255);
		speedSlider.valueProperty().bind(c.getSpeed());
		speedSlider.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
		bp1.setPrefSize(300, 70);
		bp1.setPadding(new Insets(0, 15, 0, 15));
		bp1.setCenter(speedSlider);

		playtimeLabel = new Label();
		playtimeLabel.setPrefSize(buttonWidth, buttonHeight);
		playtimeLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
		playtimeLabel.textProperty().bind(c.getTime());
		playtimeLabel.setAlignment(Pos.CENTER);

		getChildren().addAll(pauseButton, exitButton, bp1, playtimeLabel);
	}
}
