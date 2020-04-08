package View;

import Controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameOverScene extends Scene{

	//constants
	private final static int paneWidth = 760;
	private final static int paneHeight = 600;
	private final static int dashHeight = 70;
	
	//Instance variabelen
	private BorderPane bp;
	private VBox pane;
	private VBox pane2;
	private Controller c;
	private Label text;
	private Label time;
	
	//contructor
	public GameOverScene(Controller c) {
		super(new Pane(), paneWidth, 670);
		this.c = c;
		createLayout();
		
		setRoot(bp);
	}
	
	public void createLayout() {
		pane = new VBox();
		pane.setPrefSize(paneWidth, paneHeight);
		pane.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		
		text = new Label();
		text.setText("GAME OVER!");
		text.setFont(new Font("Arial", 40));
		text.setStyle("-fx-text-fill: black");
		
		time = new Label();
		time.textProperty().bind(c.getTime());
		time.setFont(new Font("Arial", 40));
		time.setStyle("-fx-text-fill: white");
		time.setAlignment(Pos.CENTER);
		
		pane.getChildren().addAll(text, time);
		pane.setAlignment(Pos.CENTER);
		
		pane2 = new VBox();
		pane2.setPrefSize(paneWidth, dashHeight);
		pane2.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		
		bp = new BorderPane();
		bp.setCenter(pane);
		bp.setBottom(pane2);
	}
}
