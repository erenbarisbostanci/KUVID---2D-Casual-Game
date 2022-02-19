package domain.object;

import domain.observer.IObjectListener;
import domain.utilities.*;

public class Powerup extends Bullet {
	
	private IObjectListener listener;
	private boolean isBullet;

	public Powerup(Coordinate coordinate, int typeId) {
		super(coordinate, typeId, 270);
		isBullet = false;
	}

	public Powerup(Coordinate coordinate, int typeId, double movementAngle) {
		super(coordinate, typeId, movementAngle);
		isBullet = true;
	}
	
	public boolean getIsBullet() {
		return isBullet;
	}

	@Override
	public void update() {
		if (isBullet) {
			willCollide((int) (StaticFields.LENGTH_L * Math.cos(Math.toRadians(this.getMovementAngle())))/4);
		}
		travel();
		if(listener != null) {
			listener.onCoordinateUpdate(getCoordinate().getX(), getCoordinate().getY());
		}
	}
	
	public void setListener(IObjectListener listener) {
		this.listener = listener;
		this.listener.onCoordinateUpdate(getCoordinate().getX(), getCoordinate().getY());
		this.listener.onSizeUpdate(getBulletSize(), getBulletSize());
	}
	
	public IObjectListener getListener() {
		return listener;
	}

	@Override
	public int getBulletSize() {
		return StaticFields.LENGTH_L * 50 /100;
	}

	@Override
	void updateCoordinate(int x, int y) {
		getCoordinate().setX(x);
		getCoordinate().setY(y);
		if(listener != null) {
			listener.onCoordinateUpdate(getCoordinate().getX(), getCoordinate().getY());
		}
	}

	public void setIsBullet(Boolean bool) {
		this.isBullet = bool;
	}
}

