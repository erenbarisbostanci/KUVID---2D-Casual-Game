package ui.handler;

import javax.swing.JFrame;

import domain.ISaveLoadAdapter;
import domain.controller.SaveLoadController;
import ui.swing.EnterSaveNameFrame;
import ui.swing.LoadFileSelectorFrame;

public class SaveLoadHandler {
	
	private static SaveLoadController DOMAIN_SAVE_LOAD_CONTROLLER;
	private static JFrame frame;
	private static UIHandler UIhandler;
	
	public static void keyAction(String str) {
		first();
		if(str.equals("S")) {
			UIhandler.sendCommandPause(false);
			frame = new EnterSaveNameFrame(UIhandler);
			frame.setVisible(true);
		}		
		if(str.equals("L")) {
			UIhandler.sendCommandPause(false);
			frame = new LoadFileSelectorFrame(UIhandler);
			frame.setVisible(true);
		}		
	}

	public static void save(String name) {
		first();
		DOMAIN_SAVE_LOAD_CONTROLLER.saveGameTo(name);
		frame.dispose();
		UIhandler.sendCommandResume();
	}

	public static void load(String name) {
		first();
		DOMAIN_SAVE_LOAD_CONTROLLER.loadGameFrom(name);
		frame.dispose();
		UIhandler.sendCommandResume();
	}
	
	public static void remove(String name) {
		first();
		DOMAIN_SAVE_LOAD_CONTROLLER.removeSave(name);
	}

	public static SaveLoadController getController() {
		first();
		return DOMAIN_SAVE_LOAD_CONTROLLER;
	}
	
	public static void first() {
		if(DOMAIN_SAVE_LOAD_CONTROLLER == null) {
			String clss;
			if(UIHandler.getSaveLoadSystem() != null) {
				clss = "domain." + UIHandler.getSaveLoadSystem();
				ISaveLoadAdapter system;
				try {
					system = (ISaveLoadAdapter) Class.forName(clss).getConstructor().newInstance();
					DOMAIN_SAVE_LOAD_CONTROLLER = new SaveLoadController(system);
					System.out.println("Will use "+ UIHandler.getSaveLoadSystem());
				} catch (Exception e) {
					System.out.println("No such system called: "+ UIHandler.getSaveLoadSystem());
					clss = "domain." + "SaveLoadFileSystem";
				}
				try {
					system = (ISaveLoadAdapter) Class.forName(clss).getConstructor().newInstance();
					DOMAIN_SAVE_LOAD_CONTROLLER = new SaveLoadController(system);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				//if no java argument, then use file system
				UIHandler.setSaveLoadSystem("SaveLoadFileSystem");
			}
		}
	}

	public static void setUIHandler(UIHandler uihandler) {
		UIhandler = uihandler;
	}
}
