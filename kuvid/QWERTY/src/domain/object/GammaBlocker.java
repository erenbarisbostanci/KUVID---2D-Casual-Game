package domain.object;

import domain.utilities.*;

public class GammaBlocker extends Blocker {

	public GammaBlocker(Coordinate coordinate) {
		super(coordinate, 2, 270);
	}

	@Override
	public void update() {
		super.travel();
		if (this.getCoordinate().getY() >= StaticFields.SCREEN_HEIGHT / 2) {
			alternate();
		}
		if(listener != null) {
			listener.onCoordinateUpdate(getCoordinate().getX(), getCoordinate().getY());
		}
		super.damage();
		super.explode();
		super.onGround();
	}
}
