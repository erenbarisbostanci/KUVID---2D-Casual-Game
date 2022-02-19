package domain.object;

import java.util.ArrayList;

import domain.utilities.StaticFields;

public class Eta extends ShieldDecorator {
	
	public Eta(Atom atom) {
		super(atom);
	}
	
	@Override
	public double getSpeedFactor() {
		return atom.getSpeedFactor()*0.95;
	}
	
	@Override
	public double getEfficiency() {
		if (getNeutrons() != getProtons()) {
			return atom.getEfficiency() + (1 - atom.getEfficiency()) * Math.abs(atom.getNeutrons() - atom.getProtons()) / atom.getProtons();
		} else return atom.getEfficiency() + ((1 - atom.getEfficiency()) * 0.05);
	}
	
	@Override
	public ArrayList<Integer> getShieldList() {
		ArrayList<Integer> list = getAtom().getShieldList();
		list.add(0);
		return list;
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
	public void travel() {
		atom.getCoordinate().setX(atom.getCoordinate().getX() + (int) ((StaticFields.LENGTH_L * Math.cos(Math.toRadians(atom.getMovementAngle()))/10)*this.getSpeedFactor()));
		atom.getCoordinate().setY(atom.getCoordinate().getY() + (int) (((StaticFields.LENGTH_L * -Math.sin(Math.toRadians(atom.getMovementAngle()))/10)*this.getSpeedFactor())-0.5));
	}
}
