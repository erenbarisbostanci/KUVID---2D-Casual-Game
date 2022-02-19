package kuvid;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import domain.factories.DropperFactory;
import domain.object.AlphaBlocker;
import domain.object.AlphaMolecule;
import domain.object.BetaBlocker;
import domain.object.BetaMolecule;
import domain.object.Blocker;
import domain.object.GammaBlocker;
import domain.object.GammaMolecule;
import domain.object.Molecule;
import domain.object.Powerup;
import domain.object.SigmaBlocker;
import domain.object.SigmaMolecule;
import domain.utilities.Coordinate;
import domain.utilities.StaticFields;

public class DropperFactoryTest {
	static Random random;
	Coordinate coor;
	int typeId;
	Molecule molecule;
	Powerup powerup;
	Blocker blocker;
	
	@BeforeAll
	static void init() {
		random = new Random();
	}
	
	/* BlackBox
	 * Tests whether generated molecule is the given type type of molecule
	 */
	@Test
	void generateMoleculeTestofType() {
		
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		molecule = DropperFactory.getDropperFactory().generateMolecule(coor, 0);
		
		assertTrue(molecule instanceof AlphaMolecule);
		
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		molecule = DropperFactory.getDropperFactory().generateMolecule(coor, 1);
		
		assertTrue(molecule instanceof BetaMolecule);
		
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		molecule = DropperFactory.getDropperFactory().generateMolecule(coor, 2);
		
		assertTrue(molecule instanceof GammaMolecule);
		
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		molecule = DropperFactory.getDropperFactory().generateMolecule(coor, 3);
		
		assertTrue(molecule instanceof SigmaMolecule);
	}
	
	/* BlackBox
	 * Tests whether generated molecule is located on the given coordinates
	 */
	@Test
	void generateMoleculeTestofInitialCoordinates() {
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		molecule = DropperFactory.getDropperFactory().generateMolecule(coor, random.nextInt(4));
		
		assertEquals(coor, molecule.getCoordinate());
	}
	
	/* GlassBox
	 * Tests whether an invalid typeId selection returns null
	 */
	@Test
	void generateMoleculeTestofInvalidTypeid() {
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		molecule = DropperFactory.getDropperFactory().generateMolecule(coor, 4);
		
		assertNull(molecule);
	}
	
	/* BlackBox
	 * Tests whether generated powerup is the given type type of molecule
	 */
	@Test
	void generatePowerupTestofType() {
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		powerup = DropperFactory.getDropperFactory().generatePowerup(coor, 0);
		
		assertEquals(0, powerup.getTypeId());
		
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		powerup = DropperFactory.getDropperFactory().generatePowerup(coor, 1);
		
		assertEquals(1, powerup.getTypeId());
		
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		powerup = DropperFactory.getDropperFactory().generatePowerup(coor, 2);
		
		assertEquals(2, powerup.getTypeId());
		
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		powerup = DropperFactory.getDropperFactory().generatePowerup(coor, 3);
		
		assertEquals(3, powerup.getTypeId());
	}
	
	/* BlackBox
	 * Tests whether generated powerup is located on the given coordinates
	 */
	@Test
	void generatePowerupTestofInitialCoordinates() {
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		powerup = DropperFactory.getDropperFactory().generatePowerup(coor, random.nextInt(4));
		
		assertEquals(coor, powerup.getCoordinate());
	}
	
	/* GlassBox
	 * Tests whether an invalid typeId selection returns null
	 */
	@Test
	void generatePowerupTestofInvalidTypeid() {
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		powerup = DropperFactory.getDropperFactory().generatePowerup(coor, 4);
		
		assertNull(powerup);
	}
	
	/* BlackBox
	 * Tests whether generated blocker is the given type type of molecule
	 */
	@Test
	void generateBlockerTestofType() {
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		blocker = DropperFactory.getDropperFactory().generateBlocker(coor, 0);
		
		assertTrue(blocker instanceof AlphaBlocker);
		
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		blocker = DropperFactory.getDropperFactory().generateBlocker(coor, 1);
		
		assertTrue(blocker instanceof BetaBlocker);
		
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		blocker = DropperFactory.getDropperFactory().generateBlocker(coor, 2);
		
		assertTrue(blocker instanceof GammaBlocker);
		
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		blocker = DropperFactory.getDropperFactory().generateBlocker(coor, 3);
		
		assertTrue(blocker instanceof SigmaBlocker);
	}
	
	/* BlackBox
	 * Tests whether an invalid typeId selection returns null
	 */
	@Test
	void generateBlockerTestofInitialCoordinates() {
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		blocker = DropperFactory.getDropperFactory().generateBlocker(coor, random.nextInt(4));
		
		assertEquals(coor, blocker.getCoordinate());
	}
	
	/* GlassBox
	 * Tests whether generated blocker is located on the given coordinates
	 */
	@Test
	void generateBlockerTestofInvalidTypeid() {
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		blocker = DropperFactory.getDropperFactory().generateBlocker(coor, 4);
		
		assertNull(blocker);
	}
}