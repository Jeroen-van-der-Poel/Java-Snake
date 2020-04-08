package View;

import Controller.Controller;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {

	// constants
	private final static int paneWidth = 760;

	// instance variabelen
	private BorderPane bp;
	private DashBoard db;
	private DrawPane dp;

	private Controller c;

	// constructor
	public GameScene(Controller c, double maxWidth, double maxHeight) {
		super(new Pane(), paneWidth, 670);
		this.c = c;

		bp = new BorderPane();
		dp = new DrawPane(maxWidth, maxHeight);
		db = new DashBoard(c);

		createMenu();
		setRoot(bp);
	}

	public void createMenu() {
		bp.setCenter(dp);
		bp.setBottom(db);
	}

	public DrawPane getDp() {
		return dp;
	}

}
