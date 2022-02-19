package domain.object;

import domain.utilities.*;

public class SigmaBlocker extends Blocker {

	public SigmaBlocker(Coordinate coordinate) {
		super(coordinate, 3, 270);
	}

	@Override
	public void update() {
		super.travel();
		if(listener != null) {
			listener.onCoordinateUpdate(getCoordinate().getX(), getCoordinate().getY());
		}
		super.damage();
		super.explode();
		super.onGround();
	}
}
