package domain.controller;

import domain.Instancer;
import domain.observer.IStatsListener;

public class InstancerController {
	private int length;
	private int moleculeCount;
	private int powerUpCount;
	private int blockerCount;
	private int atomNumber;
	private int shieldNumber;
	private String difficulty;
	private String nickname;
	
	public InstancerController() {
		//these numbers are for just one type
		this.moleculeCount = 100;
		this.atomNumber = 100;
		this.blockerCount = 10;
		this.powerUpCount = 20;
		this.shieldNumber = 40;
		this.length = 70;
		this.nickname = "Noname";
	}

	public int getL() {
		return length;
	}

	public void setL(int length) {
		this.length = length;
	}

	public int getMoleculeCount() {
		return moleculeCount;
	}

	public void setMoleculeCount(int moleculeCount) {
		this.moleculeCount = moleculeCount;
	}

	public int getPowerUpCount() {
		return powerUpCount;
	}

	public void setPowerUpCount(int powerUpCount) {
		this.powerUpCount = powerUpCount;
	}

	public int getBlockerCount() {
		return blockerCount;
	}

	public void setBlockerCount(int blockerCount) {
		this.blockerCount = blockerCount;
	}

	public int getAtomNumber() {
		return atomNumber;
	}

	public void setAtomNumber(int atomNumber) {
		this.atomNumber = atomNumber;
	}
	
	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String dif) {
		this.difficulty = dif;
	}
	
	public int getShieldNumber() {
		return shieldNumber;
	}
	
	public void setShieldNumber(int shieldNumber) {
		this.shieldNumber = shieldNumber;
	}
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void createInstancer(IStatsListener listener) {
		Instancer start = new Instancer(length, moleculeCount, powerUpCount, blockerCount, atomNumber, shieldNumber, difficulty, nickname);
		start.setStatsListener(listener);
		start.initialize();
	}
}
