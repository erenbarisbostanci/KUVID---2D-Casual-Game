package kuvid;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import domain.Inventory;
import domain.observer.IStatsListener;
import ui.objects.StatsUIHolder;

class InventoryTest {

	@BeforeAll
	static void init() {
		IStatsListener listener = new StatsUIHolder();
		Inventory.setListener(listener);
	}

	@Test
	void addAtomTest() {
		/*
		int beforeValue = Inventory.atomNumbers[0];
		Inventory.addAtom(0, 10);
		int afterValue = Inventory.atomNumbers[0];
		assertEquals(afterValue - beforeValue, 10);

		beforeValue = Inventory.atomNumbers[1];
		Inventory.addAtom(1, 10);
		afterValue = Inventory.atomNumbers[1];
		assertEquals(afterValue - beforeValue, 10);

		beforeValue = Inventory.atomNumbers[2];
		Inventory.addAtom(2, 10);
		afterValue = Inventory.atomNumbers[2];
		assertEquals(afterValue - beforeValue, 10);

		beforeValue = Inventory.atomNumbers[3];
		Inventory.addAtom(3, 10);
		afterValue = Inventory.atomNumbers[3];
		assertEquals(afterValue - beforeValue, 10);
		*/
	}

	@Test
	void addAtomTestInvalidInput() {
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
			Inventory.addAtom(5, 10);
		});

		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
			Inventory.addAtom(6, 10);
		});
	}

	@Test
	void removeAtomTest() {
		//start with initial value
		Inventory.addAtom(0, 10);
		Inventory.removeAtom(0, 4);
		//assertEquals(Inventory.atomNumbers[0], 6);

		Inventory.addAtom(1, 10);
		Inventory.removeAtom(1, 2);
		//assertEquals(Inventory.atomNumbers[1], 8);

		Inventory.addAtom(2, 10);
		Inventory.removeAtom(2, 3);
		//assertEquals(Inventory.atomNumbers[2], 7);

		Inventory.addAtom(3, 10);
		Inventory.removeAtom(3, 6);
		//assertEquals(Inventory.atomNumbers[3], 4);
	}

	@Test
	void removeAtomTestIfNoAtomLeft() {
		/*
		Inventory.atomNumbers[0] = 0;
		Inventory.atomNumbers[1] = 0;
		Inventory.atomNumbers[2] = 0;
		Inventory.atomNumbers[3] = 0;
		assertFalse(Inventory.removeAtom(0, 4));
		assertFalse(Inventory.removeAtom(1, 2));
		assertFalse(Inventory.removeAtom(2, 3));
		assertFalse(Inventory.removeAtom(3, 6));
		*/
	}
	
	@Test
	void addPowerupTest() {
		int beforeValue = Inventory.powerupNumbers[0];
		Inventory.addPowerup(0, 10);
		int afterValue = Inventory.powerupNumbers[0];
		assertEquals(afterValue - beforeValue, 10);
		
		beforeValue = Inventory.powerupNumbers[1];
		Inventory.addPowerup(1, 10);
		afterValue = Inventory.powerupNumbers[1];
		assertEquals(afterValue - beforeValue, 10);
		
		beforeValue = Inventory.powerupNumbers[2];
		Inventory.addPowerup(2, 10);
		afterValue = Inventory.powerupNumbers[2];
		assertEquals(afterValue - beforeValue, 10);
		
		beforeValue = Inventory.powerupNumbers[3];
		Inventory.addPowerup(3, 10);
		afterValue = Inventory.powerupNumbers[3];
		assertEquals(afterValue - beforeValue, 10);
	}
	
	@Test
	void addPowerupTestInvalidInput() {
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
			Inventory.addPowerup(5, 10);
		});
		
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
			Inventory.addPowerup(6, 10);
		});
	}
	
	@Test
	void removePowerupTest() {
		//start with initial value
		Inventory.addPowerup(0, 10);
		Inventory.removePowerup(0, 4);
		assertEquals(Inventory.powerupNumbers[0], 6);
		
		Inventory.addPowerup(1, 10);
		Inventory.removePowerup(1, 2);
		assertEquals(Inventory.powerupNumbers[1], 8);
		
		Inventory.addPowerup(2, 10);
		Inventory.removePowerup(2, 3);
		assertEquals(Inventory.powerupNumbers[2], 7);
		
		Inventory.addPowerup(3, 10);
		Inventory.removePowerup(3, 6);
		assertEquals(Inventory.powerupNumbers[3], 4);
	}
	
	@Test
	void removePowerUpTestIfNoPowerupLeft() {
		Inventory.powerupNumbers[0] = 0;
		Inventory.powerupNumbers[1] = 0;
		Inventory.powerupNumbers[2] = 0;
		Inventory.powerupNumbers[3] = 0;
		assertFalse(Inventory.removePowerup(0, 4));
		assertFalse(Inventory.removePowerup(1, 2));
		assertFalse(Inventory.removePowerup(2, 3));
		assertFalse(Inventory.removePowerup(3, 6));
	}

}


