package domain.object;

import domain.observer.IAtomListener;
import domain.utilities.Coordinate;
import domain.utilities.StaticFields;

public abstract class ShieldDecorator extends Atom {
	protected Atom atom;
	
	public ShieldDecorator(Atom atom) {
		super(null, -1, -1);
		this.atom = atom;
	}
	
	public Atom getAtom() {
		return atom;
	}

	public void setAtom(Atom atom) {
		this.atom = atom;
	}
	
	@Override
	public void update() {
		willCollide((int) (StaticFields.LENGTH_L * Math.cos(Math.toRadians(this.getMovementAngle())))/4);
		travel();
		
		if(getListener() != null) {
			getListener().onCoordinateUpdate(getCoordinate().getX(), getCoordinate().getY());
		}
	}

	@Override
	public int getBulletSize() {
		return atom.getBulletSize();
	}

	@Override
	void updateCoordinate(int x, int y) {
		atom.updateCoordinate(x, y);
	}

	@Override
	public void setListener(IAtomListener listener) {
		atom.setListener(listener);
	}

	@Override
	public IAtomListener getListener() {
		return atom.getListener();
	}

	@Override
	public double getStability() {
		return atom.getStability();
	}

	@Override
	public void setStability(double stability) {
		atom.setStability(stability);
	}

	@Override
	public int getNeutrons() {
		return atom.getNeutrons();
	}

	@Override
	public void setNeutrons(int neutrons) {
		atom.setNeutrons(neutrons);
	}

	@Override
	public int getProtons() {
		return atom.getProtons();
	}

	@Override
	public void setProtons(int protons) {
		atom.setProtons(protons);
	}

	@Override
	public double getEfficiency() {
		return atom.getEfficiency();
	}

	@Override
	public void setEfficiency(double efficiency) {
		atom.setEfficiency(efficiency);
	}

	@Override
	public int getTypeId() {
		return atom.getTypeId();
	}

	@Override
	public double getMovementAngle() {
		return atom.getMovementAngle();
	}

	@Override
	public void setMovementAngle(double movementAngle) {
		atom.setMovementAngle(movementAngle);
	}

	@Override
	public void travel() {
		this.getCoordinate().setX(this.getCoordinate().getX() + (int) (StaticFields.LENGTH_L * Math.cos(Math.toRadians(movementAngle))/10));
		this.getCoordinate().setY(this.getCoordinate().getY() + (int) (StaticFields.LENGTH_L * -Math.sin(Math.toRadians(movementAngle))/10));
	}

	@Override
	public void willCollide(int xMov) {
		atom.willCollide(xMov);
	}

	@Override
	public void alternate() {
		atom.alternate();
	}

	@Override
	public Coordinate getCoordinate() {
		return atom.getCoordinate();
	}
	
	@Override
	public void setCoordinate(int x, int y) {
		atom.setCoordinate(x, y);
	}
	
}
