package domain.object;

import java.util.Random;

import domain.utilities.Coordinate;

public class SigmaAtom extends Atom {

	public SigmaAtom(Coordinate coordinate) {
		super(coordinate, 3, 0);
		this.setProtons(64);
		this.setStability(0.7);
		
		Random random = new Random();
		int[] neutron = new int[]{63, 64, 67};
		int neutrons = neutron[random.nextInt(3)];
		this.setNeutrons(neutrons);
		this.setEfficiency(1.7 / 2 + (Math.abs(neutrons - 64) / 64));
	}
	
	public SigmaAtom(Coordinate coordinate, double movementAngle, int neutrons) {
		super(coordinate, 3, movementAngle, neutrons);
		this.setProtons(64);
		this.setStability(0.7);
		this.setEfficiency(1.7 / 2 + (Math.abs(neutrons - 64) / 64));
	}
}