package domain.object;

import java.util.ArrayList;

import domain.observer.IAtomListener;
import domain.utilities.Coordinate;
import domain.utilities.StaticFields;

public class Atom extends Bullet {
	
	private IAtomListener listener;
	private double stability;
	private int neutrons;
	private int protons;
	private double efficiency;
	private double speedFactor = 1d;
	

	public Atom(Coordinate coordinate, int typeId, double movementAngle) {
		super(coordinate, typeId, movementAngle);
	}
	
	public Atom(Coordinate coordinate, int typeId, double movementAngle, int neutrons) {
		super(coordinate, typeId, movementAngle);
		this.neutrons = neutrons;
	}
	
	@Override
	public void update() {
		willCollide((int) (StaticFields.LENGTH_L * Math.cos(Math.toRadians(this.getMovementAngle())))/4);
		travel();
		
		if(listener != null) {
			listener.onCoordinateUpdate(getCoordinate().getX(), getCoordinate().getY());
		}
	}
	
	
	@Override
	public void travel() {
		this.getCoordinate().setX(this.getCoordinate().getX() + (int) (StaticFields.LENGTH_L * Math.cos(Math.toRadians(movementAngle))/10));
		this.getCoordinate().setY(this.getCoordinate().getY() + (int) (StaticFields.LENGTH_L * -Math.sin(Math.toRadians(movementAngle))/10));
	}

	@Override
	public int getBulletSize() {
		return (StaticFields.LENGTH_L * 20 /100);
	}

	@Override
	void updateCoordinate(int x, int y) {
		getCoordinate().setX(x);
		getCoordinate().setY(y);
		if(listener != null) {
			listener.onCoordinateUpdate(getCoordinate().getX(), getCoordinate().getY());
		}
	}
	
	public void setListener(IAtomListener listener) {
		this.listener = listener;
		if(this.listener != null) {
			this.listener.onCoordinateUpdate(getCoordinate().getX(), getCoordinate().getY());
			this.listener.onSizeUpdate(getBulletSize(), getBulletSize());
		}
	}
	
	public IAtomListener getListener() {
		return listener;
	}

	public double getStability() {
		return stability;
	}

	public void setStability(double stability) {
		this.stability = stability;
	}

	public int getNeutrons() {
		return neutrons;
	}

	public void setNeutrons(int neutrons) {
		this.neutrons = neutrons;
	}

	public int getProtons() {
		return protons;
	}

	public void setProtons(int protons) {
		this.protons = protons;
	}
	
	public double getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
	}
	
	public double getSpeedFactor() {
		return speedFactor;
	}
	
	public ArrayList<Integer> getShieldList() {
		ArrayList<Integer> shieldTypeIds = new ArrayList<Integer>();
		return shieldTypeIds;
	}
}

