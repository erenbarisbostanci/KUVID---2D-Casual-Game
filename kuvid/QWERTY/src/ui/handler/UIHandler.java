package ui.handler;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import domain.controller.BlenderController;
import domain.controller.PauseResumeController;
import domain.controller.PowerupController;
import domain.controller.ShieldController;
import ui.objects.StatsUIHolder;
import ui.swing.GameScreen;

public class UIHandler {
	private static String saveLoadSystem;
	private static ShieldController SHIELD_CONTROLLER = new ShieldController();
	private static BlenderController BLENDER_CONTROLLER = new BlenderController();
	private static PauseResumeController PAUSE_RESUME_CONTROLLER = new PauseResumeController();
	private static PowerupController POWERUP_CONTROLLER = new PowerupController();

	public void sendCommand(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Eta")) {
			SHIELD_CONTROLLER.addEta();
		} else if (e.getActionCommand().equalsIgnoreCase("Lota")) {
			SHIELD_CONTROLLER.addLota();
		} else if (e.getActionCommand().equalsIgnoreCase("Theta")) {
			SHIELD_CONTROLLER.addTheta();
		} else if (e.getActionCommand().equalsIgnoreCase("Zeta")) {
			SHIELD_CONTROLLER.addZeta();
		} 
	}

	public void sendBlend() {
		BLENDER_CONTROLLER.blendAction(GameScreen.getRadioUse(), GameScreen.getRadioCreate());
	}

	public void sendCommandString(String e) {
		if(StatsUIHolder.isGameRunning) {
			ShooterHandler.shooterKeyAction(e);
		}
		if(e.equalsIgnoreCase("S") || e.equalsIgnoreCase("L")) {
			sendCommandSaveLoadCommand(e);
		}
		if(e.equalsIgnoreCase("P")) {
			sendCommandPause(true);
		}
		if(e.equalsIgnoreCase("R")) {
			sendCommandResume();
		}
	}

	public void sendCommandSaveLoadCommand(String e) {
		SaveLoadHandler.setUIHandler(this);
		SaveLoadHandler.keyAction(e);		
	}

	public void sendCommandPause(boolean willMessage) {
		PAUSE_RESUME_CONTROLLER.pauseAction();
		StatsUIHolder.isGameRunning=false;
		if(willMessage) {
			JFrame f=new JFrame();  
			JOptionPane.showMessageDialog(f,"PAUSED press R to resume"); 
		}
	}

	public void sendCommandResume() {	
		PAUSE_RESUME_CONTROLLER.resumeAction();
		StatsUIHolder.isGameRunning=true;
	}

	public void sendPowerupAction(int typeId) {
		POWERUP_CONTROLLER.selectPowerup(typeId);
	}

	public static void setSaveLoadSystem(String saveLoadSystem) {
		UIHandler.saveLoadSystem = saveLoadSystem;
		SaveLoadHandler.first();
	}
	
	public static String getSaveLoadSystem() {
		return saveLoadSystem;
	}




}
