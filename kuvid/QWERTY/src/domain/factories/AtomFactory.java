package domain.factories;

import domain.object.*;
import domain.utilities.Coordinate;

public class AtomFactory {

	public static AtomFactory atomFactory;
	
	public AtomFactory() {}
	
	public synchronized static AtomFactory getAtomFactory() {
		if (atomFactory == null) {
			atomFactory = new AtomFactory();
		}
		return atomFactory;
	}
	
	public Atom generateAtom(Coordinate coor, int typeId) {
		Atom ret = null;
		switch(typeId) {
			case 0:
				ret = new AlphaAtom(coor);
				break;
			case 1:
				ret = new BetaAtom(coor);
				break;
			case 2:
				ret = new GammaAtom(coor);
				break;
			case 3:
				ret = new SigmaAtom(coor);
				break;
		}
		return ret;
	}
}
