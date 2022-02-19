package domain.observer;

import domain.object.Atom;
import domain.object.Blocker;
import domain.object.Molecule;
import domain.object.Powerup;
import domain.object.Shooter;

public interface ICreationListener {
	void setShooter(Shooter shooter);
	void setAtomListener(Atom atom);
	void setMoleculeListener(Molecule molecule);
	void removeAtomListener(IAtomListener list);
	void removeMoleculeListener(IObjectListener list);
	void setBlockerListener(Blocker blocker);
	void removeBlockerListener(IBlockerListener list);
	void setPowerupListener(Powerup powerup);
	void removePowerupListener(IObjectListener list);

}
