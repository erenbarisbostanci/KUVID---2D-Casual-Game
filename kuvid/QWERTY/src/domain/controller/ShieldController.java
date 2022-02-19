package domain.controller;

import domain.Inventory;
import domain.ScreenObjects;
import domain.object.*;

public class ShieldController {

	public ShieldController() {}
	
	public void addEta() {
		if (ScreenObjects.getScreenObjects().getShooter().getBullet() instanceof Atom &&
				Inventory.shieldNumbers[0] != 0) {
			ScreenObjects.getScreenObjects().getShooter().setBullet(new Eta((Atom) ScreenObjects.getScreenObjects().getShooter().getBullet()));
			Inventory.removeShield(0, 1);

		}	
	}
	
	public void addLota() {
		if (ScreenObjects.getScreenObjects().getShooter().getBullet() instanceof Atom &&
				Inventory.shieldNumbers[1] != 0) {
			ScreenObjects.getScreenObjects().getShooter().setBullet(new Lota((Atom) ScreenObjects.getScreenObjects().getShooter().getBullet()));
			Inventory.removeShield(1, 1);

		}
	}
	
	public void addTheta() {
		if (ScreenObjects.getScreenObjects().getShooter().getBullet() instanceof Atom &&
				Inventory.shieldNumbers[2] != 0) {
			ScreenObjects.getScreenObjects().getShooter().setBullet(new Theta((Atom) ScreenObjects.getScreenObjects().getShooter().getBullet()));
			Inventory.removeShield(2, 1);

		}
	}

	public void addZeta() {
		if (ScreenObjects.getScreenObjects().getShooter().getBullet() instanceof Atom &&
				Inventory.shieldNumbers[3] != 0) {
			ScreenObjects.getScreenObjects().getShooter().setBullet(new Zeta((Atom) ScreenObjects.getScreenObjects().getShooter().getBullet()));
			Inventory.removeShield(3, 1);

		}
	}
}
