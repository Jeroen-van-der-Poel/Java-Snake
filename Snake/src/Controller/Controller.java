package Controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

import Model.Direction;
import Model.Game;
import View.DrawPane;
import View.GameOverScene;
import View.GameScene;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller {

	// constants
	private final static int minSpeed = 800;
	private final static int paneWidth = 760;
	private final static int paneHeight = 600;
	private final static int duration = 1;

	// instance variabelen
 	private Game game;
	private GameScene gs;
	private DrawPane dp;
	private Stage stage;
	private Timeline timer2;
	private Timeline timer;
	private SimpleStringProperty time;
	private SimpleIntegerProperty speed;
	private SimpleDateFormat dateformat;

	// constructor
	public Controller(Stage stage) {
		this.stage = stage;
		game = new Game();
		time = new SimpleStringProperty("00:00.000");
		dateformat = new SimpleDateFormat("mm:ss.SSS");
		speed = new SimpleIntegerProperty(1);

		stage.setTitle("PROG4 ASS Snake - Jeroen van der Poel");
		stage.setResizable(false);

		gs = new GameScene(this, paneWidth, paneHeight);
		stage.setScene(gs);

		createStopwatch();
		dp = gs.getDp();
		updateView();
		updateTimer();
		moveSnake();

		stage.show();
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
	}

	// interval van het bewegen
	// timer die alles update
	public void updateTimer() {
		timer = new Timeline(new KeyFrame(Duration.millis(minSpeed), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				game.move();
				checkDead();
				updateView();
			}
		}));
		// blijft altijd doortellen
		timer.setCycleCount(Timeline.INDEFINITE);
		// de snelheid van de slang verhogen
		timer.rateProperty().bind(speed.divide(1.3));
	}

	// stopwatch om de tijd bij te houden hoe lang je in game zit
	public void createStopwatch() {
		timer2 = new Timeline(new KeyFrame(Duration.millis(duration), new EventHandler<ActionEvent>() {
			private int milliseconds = 0;

			@Override
			public void handle(ActionEvent event) {
				// milliseconds ophogen
				milliseconds++;
				long currentTime;
				currentTime = milliseconds;
				// de currentTime in de time zetten
				time.set(dateformat.format(new Date(currentTime)));

				snakeUpdate(milliseconds);
			}
		}));
		// blijft altijd doortellen
		timer2.setCycleCount(Timeline.INDEFINITE);
	}

	// update drawpane view
	public void updateView() {
		dp.clear();
		dp.draw();
		dp.addSnake(game.getBodyX(), game.getBodyY(), game.getSnakeX(), game.getSnakeY());
		dp.addSpots(game.getSpotX(), game.getSpotY(), game.getMarker());
	}

	// update de snake om de zoveel tijd door de snelheid te verhogen en bodyparts
	// toe te voegen
	private void snakeUpdate(int milliseconds) {
		// verhoog de sneldheid en voeg bodyparts toe
		// % = 10 % 3 = 1, geeft de restwaarde terug
		if (milliseconds % 5000 == 0) {
			addSpeed(1);
			game.addBodyParts(3);
		}
		// random spot generenen om de zoveel tijd
		if (milliseconds % 1000 == 0) {
			Random r = new Random();
			// 1 op 3 kans dat er spot wordt geplaatst
			if (r.nextInt(3) < 1) {
				game.createSpot(paneWidth, paneHeight);
			}
		}
	}

	// de snake bewegen
	public void moveSnake() {
		stage.addEventFilter(KeyEvent.KEY_RELEASED, KeyEvent -> {
			switch (KeyEvent.getCode()) {
			case LEFT:
				game.setDirection(Direction.LEFT);
				break;
			case RIGHT:
				game.setDirection(Direction.RIGHT);
				break;
			case UP:
				game.setDirection(Direction.UP);
				break;
			case DOWN:
				game.setDirection(Direction.DOWN);
				break;
			default:
				break;
			}
		});
	}

	// kijken of de snake dood is
	public void checkDead() {
		if (game.dead(paneWidth, paneHeight, 0, 0) || game.checkOnSpot()) {
			GameOverScene gos = new GameOverScene(this);
			timer.stop();
			timer2.stop();
			stage.setScene(gos);
		}
	}

	// kopellen aan de pauze knop
	public void startGame() {
		timer2.play();
		timer.play();
	}

	// kopellen aan de start knop
	public void pauseGame() {
		timer2.stop();
		timer.stop();
	}

	// exit het spel
	public void Exit() {
		Platform.exit();
	}

	// timer
	public SimpleStringProperty getTime() {
		return time;
	}

	// snake
	public int getsnakeX() {
		return game.getSnakeX();
	}

	public int getSnakeY() {
		return game.getSnakeY();
	}

	// speed
	public SimpleIntegerProperty getSpeed() {
		return speed;
	}

	public void addSpeed(int value) {
		speed.setValue(speed.getValue() + 1);
	}
}
