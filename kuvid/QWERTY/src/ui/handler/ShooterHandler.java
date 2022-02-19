package ui.handler;

import domain.controller.ShooterController;

public class ShooterHandler {
	
	private static ShooterController DOMAIN_SHOOTER_CONTROLLER;
	
	public static void shooterKeyAction(String str) {
		
		if(DOMAIN_SHOOTER_CONTROLLER == null) {
			initControllers();
		}
		if(str.equals("UP")) {
			DOMAIN_SHOOTER_CONTROLLER.fireShooter();			
		}		
		if(str.equals("RIGHT")) {
			DOMAIN_SHOOTER_CONTROLLER.moveShooter(0);			
		}
		if(str.equals("LEFT")) {
			DOMAIN_SHOOTER_CONTROLLER.moveShooter(1);			
		}
		
		if(str.equals("D")) {
			DOMAIN_SHOOTER_CONTROLLER.rotateShooter(0);			
		}
		if(str.equals("A")) {
			DOMAIN_SHOOTER_CONTROLLER.rotateShooter(1);			
		}
		if(str.equals("C")) {
			DOMAIN_SHOOTER_CONTROLLER.loadRandomAtom();			
		}
		
	}
	
	public static void initControllers() {
		if(DOMAIN_SHOOTER_CONTROLLER == null) {
			DOMAIN_SHOOTER_CONTROLLER = new ShooterController();
		}
	}

	public static ShooterController getController() {
		if(DOMAIN_SHOOTER_CONTROLLER == null) {
			initControllers();
		}
		return DOMAIN_SHOOTER_CONTROLLER;
	}

}
