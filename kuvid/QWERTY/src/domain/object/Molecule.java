package domain.object;

import domain.observer.IObjectListener;
import domain.utilities.*;

public abstract class Molecule extends Nonshooter {

	protected IObjectListener listener;
	
	public Molecule(Coordinate coordinate, int typeId, double movementAngle) {
		super(coordinate, typeId, movementAngle);
	}

	@Override
	public void update() {
		travel();
	}
	
	public void setListener(IObjectListener listener) {
		this.listener = listener;
		this.listener.onCoordinateUpdate(getCoordinate().getX(), getCoordinate().getY());
		this.listener.onSizeUpdate(getSize(), getSize());
	}
	
	public IObjectListener getListener() {
		return listener;
	}
	
	public int getSize() {
		return StaticFields.LENGTH_L * 50 /100;
	}
}


