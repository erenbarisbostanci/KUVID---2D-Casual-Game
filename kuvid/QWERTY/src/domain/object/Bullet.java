package domain.object;

import domain.utilities.Coordinate;

public abstract class Bullet extends Nonshooter{
	
	public Bullet(Coordinate coordinate, int typeId, double movementAngle) {
		super(coordinate, typeId, movementAngle);
	}
	
	abstract int getBulletSize();
	abstract void updateCoordinate(int x, int y);
}
