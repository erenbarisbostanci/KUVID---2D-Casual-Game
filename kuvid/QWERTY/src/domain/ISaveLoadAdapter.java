package domain;

import java.util.ArrayList;

public interface ISaveLoadAdapter {
	
	void save(String save);
	void load(String load);
	ArrayList<String> getSaveFileNames();
	void remove(String remove);
}
