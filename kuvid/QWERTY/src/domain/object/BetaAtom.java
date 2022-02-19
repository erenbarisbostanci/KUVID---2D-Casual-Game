package domain.object;

import java.util.Random;

import domain.utilities.Coordinate;

public class BetaAtom extends Atom {

	public BetaAtom(Coordinate coordinate) {
		super(coordinate, 1, 0);
		this.setProtons(16);
		this.setStability(0.9);
		
		Random random = new Random();
		int[] neutron = new int[]{15, 16, 17, 18, 21};
		int neutrons = neutron[random.nextInt(5)];
		this.setNeutrons(neutrons);
		this.setEfficiency(0.9 - (0.5 * Math.abs(neutrons - 16) /  16));
	}
	
	public BetaAtom(Coordinate coordinate, double movementAngle, int neutrons) {
		super(coordinate, 1, movementAngle, neutrons);
		this.setProtons(16);
		this.setStability(0.9);
		this.setEfficiency(0.9 - (0.5 * Math.abs(neutrons - 16) /  16));
	}
}
