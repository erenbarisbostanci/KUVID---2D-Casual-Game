package domain.object;

import domain.utilities.*;

public class BetaMolecule extends Molecule {
	
	public BetaMolecule(Coordinate coordinate) {
		super(coordinate, 1, 270);
	}

	@Override
	public void update() {
		super.travel();
		if (this.getCoordinate().getY() >= StaticFields.SCREEN_HEIGHT / 4) {
			alternate();
		}
		if(listener != null) {
			listener.onCoordinateUpdate(getCoordinate().getX(), getCoordinate().getY());
		}
	}
}
