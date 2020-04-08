package Model;

import java.util.ArrayList;

public class Snake {
	
	//constants
	private final static int square = 40;

	// Instance variabelen
	private int positionX;
	private int positionY;
	private Direction direction;
	private ArrayList<BodyPart> body;

	// constructor
	public Snake() {
		direction = Direction.RIGHT;
		body = new ArrayList<BodyPart>();
		positionX = 280;
		positionY = 120;
		createStarterBody();
	}

	// maakt de starter body van de snake aan
	private void createStarterBody() {
		int bodyX = getPositionX() - square;
		for (int c = 0; c < 4; c++) {
			body.add(new BodyPart(bodyX, getPositionY()));
			bodyX -= square;
		}
	}

	// positie x van elke bodypart opvragen
	public ArrayList<Integer> getAllBodyX() {
		ArrayList<Integer> x = new ArrayList<Integer>();
		for (int i = 0; i < body.size(); i++) {
			x.add(body.get(i).getPositionX());
		}
		return x;
	}

	// positie y van elke bodypart opvragen
	public ArrayList<Integer> getAllBodyY() {
		ArrayList<Integer> y = new ArrayList<Integer>();
		for (int i = 0; i < body.size(); i++) {
			y.add(body.get(i).getPositionY());
		}
		return y;
	}

	// slang gaat bewegen
	public void move() {
		BodyPart part = body.get(body.size() - 1);
		part.setPositionX(positionX);
		part.setPositionY(positionY);

		body.remove(part);
		orderArrayList(part);

		if (direction == Direction.RIGHT) {
			positionX += square;
		} else if (direction == Direction.LEFT) {
			positionX -= square;
		} else if (direction == Direction.UP) {
			positionY -= square;
		} else if (direction == Direction.DOWN) {
			positionY += square;
		}
	}

	// arraylist ordenen
	// verplaatst de laatste in de lijst naar voren
	private void orderArrayList(BodyPart p) {
		ArrayList<BodyPart> oldList = body;
		body = new ArrayList<BodyPart>();
		body.add(p);
		body.addAll(oldList);
	}

	// kijken of de snake dood is door de muur
	public boolean hasHitWall(double maxWidth, double maxHeight, double minWidth, double minHeight) {
		if (getPositionX() >= maxWidth || getPositionY() >= maxHeight || getPositionX() < minWidth
				|| getPositionY() < minHeight) {
			return true;
		}
		return false;
	}

	// kijken of de snake dood is door zichzelf
	public boolean hasHitBody() {
		for (int i = 0; i < body.size(); i++) {
			if (getPositionX() == body.get(i).getPositionX() && getPositionY() == body.get(i).getPositionY()) {
				return true;
			}
		}
		return false;
	}

	// naar de positie kijken voordat je iets plaatst
	public boolean checkPosition(int x, int y) {
		if (x == getPositionX() && y == getPositionY()) {
			return false;
		}
		for (BodyPart p : body) {
			if (x == p.getPositionX() && y == p.getPositionY()) {
				return false;
			}
		}
		return true;
	}

	// wanneer de slang een muis eet/ over tijd groeit
	public void addBodyParts(int amount) {
		// plek van het laatste bodypart opvragen
		int x = body.get(body.size() - 1).getPositionX();
		int y = body.get(body.size() - 1).getPositionY();

		// nieuwe bodypart toevoegen op de laatste plek
		for (int i = 0; i < amount; i++) {
			body.add(new BodyPart(x, y));
		}
	}

	// wanneer de slang over een beer gaat
	public void removeHalfBody() {
		// afronden naar beneden en delen door 2
		int amount = (int) Math.floorDiv((body.size() + 1), 2);

		for (int i = 0; i < amount; i++) {
			body.remove(body.size() - 1);
		}
	}

	// kijken of de slang nog genoeg body heeft, om door te spelen
	public boolean isTooShort() {
		if (body.size() < 4) {
			return true;
		}
		return false;
	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
	}

	public ArrayList<BodyPart> getBody() {
		return body;
	}

	public void setBody(ArrayList<BodyPart> body) {
		this.body = body;
	}

	// Direction
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
