package domain.controller;

import domain.ScreenObjects;

public class ShooterController {
	/*
	 * if i is 0 than rotate shooter to right
	 * if i is 1 than rotate shooter to left
	 */
	public boolean moveShooter(int i) {
		if(i == 0) {
			return ScreenObjects.getScreenObjects().getShooter().moveRight();				
		}else if(i == 1) {
			return ScreenObjects.getScreenObjects().getShooter().moveLeft();				
		}
		return false;
	}
	
	public int getX() {
		return ScreenObjects.getScreenObjects().getShooter().getCoordinate().getX();
	}
	
	public int getY() {
		return ScreenObjects.getScreenObjects().getShooter().getCoordinate().getY();
	}
	
	public int getheight() {
		return ScreenObjects.getScreenObjects().getShooter().getHeight();
	}
	
	public int getwidth() {
		return ScreenObjects.getScreenObjects().getShooter().getWidth();
	}
	
	public int getAngle() {
		return ScreenObjects.getScreenObjects().getShooter().getAngle();
	}
	
	
	/*
	 * if i is 0 than rotate shooter to right
	 * if i is 1 than rotate shooter to left
	 */
	public boolean rotateShooter(int i) {
		if(i == 0) {
			return ScreenObjects.getScreenObjects().getShooter().rotateShooter(10);				
		}else if(i == 1) {
			return ScreenObjects.getScreenObjects().getShooter().rotateShooter(-10);				
		}
		return false;
	}
	
	public void fireShooter() {
		ScreenObjects.getScreenObjects().getShooter().shootItem();
	}

	public void loadRandomAtom() {
        ScreenObjects.getScreenObjects().getShooter().removeBullet();
        ScreenObjects.getScreenObjects().getShooter().loadAtom();
    }

}
