package domain;

import domain.observer.IStatsListener;
import domain.utilities.StaticFields;
import domain.utilities.StatisticTracker;

public final class Instancer {

	private int length;
	private int moleculeCount;
	private int powerUpCount;
	private int blockerCount;
	private int atomNumber;
	private int shieldNumber;
	private String difficulty;
	private String nickname;
	private IStatsListener statsListener;

	public Instancer(int length, int moleculeCount, int powerUpCount, int blockerCount, int atomNumber,
			int shieldNumber, String difficulty, String nickname) {
		this.length = length;
		this.moleculeCount = moleculeCount;
		this.powerUpCount = powerUpCount;
		this.blockerCount = blockerCount;
		this.atomNumber = atomNumber;
		this.shieldNumber = shieldNumber;
		this.difficulty = difficulty;
		this.nickname = nickname;
	}

	private void loadInventory() {
		Inventory.setListener(statsListener);

		if (this.atomNumber < 1)
			this.atomNumber = 100;
		for (int i = 0; i < 4; i++) {
			Inventory.addAtom(i, this.atomNumber);
			Inventory.addPowerup(i, this.powerUpCount);
			Inventory.addShield(i, this.shieldNumber);
		}

	}

	private void setInitialCounts() {
		for (int i = 0; i < 4; i++) {
			StaticFields.MOLECULES[i] = this.moleculeCount;
			StaticFields.BLOCKERS[i] = this.blockerCount;
			StaticFields.POWERUPS[i] = this.powerUpCount;
		}

		StaticFields.LENGTH_L = this.length;
		StaticFields.SCREEN_HEIGHT = 10 * this.length;
		StaticFields.SCREEN_WIDTH = (int) (StaticFields.SCREEN_HEIGHT * 1.5);
		StaticFields.RIGHT_MENU_WIDTH = (int) (StaticFields.LENGTH_L * 2.5);
	}

	public void setStatsListener(IStatsListener statsListener) {
		this.statsListener = statsListener;
	}

	/*
	 * Requires: Valid input values of length, molecule count, powerup count,
	 * blockercount, atomnumber and difficulty. Modifies: Inventory, Shooter;
	 * StaticFields, StatisticsTracker, DropperFactory, GameScreen. Effects: Sets up
	 * the initial parameters of the gameplay using the input values passed from the
	 * UIHandler.
	 */

	public void initialize() {
		loadInventory();
		setInitialCounts();
		ScreenObjects.getScreenObjects().getListener().setShooter(ScreenObjects.getScreenObjects().getShooter());
		ScreenObjects.getScreenObjects().getShooter().setLocation(StaticFields.SCREEN_WIDTH/2,(StaticFields.SCREEN_HEIGHT-StaticFields.LENGTH_L));
		ScreenObjects.getScreenObjects().getShooter().loadAtom();

		switch (difficulty.toLowerCase()) {
		case "hard":
			StaticFields.difficulty = 3;
			break;
		case "medium":
			StaticFields.difficulty = 5;
			break;
		case "easy":
		default:
			StaticFields.difficulty = 10;
			break;
		}
		StatisticTracker.getStatisticTracker();
		statsListener.init(StatisticTracker.getStatisticTracker());
		StatisticTracker.getStatisticTracker().setNickname(this.nickname);
	}
}
