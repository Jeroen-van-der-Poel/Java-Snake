package View;

import java.util.ArrayList;

import Model.Marker;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class DrawPane extends GridPane {

	// constants
	private final static int rows = 15;
	private final static int cols = 19;

	// instance variabelen
	private double paneWidth;
	private double paneHeight;
	private Canvas canvas;
	private GraphicsContext gc;
	private Image mouse;
	private Image bear;
	private Image fire;

	// constructor
	public DrawPane(double maxWidth, double maxHeight) {
		this.paneWidth = maxWidth;
		this.paneHeight = maxHeight;

		setMinSize(paneWidth, paneHeight);
		setMaxSize(paneWidth, paneHeight);

		mouse = new Image(getClass().getResource("/images/mouse.png").toString());
		bear = new Image(getClass().getResource("/images/bear.png").toString());
		fire = new Image(getClass().getResource("/images/fire.png").toString());

		canvas = new Canvas(paneWidth, paneHeight);
		gc = canvas.getGraphicsContext2D();
		getChildren().add(canvas);

		draw();
	}

	// tekenen van de vakjes op de canvas
	public void draw() {
		for (int x = 0; x < cols; x++) {
			for (int y = 0; y < rows; y++) {
				// x en y delen door 2, als dat 0 is of juist niet 0
				// % = een soort gedeeld door maar heeft als antwoord een restwaarde
				if ((x % 2) == 0 && (y % 2) == 0) {
					gc.setFill(Color.BLACK);
				} else if ((x % 2) != 0 && (y % 2) != 0) {
					gc.setFill(Color.valueOf("#333333"));
				} else {
					gc.setFill(Color.valueOf("#1a1a1a"));
				}
				gc.fillRect((x * (paneWidth / cols)), (y * (paneHeight / rows)), (paneWidth / cols),
						(paneHeight / rows));
			}
		}
	}

	// snake toevoegen op het canvas
	public void addSnake(ArrayList<Integer> x, ArrayList<Integer> y, int headX, int headY) {
		gc.setFill(Color.RED);
		gc.fillOval(headX, headY, (paneWidth / cols), (paneHeight / rows));

		gc.setFill(Color.ORANGE);
		for (int i = 0; i < x.size(); i++) {
			gc.fillOval(x.get(i), y.get(i), (paneWidth / cols), (paneHeight / rows));
		}
	}

	// spots toevoegen aan het canvas
	public void addSpots(ArrayList<Integer> x, ArrayList<Integer> y, ArrayList<Marker> m) {
		for (int i = 0; i < x.size(); i++) {
			if (m.get(i) == Marker.MOUSE) {
				gc.drawImage(mouse, x.get(i), y.get(i), (paneWidth / cols), (paneHeight / rows));
			} else if (m.get(i) == Marker.BEAR) {
				gc.drawImage(bear, x.get(i), y.get(i), (paneWidth / cols), (paneHeight / rows));
			} else if (m.get(i) == Marker.FIRE) {
				gc.drawImage(fire, x.get(i), y.get(i), (paneWidth / cols), (paneHeight / rows));
			}
		}
	}

	// canvas clearen
	public void clear() {
		gc.clearRect(0, 0, paneWidth, paneHeight);
	}

}
