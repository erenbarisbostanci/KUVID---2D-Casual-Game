package domain.object;

import domain.utilities.*;

public abstract class ObjectBase {
	
	private Coordinate coordinate;

	public ObjectBase(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}
	
	public void setCoordinate(int x, int y) {
		this.coordinate.setX(x);
		this.coordinate.setY(y);
	}
}
