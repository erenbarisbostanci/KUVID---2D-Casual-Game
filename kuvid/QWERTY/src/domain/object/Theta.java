package domain.object;

import java.util.ArrayList;
import java.util.Random;

import domain.utilities.StaticFields;

public class Theta extends ShieldDecorator {
	double Theta_efficiency_boost;
	static Random random = new Random();
	public Theta(Atom atom) {
		super(atom);
		Theta_efficiency_boost = (random.nextInt(9)+6)/100;
	}
	
	@Override
	public double getSpeedFactor() {
		return atom.getSpeedFactor()*0.91;
	}
	
	@Override
	public double getEfficiency() {
		return atom.getEfficiency() + ((1 - atom.getEfficiency()) * Theta_efficiency_boost);
	}
	
	@Override
	public ArrayList<Integer> getShieldList() {
		ArrayList<Integer> list = getAtom().getShieldList();
		list.add(2);
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
