package kuvid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import domain.Inventory;
import domain.ScreenObjects;
import domain.object.Shooter;
import domain.observer.IStatsListener;
import domain.utilities.Coordinate;
import domain.utilities.StaticFields;
import ui.objects.AnimationPanel;
import ui.objects.StatsUIHolder;

class ShooterTest {
	Shooter shooter = new Shooter(new Coordinate(100, 100));
	int ScreenRightBorder = StaticFields.SCREEN_WIDTH-StaticFields.RIGHT_MENU_WIDTH;
	int ScreenLeftBorder = 0;
	int ExcpectedShooterMovementDistance = StaticFields.LENGTH_L/9;
	
	@BeforeAll
	static void init() {
		IStatsListener listener = new StatsUIHolder();
		ScreenObjects.getScreenObjects().setListener(new AnimationPanel(1000, 1000));
		Inventory.setListener(listener);
		Inventory.addAtom(0, 10);
		Inventory.addAtom(1, 10);
		Inventory.addAtom(2, 10);
		Inventory.addAtom(3, 10);
	}
	
	
	/*
	 * is bullet move same length with shooter
	 */
	@Test
	void moveLeftTestsBulletMovementWithShooter() {
		shooter.loadAtom();
		int coorX = shooter.getCoordinate().getX();
		int bulletCoorX = shooter.getBullet().getCoordinate().getX();
		
		shooter.moveLeft();
		
		int coorNewX = shooter.getCoordinate().getX();
		int bulletCoorNewX = shooter.getBullet().getCoordinate().getX();
		
		assertEquals(coorX - coorNewX, bulletCoorX - bulletCoorNewX);
	}
	@Test
	void moveRightTestsBulletMovementWithShooter() {
		shooter.loadAtom();
		int coorX = shooter.getCoordinate().getX();
		int bulletCoorX = shooter.getBullet().getCoordinate().getX();
		
		shooter.moveRight();
		
		int coorNewX = shooter.getCoordinate().getX();
		int bulletCoorNewX = shooter.getBullet().getCoordinate().getX();
		
		assertEquals(coorX - coorNewX, bulletCoorX - bulletCoorNewX);
	}
	
	
	
	/*
	 * is displacement value correct
	 */
	@Test
	void moveLeftTestsMovementValue() {
		int coorX = shooter.getCoordinate().getX();
		
		shooter.moveLeft();
		
		int coorNewX = shooter.getCoordinate().getX();
		
		assertEquals(ExcpectedShooterMovementDistance, coorX - coorNewX);
	}
	@Test
	void moveRightTestsMovementValue() {
		int coorX = shooter.getCoordinate().getX();
		
		shooter.moveRight();
		
		int coorNewX = shooter.getCoordinate().getX();
		
		assertEquals(ExcpectedShooterMovementDistance, coorNewX - coorX);
	}
	
	
	
	/*
	 * if shooter at left border, will it still move
	 */
	@Test
	void moveLeftTestsWillMoveOutOfScreen() {
		shooter.setCoordinate(ScreenLeftBorder, 100);
		int coorX = shooter.getCoordinate().getX();
		shooter.moveLeft();
		int coorNewX = shooter.getCoordinate().getX();
		
		assertTrue(coorX==coorNewX);
	}
	@Test
	void moveRightTestsWillMoveOutOfScreen() {
		shooter.setCoordinate(ScreenRightBorder, 100);
		int coorX = shooter.getCoordinate().getX();
		shooter.moveRight();
		int coorNewX = shooter.getCoordinate().getX();
		
		assertTrue(coorX==coorNewX);
	}
	
	
	/*
	 * will movement effects shooter's angle
	 */
	@Test
	void moveLeftTestsWillAnglePreserved() {
		int angle = shooter.getAngle();
		shooter.moveLeft();
		int newAngle = shooter.getAngle();
		
		assertEquals(angle, newAngle);
	}
	@Test
	void moveRightTestsWillAnglePreserved() {
		int angle = shooter.getAngle();
		shooter.moveRight();
		int newAngle = shooter.getAngle();
		
		assertEquals(angle, newAngle);
	}
	
	
	
	/*
	 * checks when input is smaller or bigger than precondition, will it rotate
	 */
	@Test
	void rotateShooterTestsBounds() {
		//for up bound
		int angle = shooter.getAngle();
		int testRotAngle = 180 - shooter.getAngle() +1;
		shooter.rotateShooter(testRotAngle);
		assertEquals(angle, shooter.getAngle());
		assertFalse(shooter.rotateShooter(testRotAngle));
		
		//for low bound
		angle = shooter.getAngle();
		testRotAngle = shooter.getAngle() * (-1) - 1;
		shooter.rotateShooter(testRotAngle);
		assertEquals(angle, shooter.getAngle());
		assertFalse(shooter.rotateShooter(testRotAngle));
	}

}
