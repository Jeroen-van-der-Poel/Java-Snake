package Model;

import java.util.Random;

public enum Marker {

	MOUSE, BEAR, FIRE;

	// random genereren van een marker
	public static Marker generateRandomMarker() {
		Random r = new Random();
		switch (r.nextInt(3)) {
		case 0:
			return Marker.MOUSE;
		case 1:
			return Marker.BEAR;
		case 2:
			return Marker.FIRE;
		default:
			return null;
		}
	}

}
