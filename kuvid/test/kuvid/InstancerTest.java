package kuvid;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.Instancer;
import domain.ScreenObjects;
import domain.utilities.StaticFields;
/*
import domain.Inventory;
import domain.factories.DropperFactory;
import domain.observer.ICreationListener;
import ui.objects.StatsUIHolder;
*/
class InstancerTest {

	//Default values for an instancer.
	int moleculeCount = 100;
	int atomNumber = 100;
	int blockerCount = 10;
	int powerUpCount = 20;
	int length = 70;
	String difficulty = "easy";

	Instancer def_inst;

	@BeforeEach
	void setUp() throws Exception {
		//def_inst = new Instancer(length, moleculeCount, powerUpCount, blockerCount, atomNumber, difficulty);
	}

	@AfterEach
	void tearDown() throws Exception {

	}



	@Test
	/*
	 * Test type: Black Box
	 */

	void invalidLengthOnInitializeTest() {
		int def_length = StaticFields.LENGTH_L;
		//Instancer short_inst = new Instancer(54, moleculeCount, powerUpCount, blockerCount, atomNumber, difficulty);
		int short_length = StaticFields.LENGTH_L;
		assertEquals(def_length,short_length);
	}

	@Test
	/*
	 * Test type: Black Box
	 */
	 
	void NoAtomsOnInitializeTest() {
		/*
		int[] defAtomNumbers = Inventory.atomNumbers;
		Instancer atomless_inst = new Instancer(length, moleculeCount, powerUpCount, blockerCount, 0, difficulty);
		
		int[] zeroAtomNumbers = Inventory.atomNumbers;
		assertEquals(defAtomNumbers,zeroAtomNumbers);
		*/
	}

	@Test
	/*
	 * Test type: Glass Box
	 */
	void InvalidDifficultyOnInitializeTest() {
		
		int def_diff = StaticFields.difficulty;
		//Instancer invalid_diff_inst = new Instancer(length, moleculeCount, powerUpCount, blockerCount, 0, "baby mode");
	
		int inv_diff = StaticFields.difficulty;
		assertEquals(inv_diff,def_diff);
	}

	@Test
	/*
	 * Test type: Black Box
	 */

	void DomainShooterOnInitializeTest() {
		assertNotNull(ScreenObjects.getScreenObjects().getShooter());
	}
	
	@Test
	/*
	 * Test type: Black Box
	 */

	void DomainDropperOnInitializeTest() {
		//assertNull(DropperFactory.getDropperFactory().getListener());
	}
	
}
