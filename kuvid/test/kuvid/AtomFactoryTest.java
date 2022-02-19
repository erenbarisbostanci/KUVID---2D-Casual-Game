package kuvid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import domain.factories.AtomFactory;
import domain.object.AlphaAtom;
import domain.object.Atom;
import domain.object.BetaAtom;
import domain.object.Blocker;
import domain.object.GammaAtom;
import domain.object.Molecule;
import domain.object.Powerup;
import domain.object.SigmaAtom;
import domain.utilities.Coordinate;
import domain.utilities.StaticFields;

public class AtomFactoryTest {
	static Random random;
	Coordinate coor;
	int typeId;
	Molecule molecule;
	Powerup powerup;
	Blocker blocker;
	Atom atom;
	
	@BeforeAll
	static void init() {
		random = new Random();
	}
	
	@Test
	void generateAtomTestofType() {
		
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		atom = AtomFactory.getAtomFactory().generateAtom(coor, 0);
		
		assertTrue(atom instanceof AlphaAtom);
		
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		atom = AtomFactory.getAtomFactory().generateAtom(coor, 1);
		
		assertTrue(atom instanceof BetaAtom);
		
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		atom = AtomFactory.getAtomFactory().generateAtom(coor, 2);
		
		assertTrue(atom instanceof GammaAtom);
		
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		atom = AtomFactory.getAtomFactory().generateAtom(coor, 3);
		
		assertTrue(atom instanceof SigmaAtom);
	}
	
	@Test
	void proton() {
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		atom = AtomFactory.getAtomFactory().generateAtom(coor, 0);
		
		assertEquals(8, atom.getProtons());
		
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		atom = AtomFactory.getAtomFactory().generateAtom(coor, 1);
		
		assertEquals(16, atom.getProtons());
		
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		atom = AtomFactory.getAtomFactory().generateAtom(coor, 2);
		
		assertEquals(32, atom.getProtons());
		
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		atom = AtomFactory.getAtomFactory().generateAtom(coor, 3);
		
		assertEquals(64, atom.getProtons());
	}
	
	@Test
	void neutron() {
		coor = new Coordinate(random.nextInt(StaticFields.SCREEN_WIDTH), 0);
		atom = AtomFactory.getAtomFactory().generateAtom(coor, 1);
		
		assertEquals(64, atom.getNeutrons());
	}
}
