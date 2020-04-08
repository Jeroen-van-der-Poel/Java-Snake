package Model;

import java.util.ArrayList;
import java.util.Random;

public class Game {

	// constants
	private final static int square = 40;

	// instance variabelen
	private ArrayList<Spot> spots;
	private Snake snake;

	// constructor
	public Game() {
		snake = new Snake();
		spots = new ArrayList<Spot>();
	}

	// kijken of de slang een spot raakt
	public boolean checkOnSpot() {
		for (int i = 0; i < spots.size(); i++) {
			if (snake.getPositionX() == spots.get(i).getPositionX()
					&& snake.getPositionY() == spots.get(i).getPositionY()) {
				if (Marker.MOUSE == spots.get(i).getMarker()) {
					snake.addBodyParts(5);
					spots.remove(i);
					return false;
				} else if (Marker.BEAR == spots.get(i).getMarker()) {
					snake.removeHalfBody();
					spots.remove(i);
					if (snake.isTooShort()) {
						return true;
					}
					return false;
				} else if (Marker.FIRE == spots.get(i).getMarker()) {
					return true;
				}
			}
		}
		return false;
	}

	// maak willekeurige spot
	public void createSpot(double maxWidth, double maxHeight) {
		// math.round om af te ronden
		int[] coordinates = generateSpotPlacement((int) Math.round(maxWidth), (int) Math.round(maxHeight));
		spots.add(new Spot(coordinates[0], coordinates[1], Marker.generateRandomMarker()));
	}

	// maak een willekeurige placement voor de spot
	public int[] generateSpotPlacement(int maxWidth, int maxHeight) {
		Random r = new Random();
		boolean cords = true;
		int[] coordinates = new int[2];

		while (cords) {
			// opvragen van een vakje
			int x = r.nextInt(maxWidth / square);
			int y = r.nextInt(maxHeight / square);
			// x/y veranderen naar de layout
			x *= square;
			y *= square;
			if (snake.checkPosition(x, y) && checkSpots(x, y)) {
				cords = false;
				coordinates[0] = x;
				coordinates[1] = y;
			}
		}
		return coordinates;
	}

	// kijken naar de spots die al zijn geplaats
	public boolean checkSpots(int x, int y) {
		for (Spot s : spots) {
			if (x == s.getPositionX() && y == s.getPositionY()) {
				return false;
			}
		}
		return true;
	}

	// x opvragen van een spot
	public ArrayList<Integer> getSpotX() {
		ArrayList<Integer> x = new ArrayList<Integer>();
		for (int i = 0; i < spots.size(); i++) {
			x.add(spots.get(i).getPositionX());
		}
		return x;
	}

	// y opvragen van een spot
	public ArrayList<Integer> getSpotY() {
		ArrayList<Integer> y = new ArrayList<Integer>();
		for (int i = 0; i < spots.size(); i++) {
			y.add(spots.get(i).getPositionY());
		}
		return y;
	}

	// marker opvragen
	// juiste plaatje voor elke spot krijgen
	public ArrayList<Marker> getMarker() {
		ArrayList<Marker> m = new ArrayList<Marker>();
		for (int i = 0; i < spots.size(); i++) {
			m.add(spots.get(i).getMarker());
		}
		return m;
	}

	// kijken of de snake dood is, methodes uit snake returnen
	public boolean dead(double maxWidth, double maxHeight, double minWidth, double minHeight) {
		return snake.hasHitWall(maxWidth, maxHeight, minWidth, minHeight) || snake.hasHitBody();
	}

	// bodyparts opvragnen
	public void addBodyParts(int amount) {
		snake.addBodyParts(amount);
	}

	// snake
	public void move() {
		snake.move();
	}

	public int getSnakeX() {
		return snake.getPositionX();
	}

	public int getSnakeY() {
		return snake.getPositionY();
	}

	public ArrayList<Integer> getBodyX() {
		return snake.getAllBodyX();
	}

	public ArrayList<Integer> getBodyY() {
		return snake.getAllBodyY();
	}

	public Direction getDirection() {
		return snake.getDirection();
	}

	public void setDirection(Direction direction) {
		snake.setDirection(direction);
	}

}
