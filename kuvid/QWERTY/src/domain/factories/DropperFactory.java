package domain.factories;

import domain.object.AlphaMolecule;
import domain.object.AlphaBlocker;
import domain.object.BetaMolecule;
import domain.object.BetaBlocker;
import domain.object.Blocker;
import domain.object.GammaMolecule;
import domain.object.GammaBlocker;
import domain.object.Molecule;
import domain.object.Powerup;
import domain.object.SigmaMolecule;
import domain.utilities.Coordinate;
import domain.object.SigmaBlocker;

public class DropperFactory {

	public static DropperFactory dropper;
	
	private DropperFactory() {}
	
	public synchronized static DropperFactory getDropperFactory() {
		if (dropper == null) {
			dropper = new DropperFactory();
		}
		return dropper;
	}
	
	public Molecule generateMolecule(Coordinate coor, int typeId) {
		//REQUIRES: "coor" parameter is valid (between 0 and SCREEN_WIDTH) and "typeId" parameter is 0,1,2 or 3, indicating four different types 
		//MODIFIES: -				(nothing to be modified, just creates and returns)
		//EFFECTS: Returned molecule has corresponding coordinate "coor" and type "typeId"
		Molecule ret = null;
		switch(typeId) {
			case 0:
				ret = new AlphaMolecule(coor);
				break;
			case 1:
				ret = new BetaMolecule(coor);
				break;
			case 2:
				ret = new GammaMolecule(coor);
				break;
			case 3:
				ret = new SigmaMolecule(coor);
				break;
		}
		return ret;
	}
	
	public Powerup generatePowerup(Coordinate coor, int typeId) {
		//REQUIRES: "coor" parameter is valid (between 0 and SCREEN_WIDTH) and "typeId" parameter is 0,1,2 or 3, indicating four different types 
		//MODIFIES: -				(nothing to be modified, just creates and returns)
		//EFFECTS: Returned powerup has corresponding coordinate "coor" and type "typeId"
		Powerup ret = null;
		switch(typeId) {
			case 0:
				ret = new Powerup(coor, 0);
				break;
			case 1:
				ret = new Powerup(coor, 1);
				break;
			case 2:
				ret = new Powerup(coor, 2);
				break;
			case 3:
				ret = new Powerup(coor, 3);
				break;
		}
		return ret;
	}
	
	public Blocker generateBlocker(Coordinate coor, int typeId) {
		//REQUIRES: "coor" parameter is valid (between 0 and SCREEN_WIDTH) and "typeId" parameter is 0,1,2 or 3, indicating four different types 
		//MODIFIES: -				(nothing to be modified, just creates and returns)
		//EFFECTS: Returned blocker has corresponding coordinate "coor" and type "typeId"
		Blocker ret = null;
		switch(typeId) {
			case 0:
				ret = new AlphaBlocker(coor);
				break;
			case 1:
				ret = new BetaBlocker(coor);
				break;
			case 2:
				ret = new GammaBlocker(coor);
				break;
			case 3:
				ret = new SigmaBlocker(coor);
				break;
		}
		return ret;
	}
}
