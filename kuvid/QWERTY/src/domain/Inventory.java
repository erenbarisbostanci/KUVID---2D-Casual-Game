package domain;

import java.util.ArrayList;
import java.util.Random;

import domain.factories.AtomFactory;
import domain.object.Atom;
import domain.observer.IStatsListener;
import domain.utilities.Coordinate;

public class Inventory {

	private static IStatsListener listener;

	// Inventory for power ups, each index represents an power up type while the
	// value
	// of the index represents the amount in inventory
	public static int powerupNumbers[] = new int[4];
	public static int shieldNumbers[] = new int[4];
	static ArrayList<Atom> alphaAtoms = new ArrayList<Atom>();
	static ArrayList<Atom> betaAtoms = new ArrayList<Atom>();
	static ArrayList<Atom> gammaAtoms = new ArrayList<Atom>();
	static ArrayList<Atom> sigmaAtoms = new ArrayList<Atom>();

	static Random rand = new Random();

	// These methods does not require exception handling since they will never be
	// used with negative values.
	// REQUIRES: Valid entries for atomTypeID and amount
	// MODIFIES: Increases the amount of the atom type by the value entered.
	// EFFECTS: atomNumbers[atomTypeID] is incremented by amount entered.

	public static void addAtom(int atomID, int amount) {
		Atom tempAtom;
		for (int i = 0; i < amount; i++) {
			tempAtom = AtomFactory.getAtomFactory().generateAtom(new Coordinate(0, 0), atomID);
			switch (atomID) {
			case 0:
				alphaAtoms.add(tempAtom);
				break;
			case 1:
				betaAtoms.add(tempAtom);
				break;
			case 2:
				gammaAtoms.add(tempAtom);
				break;
			case 3:
				sigmaAtoms.add(tempAtom);
				break;
			default:
				break;
			}
			listener.onItemCountUpdate(alphaAtoms.size(), betaAtoms.size(), sigmaAtoms.size(), gammaAtoms.size());
		}
	}

	public static void addAtom(Atom input) {
		switch (input.getTypeId()) {
		case 0:
			alphaAtoms.add(input);
			break;
		case 1:
			betaAtoms.add(input);
			break;
		case 2:
			gammaAtoms.add(input);
			break;
		case 3:
			sigmaAtoms.add(input);
			break;
		default:
			break;
		}
		listener.onItemCountUpdate(alphaAtoms.size(), betaAtoms.size(), sigmaAtoms.size(), gammaAtoms.size());
	}

	// If this method returns false, it means that the removal operation failed
	// because there are
	// not enough atoms in the inventory.
	// REQUIRES: Valid entries for atomTypeID and amount
	// MODIFIES: Decreases the amount of the atom type by the value entered.
	// EFFECTS: atomNumbers[atomTypeID] is decreased by the amount entered.
	public static boolean removeAtom(int ID, int atomAmount) {
		switch (ID) {
		case 0:
			if (alphaAtoms.size() >= atomAmount) {
				for (int i = 0; i < atomAmount; i++) {
					alphaAtoms.remove(0);
					listener.onItemCountUpdate(alphaAtoms.size(), betaAtoms.size(), sigmaAtoms.size(),
							gammaAtoms.size());
				}
				return true;
			}
			break;
		case 1:
			if (betaAtoms.size() >= atomAmount) {
				for (int i = 0; i < atomAmount; i++) {
					betaAtoms.remove(0);
					listener.onItemCountUpdate(alphaAtoms.size(), betaAtoms.size(), sigmaAtoms.size(),
							gammaAtoms.size());
				}
				return true;
			}
			break;
		case 2:
			if (gammaAtoms.size() >= atomAmount) {
				for (int i = 0; i < atomAmount; i++) {
					gammaAtoms.remove(0);
					listener.onItemCountUpdate(alphaAtoms.size(), betaAtoms.size(), sigmaAtoms.size(),
							gammaAtoms.size());
				}
				return true;
			}
			break;
		case 3:
			if (sigmaAtoms.size() >= atomAmount) {
				for (int i = 0; i < atomAmount; i++) {
					sigmaAtoms.remove(0);
					listener.onItemCountUpdate(alphaAtoms.size(), betaAtoms.size(), sigmaAtoms.size(),
							gammaAtoms.size());
				}
				return true;
			}
			break;
		}
		return false;
	}

	public static void removeAtom(Atom input) {
		switch (input.getTypeId()) {
		case 0:
			if (alphaAtoms.contains(input)) {
				alphaAtoms.remove(input);
				listener.onItemCountUpdate(alphaAtoms.size(), betaAtoms.size(), sigmaAtoms.size(), gammaAtoms.size());
			}
			break;
		case 1:
			if (betaAtoms.contains(input)) {
				betaAtoms.remove(input);
				listener.onItemCountUpdate(alphaAtoms.size(), betaAtoms.size(), sigmaAtoms.size(), gammaAtoms.size());
			}
			break;
		case 2:
			if (gammaAtoms.contains(input)) {
				gammaAtoms.remove(input);
				listener.onItemCountUpdate(alphaAtoms.size(), betaAtoms.size(), sigmaAtoms.size(), gammaAtoms.size());
			}
			break;
		case 3:
			if (sigmaAtoms.contains(input)) {
				sigmaAtoms.remove(input);
				listener.onItemCountUpdate(alphaAtoms.size(), betaAtoms.size(), sigmaAtoms.size(), gammaAtoms.size());
			}
			break;
		default:
			break;
		}
	}

	// These methods does not require exception handling since they will never be
	// used with negative values.
	// REQUIRES: Valid entries for powerUpTypeId and amount
	// MODIFIES: Increases the amount of the powerup type by the value entered.
	// EFFECTS: powerupNumbers[powerupTypeId] is increased by the amount entered.
	public static void addPowerup(int powerupTypeId, int amount) {
		powerupNumbers[powerupTypeId] += amount;
		listener.onPowerupCountUpdate(powerupNumbers[0], powerupNumbers[1], powerupNumbers[3], powerupNumbers[2]);
	}

	// If this method returns false, it means that the removal operation failed
	// because there are
	// not enough power ups in the inventory.
	// REQUIRES: Valid entries for powerUpTypeId and amount
	// MODIFIES: Decreases the amount of the powerup type by the value entered.
	// EFFECTS: powerupNumbers[powerupTypeId] is decreased by the amount entered.
	public static boolean removePowerup(int powerupTypeId, int amount) {
		if (powerupNumbers[powerupTypeId] >= amount) {
			powerupNumbers[powerupTypeId] -= amount;
			listener.onPowerupCountUpdate(powerupNumbers[0], powerupNumbers[1], powerupNumbers[3], powerupNumbers[2]);
			return true;
		} else {
			return false;
		}
	}

	public static void addShield(int shieldTypeId, int amount) {
		shieldNumbers[shieldTypeId] += amount;
		listener.onShieldCountUpdate(shieldNumbers[0], shieldNumbers[1], shieldNumbers[2], shieldNumbers[3]);
	}

	public static boolean removeShield(int shieldTypeId, int amount) {
		if (shieldNumbers[shieldTypeId] >= amount) {
			shieldNumbers[shieldTypeId] -= amount;
			listener.onShieldCountUpdate(shieldNumbers[0], shieldNumbers[1], shieldNumbers[2], shieldNumbers[3]);
			return true;
		} else {
			return false;
		}
	}

	// Chooses a random atom to send to shooter //REQUIRES: Valid entries for
	// powerUpTypeId and amount
	// REQUIRES: Inventory has at least 1 atom.
	// MODIFIES: Picks a random atom from inventory and returns it for shooter to
	// use
	// EFFECTS: The returned atom is then used by the shooter as ammunition.
	public static Atom loadRandomAtom() {

		@SuppressWarnings("rawtypes")
		ArrayList<ArrayList> list = new ArrayList<ArrayList>();
		if (!alphaAtoms.isEmpty()) {
			list.add(alphaAtoms);
		}
		if (!betaAtoms.isEmpty()) {
			list.add(betaAtoms);
		}
		if (!gammaAtoms.isEmpty()) {
			list.add(gammaAtoms);
		}
		if (!sigmaAtoms.isEmpty()) {
			list.add(sigmaAtoms);
		}
		if (list.isEmpty()) {
			return null;
		}
		int i = rand.nextInt(list.size());
		int k = rand.nextInt(list.get(i).size());
		Atom remover = ((Atom) list.get(i).get(k));
		removeAtom(remover);
		return remover;

	}

	public static void setListener(IStatsListener listenerI) {
		listener = listenerI;
	}

	public static void clean() {
		for(int i = 0; i < 4; i++) {
			powerupNumbers[i] = 0;
			shieldNumbers[i] = 0;			
		}
		alphaAtoms = new ArrayList<Atom>();
		betaAtoms = new ArrayList<Atom>();
		gammaAtoms = new ArrayList<Atom>();
		sigmaAtoms = new ArrayList<Atom>();
		listener.onItemCountUpdate(alphaAtoms.size(), betaAtoms.size(), sigmaAtoms.size(), gammaAtoms.size());
		listener.onPowerupCountUpdate(powerupNumbers[0], powerupNumbers[1], powerupNumbers[3], powerupNumbers[2]);
		listener.onShieldCountUpdate(shieldNumbers[0], shieldNumbers[1], shieldNumbers[2], shieldNumbers[3]);
	}

}
