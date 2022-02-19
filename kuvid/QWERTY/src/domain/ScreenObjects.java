package domain;

import java.util.ArrayList;

import domain.factories.DropperFactory;
import domain.object.Atom;
import domain.object.Blocker;
import domain.object.Molecule;
import domain.object.Nonshooter;
import domain.object.ObjectBase;
import domain.object.Powerup;
import domain.object.Shooter;
import domain.observer.ICreationListener;
import domain.utilities.Coordinate;
import domain.utilities.StaticFields;
import domain.utilities.StatisticTracker;

public class ScreenObjects {

	private static ScreenObjects screenObjects;
	private static ICreationListener listener;

	private static ArrayList<Atom> ATOMS = new ArrayList<Atom>();
	private static ArrayList<Molecule> MOLECULES = new ArrayList<Molecule>();
	private static ArrayList<Powerup> POWERUPS = new ArrayList<Powerup>();
	private static ArrayList<Blocker> BLOCKERS = new ArrayList<Blocker>();
	ArrayList<Nonshooter> remove;

	private static Shooter SHOOTER = new Shooter(
			new Coordinate(StaticFields.SCREEN_WIDTH / 2, (StaticFields.SCREEN_HEIGHT - StaticFields.LENGTH_L)));

	public synchronized static ScreenObjects getScreenObjects() {
		if (screenObjects == null) {
			screenObjects = new ScreenObjects();
		}
		return screenObjects;
	}

	public void setListener(ICreationListener listenerIn) {
		listener = listenerIn;
	}

	public ICreationListener getListener() {
		return listener;
	}

	public ArrayList<Atom> getATOMS() {
		return ATOMS;
	}

	public void setATOMS(ArrayList<Atom> aTOMS) {
		ATOMS = aTOMS;
	}

	public ArrayList<Molecule> getMOLECULES() {
		return MOLECULES;
	}

	public void setMOLECULES(ArrayList<Molecule> mOLECULES) {
		MOLECULES = mOLECULES;
	}

	public ArrayList<Powerup> getPOWERUPS() {
		return POWERUPS;
	}

	public void setPOWERUPS(ArrayList<Powerup> pOWERUPS) {
		POWERUPS = pOWERUPS;
	}

	public ArrayList<Blocker> getBLOCKERS() {
		return BLOCKERS;
	}

	public void setBLOCKERS(ArrayList<Blocker> bLOCKERS) {
		BLOCKERS = bLOCKERS;
	}

	public void addAtom(Atom atom) {
		ATOMS.add(atom);
		if (atom.getListener() == null) {
			listener.setAtomListener(atom);
			atom.getListener().onShieldUpdate(atom.getShieldList());
		}
	}

	public Molecule addMolecule(Coordinate coor, int typeId) {
		Molecule molecule = DropperFactory.getDropperFactory().generateMolecule(coor, typeId);
		listener.setMoleculeListener(molecule);
		MOLECULES.add(molecule);
		return molecule;
	}

	public Powerup addPowerup(Coordinate coor, int typeId) {
		Powerup powerup = DropperFactory.getDropperFactory().generatePowerup(coor, typeId);
		listener.setPowerupListener(powerup);
		POWERUPS.add(powerup);
		return powerup;
	}
	public void addPowerup(Powerup powerup) {
		POWERUPS.add(powerup);
		if (powerup.getListener() == null) {
			listener.setPowerupListener(powerup);
		}
	}

	public Blocker addBlocker(Coordinate coor, int typeId) {
		Blocker blocker = DropperFactory.getDropperFactory().generateBlocker(coor, typeId);
		listener.setBlockerListener(blocker);
		BLOCKERS.add(blocker);
		return blocker;
	}

	public void removeAtom(Atom rem) {
		listener.removeAtomListener(rem.getListener());
		ATOMS.remove(rem);
	}

	public void removeMolecule(Molecule rem) {
		listener.removeMoleculeListener(rem.getListener());
		MOLECULES.remove(rem);
	}

	public void removePowerup(Powerup rem) {
		listener.removePowerupListener(rem.getListener());
		POWERUPS.remove(rem);
	}

	public void removeBlocker(Blocker rem) {
		listener.removeBlockerListener(rem.getListener());
		BLOCKERS.remove(rem);
	}

	public Shooter getShooter() {
		return SHOOTER;
	}

	public void cleanObjects() {
		remove = new ArrayList<Nonshooter>();
		for (Atom atom : ATOMS) {
			remove.add(atom);
		}

		for (Molecule molecule : MOLECULES) {
			remove.add(molecule);
		}
		for (Powerup powerup : POWERUPS) {
			remove.add(powerup);
		}
		for (Blocker blocker : BLOCKERS) {
			remove.add(blocker);
		}

		for (int i = 0; i < remove.size(); i++) {
			if (remove.get(i) instanceof Atom) {
				removeAtom((Atom) remove.get(i));
			}
			if (remove.get(i) instanceof Molecule) {
				removeMolecule((Molecule) remove.get(i));
			}
			if (remove.get(i) instanceof Powerup) {
				removePowerup((Powerup) remove.get(i));
			}
			if (remove.get(i) instanceof Blocker) {
				removeBlocker((Blocker) remove.get(i));
			}
		}
	}

	public void update() {
		remove = new ArrayList<Nonshooter>();

		for (int i = 0; i < ATOMS.size(); i++) {
			ATOMS.get(i).update();
			if (isOut(ATOMS.get(i))) {
				remove.add(ATOMS.get(i));
			}
		}

		for (int i = 0; i < MOLECULES.size(); i++) {
			MOLECULES.get(i).update();
			if (isOut(MOLECULES.get(i))) {
				remove.add(MOLECULES.get(i));
			}
		}

		for (int i = 0; i < POWERUPS.size(); i++) {
			POWERUPS.get(i).update();
			if (isOut(POWERUPS.get(i))) {
				remove.add(POWERUPS.get(i));
			}
		}

		for (int i = 0; i < BLOCKERS.size(); i++) {
			BLOCKERS.get(i).update();
			if (isOut(BLOCKERS.get(i))) {
				remove.add(BLOCKERS.get(i));
			}
		}

		remove = willCollideAtomMolecule(remove);
		remove = willCollidePowerupBlocker(remove);
		remove = willCollidePowerupShooter(remove);

		for (Nonshooter obj : remove) {
			if (obj instanceof Molecule) {
				removeMolecule((Molecule) obj);
			} else if (obj instanceof Atom) {
				removeAtom((Atom) obj);
			} else if (obj instanceof Powerup) {
				removePowerup((Powerup) obj);
			} else if (obj instanceof Blocker) {
				removeBlocker((Blocker) obj);
			}
		}
	}

	private ArrayList<Nonshooter> willCollideAtomMolecule(ArrayList<Nonshooter> remove) {
		for (int i = 0; i < ATOMS.size(); i++) {
			Atom atom = ATOMS.get(i);
			for (int j = 0; j < MOLECULES.size(); j++) {
				Molecule molecule = MOLECULES.get(j);
				if (!remove.contains(atom) && !remove.contains(molecule)) {
					if (atom.getTypeId() == molecule.getTypeId()) {

						int atomX = atom.getCoordinate().getX();
						int atomToX = atom.getCoordinate().getX() + atom.getBulletSize();
						int moleculeX = molecule.getCoordinate().getX();
						int moleculeToX = molecule.getCoordinate().getX() + molecule.getSize();

						if (((atomX > moleculeX) && (atomX < moleculeToX))
								|| ((atomToX > moleculeX) && (atomToX < moleculeToX))) {
							int atomY = atom.getCoordinate().getY();
							int atomToY = atom.getCoordinate().getY() + atom.getBulletSize();
							int moleculeY = molecule.getCoordinate().getY();
							int moleculeToY = molecule.getCoordinate().getY() + molecule.getSize();

							if (((atomY > moleculeY) && (atomY < moleculeToY))
									|| ((atomToY > moleculeY) && (atomToY < moleculeToY))) {

								remove.add(atom);
								remove.add(molecule);

								StatisticTracker.getStatisticTracker().setScore(StatisticTracker.getStatisticTracker().getScore() + atom.getEfficiency());
							}

						}
					}
				}
			}
		}
		return remove;

	}

	private ArrayList<Nonshooter> willCollidePowerupBlocker(ArrayList<Nonshooter> remove) {
		for (int i = 0; i < POWERUPS.size(); i++) {
			Powerup powerup = POWERUPS.get(i);
			if(powerup.getIsBullet()) {
				for (int j = 0; j < BLOCKERS.size(); j++) {
					Blocker blocker = BLOCKERS.get(j);
					if (!remove.contains(powerup) && !remove.contains(blocker)) {
						if (powerup.getTypeId() == blocker.getTypeId()) {

							int puX = powerup.getCoordinate().getX();
							int puToX = powerup.getCoordinate().getX() + powerup.getBulletSize();
							int blockX = blocker.getCoordinate().getX();
							int blockToX = blocker.getCoordinate().getX() + blocker.getSize();

							if (((puX > blockX) && (puX < blockToX))
									|| ((puToX > blockX) && (puToX < blockToX))) {
								int puY = powerup.getCoordinate().getY();
								int puToY = powerup.getCoordinate().getY() + powerup.getBulletSize();
								int blockY = blocker.getCoordinate().getY();
								int blockToY = blocker.getCoordinate().getY() + blocker.getSize();

								if (((puY > blockY) && (puY < blockToY))
										|| ((puToY > blockY) && (puToY < blockToY))) {

									remove.add(powerup);
									remove.add(blocker);

								}

							}
						}
					}

				}
			}
		}
		return remove;

	}

	private ArrayList<Nonshooter> willCollidePowerupShooter(ArrayList<Nonshooter> remove) {
		for (int i = 0; i < POWERUPS.size(); i++) {
			Powerup powerup = POWERUPS.get(i);
			if(!powerup.getIsBullet()) {
				if (!remove.contains(powerup)) {
					if(powerup.getCoordinate().getY() + powerup.getBulletSize() >= StaticFields.SCREEN_HEIGHT - ScreenObjects.getScreenObjects().getShooter().getHeight()) {

						int puX = powerup.getCoordinate().getX();
						int puToX = powerup.getCoordinate().getX() + powerup.getBulletSize();
						int shooterX = ScreenObjects.getScreenObjects().getShooter().getCoordinate().getX();
						int shooterToX = ScreenObjects.getScreenObjects().getShooter().getCoordinate().getX() + ScreenObjects.getScreenObjects().getShooter().getWidth();

						if (((puX > shooterX) && (puX < shooterToX))
								|| ((puToX > shooterX) && (puToX < shooterToX))) {

							remove.add(powerup);
							Inventory.addPowerup(powerup.getTypeId(), 1);
						}

					}
				}
			}

		}
		return remove;

	}

	public ArrayList<Nonshooter> getRemove() {
		return remove;
	}

	public boolean isOut(ObjectBase object) {
		if ((object.getCoordinate().getX() < StaticFields.LEFT_BORDER)
				|| (object.getCoordinate().getX() > StaticFields.SCREEN_WIDTH) || (object.getCoordinate().getY() < 0)
				|| (object.getCoordinate().getY() > StaticFields.SCREEN_HEIGHT))
			return true;
		return false;
	}
}
