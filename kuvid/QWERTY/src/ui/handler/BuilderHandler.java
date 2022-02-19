package ui.handler;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import domain.ScreenObjects;
import domain.controller.InstancerController;
import domain.observer.ICreationListener;
import domain.observer.IStatsListener;

public class BuilderHandler {
	IStatsListener statisticHolder;
	
	public void sendCommand(ActionEvent e) {
	}
	public void sendCommandStringArr(String[] e) {
		//atomnumber powerup molecule reactionblocker length difficulty
		InstancerController insControl=new InstancerController();
		insControl.setAtomNumber(Integer.parseInt(e[0]));
		insControl.setPowerUpCount(Integer.parseInt(e[1]));
		insControl.setMoleculeCount(Integer.parseInt(e[2]));
		insControl.setBlockerCount(Integer.parseInt(e[3]));
		insControl.setShieldNumber(Integer.parseInt(e[5]));
		insControl.setDifficulty(e[6]);
		if(e[4].length()!=0&&(Integer.parseInt(e[4])>69)) {
			insControl.setL(Integer.parseInt(e[4]));
		}
		else {
			JFrame f=new JFrame();  
			JOptionPane.showMessageDialog(f,"Invalid length, initializing with default value.");  
		}
		insControl.setNickname(e[7]);
		insControl.createInstancer(statisticHolder);
	}
	
	public void sendCreationListener(ICreationListener listener) {
		ScreenObjects.getScreenObjects().setListener(listener);
	}
	public void sendStatisticListener(IStatsListener statisticHolder) {
		this.statisticHolder = statisticHolder;
	}
}
