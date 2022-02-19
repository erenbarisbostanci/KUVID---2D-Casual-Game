package domain.object;

import domain.utilities.*;

public class AlphaMolecule extends Molecule {
	
	public AlphaMolecule(Coordinate coordinate) {
		super(coordinate, 0, 315);
	}

	@Override
	public void update() {
		super.travel();
		alternate();
		if(listener != null) {
			listener.onCoordinateUpdate(getCoordinate().getX(), getCoordinate().getY());
		}
	}
}
