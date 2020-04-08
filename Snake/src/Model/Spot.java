package Model;

public class Spot {

	// instance variabelen
	private int positionX;
	private int positionY;
	private Marker marker;

	// constructor
	public Spot(int positionX, int positionY, Marker marker) {
		this.positionX = positionX;
		this.positionY = positionY;
		this.marker = marker;
	}

	public int getPositionX() {
		return positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public Marker getMarker() {
		return marker;
	}
}
