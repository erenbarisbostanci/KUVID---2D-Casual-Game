package domain.controller;

import java.util.ArrayList;

import domain.ISaveLoadAdapter;

public class SaveLoadController {
	
	ISaveLoadAdapter saveLoadService;

	public SaveLoadController(ISaveLoadAdapter saveLoadService) {
		this.saveLoadService = saveLoadService;
	}
	
	public void saveGameTo(String save){
		saveLoadService.save(save);
	}
	
	public void loadGameFrom(String load){
		saveLoadService.load(load);
	}
	
	public ArrayList<String> getSaveFilesList(){
		return saveLoadService.getSaveFileNames();
	}
	
	public void removeSave(String remove) {
		saveLoadService.remove(remove);
	}

}
