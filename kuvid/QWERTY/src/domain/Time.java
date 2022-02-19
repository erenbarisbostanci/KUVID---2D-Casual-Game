package domain;

import java.util.Random;
import java.util.TimerTask;

import domain.utilities.*;

public class Time extends TimerTask {
	volatile long time = 0;
	public boolean timerRuns = true;
	private static Random random = new Random();

	@Override
	public void run() {
		if (timerRuns) {
			time += 1;
			checkIfGameEnd();
			/*if (random.nextInt() % 40 == 0 && StaticFields.X > 0) {
				ScreenObjects.SHOOTER.shootItem();			//SHOT ATOM CHECK***********
				StaticFields.X--;
				}*/
			//35 is medium
			//25 is easy
			//50 is hard
			if(time%StaticFields.difficulty==0) {
				Coordinate coor = new Coordinate(random.nextInt((int) (StaticFields.SCREEN_WIDTH-StaticFields.RIGHT_MENU_WIDTH)), 0);
				int typeId;
				switch(random.nextInt(3)) {
				case 0:
					typeId = create(hasMolecule(), StaticFields.MOLECULES);
					if (typeId != -1) ScreenObjects.getScreenObjects().addMolecule(coor, typeId);
					break;
			
					/*while(hasMolecule()) {
						typeId = random.nextInt(4);			//DROP ITEM CHECK************
						if(StaticFields.MOLECULES[typeId] > 0) {
							ScreenObjects.getScreenObjects().addMolecule(coor, typeId);
							StaticFields.MOLECULES[typeId]--;
							break;
						}
					}*/ 
				case 1: 
					typeId = create(hasPowerUp(), StaticFields.POWERUPS);
					if (typeId != -1) ScreenObjects.getScreenObjects().addPowerup(coor, typeId);
					break;
				default:
					if(time%3==0) {
						
						typeId = create(hasBlocker(), StaticFields.BLOCKERS);
						if (typeId != -1) ScreenObjects.getScreenObjects().addBlocker(coor, typeId);
					}
					break;
				}
				/*while(hasMolecule()) {
					typeId = random.nextInt(4);			//DROP ITEM CHECK************
					if(StaticFields.MOLECULES[typeId] > 0) {
						ScreenObjects.getScreenObjects().addMolecule(coor, typeId);
						StaticFields.MOLECULES[typeId]--;
						break;
					}
				}*/
			}
			ScreenObjects.getScreenObjects().update();
			StatisticTracker.getStatisticTracker().setSeconds((int) time/10);
		}
	}
	
	private void checkIfGameEnd() {
		
		if(StatisticTracker.getStatisticTracker().getHealth() <= 0) {
			StatisticTracker.getStatisticTracker().endGame("GAME OVER");
			timerRuns = false;
		}else {
			boolean bool = true;
			for(int i = 0; i < 4; i++) {
				if(StaticFields.BLOCKERS[i] != 0) {
					bool = false;
				}
				if(StaticFields.POWERUPS[i] != 0) {
					bool = false;
				}
				if(StaticFields.MOLECULES[i] != 0) {
					bool = false;
				}
			}
			
			if(bool) {
				if(ScreenObjects.getScreenObjects().getATOMS().isEmpty() && ScreenObjects.getScreenObjects().getMOLECULES().isEmpty()
						&& ScreenObjects.getScreenObjects().getPOWERUPS().isEmpty() && ScreenObjects.getScreenObjects().getMOLECULES().isEmpty()) {
					StatisticTracker.getStatisticTracker().endGame("Congratulations you have cured KUVID-19");
					timerRuns = false;
				}
			}			
		}
		
	}

	private int create(boolean has, int[] stocks) {
		int typeID = -1;
		
		while(has) {
			typeID = random.nextInt(4);
			if(stocks[typeID] > 0) {
				stocks[typeID]--;
				break;
			}	
		}
		return typeID;
	}
	
	

	public boolean hasMolecule() {
		if(StaticFields.MOLECULES[0] > 0 || StaticFields.MOLECULES[1] > 0 ||
				StaticFields.MOLECULES[2] > 0 ||StaticFields.MOLECULES[3] > 0) {
			return true;
		}
		return false;
	}

	public boolean hasPowerUp() {
		if(StaticFields.POWERUPS[0] > 0 || StaticFields.POWERUPS[1] > 0 ||
				StaticFields.POWERUPS[2] > 0 ||StaticFields.POWERUPS[3] > 0) {
			return true;
		}
		return false;
	}

	public boolean hasBlocker() {
		if(StaticFields.BLOCKERS[0] > 0 || StaticFields.BLOCKERS[1] > 0 ||
				StaticFields.BLOCKERS[2] > 0 ||StaticFields.BLOCKERS[3] > 0) {
			return true;
		}
		return false;
	}

	public void timerPause() {
		timerRuns=false;
	}

	public void timerStart() {
		timerRuns=true;
	}

	public long getSecond() {
		return time /10;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
