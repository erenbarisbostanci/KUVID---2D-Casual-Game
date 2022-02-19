package domain.object;

import domain.utilities.*;

public class GammaMolecule extends Molecule {

	public GammaMolecule(Coordinate coordinate) {
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
	}
}
