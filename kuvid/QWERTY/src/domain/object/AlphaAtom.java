package domain.object;

import java.util.Random;

import domain.utilities.Coordinate;

public class AlphaAtom extends Atom {

	public AlphaAtom(Coordinate coordinate) {
		super(coordinate, 0, 0);
		this.setProtons(8);
		this.setStability(0.85);
		
		Random random = new Random();
		int[] neutron = new int[]{7,8,9};
		int neutrons = neutron[random.nextInt(3)];
		this.setNeutrons(neutrons);
		this.setEfficiency((1 - (Math.abs(neutrons - 8) /  8)) * 0.85);
	}

	public AlphaAtom(Coordinate coordinate, double movementAngle, int neutrons) {
		super(coordinate, 0, movementAngle, neutrons);
		this.setProtons(8);
		this.setStability(0.85);
		this.setEfficiency((1 - (Math.abs(neutrons - 8) /  8)) * 0.85);
	}
}
