package domain.object;

import java.util.Random;

import domain.utilities.Coordinate;

public class GammaAtom extends Atom {

	public GammaAtom(Coordinate coordinate) {
		super(coordinate, 2, 0);
		this.setProtons(32);
		this.setStability(0.8);
		
		Random random = new Random();
		int[] neutron = new int[]{29, 32 ,33};
		int neutrons = neutron[random.nextInt(3)];
		this.setNeutrons(neutrons);
		this.setEfficiency(0.8 +  (Math.abs(neutrons - 32) / 64));
	}

	public GammaAtom(Coordinate coordinate, double movementAngle, int neutrons) {
		super(coordinate, 2, movementAngle, neutrons);
		this.setProtons(32);
		this.setStability(0.8);
		this.setEfficiency(0.8 +  (Math.abs(neutrons - 32) / 64));
	}
}