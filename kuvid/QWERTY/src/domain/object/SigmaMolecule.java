package domain.object;

import domain.utilities.*;

public class SigmaMolecule extends Molecule {

	public SigmaMolecule(Coordinate coordinate) {
		super(coordinate, 3, 270);
	}

	@Override
	public void update() {
		super.travel();
		if(listener != null) {
			listener.onCoordinateUpdate(getCoordinate().getX(), getCoordinate().getY());
		}
	}
}
