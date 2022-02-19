package ui.objects;

import domain.Inventory;
import domain.observer.IStatsListener;
import domain.utilities.StatisticTracker;

public class StatsUIHolder implements IStatsListener{
	
	static int alpha;
	static int beta;
	static int sigma;
	static int gamma;
	static int alphaPU;
	static int betaPU;
	static int sigmaPU;
	static int gammaPU;
	static int eta;
	static int lota;
	static int theta;
	static int zeta;
	static int time;
	static double score;
	static int health;
	public static String NICKNAME;
	public static String END_MESSAGE;
	public static Double END_SCORE;
	public static boolean isEND = false;
	public static int LENGTH_L = 70;
	public static int GAME_HEIGHT = 10*LENGTH_L;
	public static int GAME_WIDTH = (int) (GAME_HEIGHT*1.5);
	public static int RIGHT_MENU_WIDTH = (int)(LENGTH_L*2.5);
	public static boolean isStraight = false;
	public static boolean areMoleculesSpin = false;
	public static boolean isGameRunning = true;
	

	@Override
	public void onItemCountUpdate(int alphaI, int betaI, int sigmaI, int gammaI) {
		alpha = alphaI;
		beta = betaI;
		sigma = sigmaI;
		gamma = gammaI;
	}

	@Override
	public void onTimeUpdate(int timeI) {
		time = timeI;
		
	}

	@Override
	public void onScoreUpdate(double scoreI) {
		score = scoreI;		
	}

	@Override
	public void onHealthUpdate(int healthI) {
		health = healthI;
	}

	public int getAlpha() {
		return alpha;
	}

	public int getBeta() {
		return beta;
	}

	public int getSigma() {
		return sigma;
	}

	public int getGamma() {
		return gamma;
	}

	public int getTime() {
		return time;
	}

	public double getScore() {
		return score;
	}

	public int getHealth() {
		return health;
	}
	
	@Override
	public void init(StatisticTracker tracker) {
		tracker.setListener(this);
	}
	
	@Override
	public void init(Inventory inventory) {
		Inventory.setListener(this);
	}

	public void setL(int lengthL) {
		LENGTH_L = lengthL;
		GAME_HEIGHT = 10*LENGTH_L;
		GAME_WIDTH = (int) (GAME_HEIGHT*1.5);
		RIGHT_MENU_WIDTH = (int)(LENGTH_L*2.5);
		
	}

	public int getWidth() {
		return GAME_WIDTH;
	}
	
	public int getHeight() {
		return GAME_HEIGHT;
	}

	@Override
	public void onShieldCountUpdate(int etaI, int lotaI, int thetaI, int zetaI) {
		eta = etaI;
		lota = lotaI;
		theta = thetaI;
		zeta = zetaI;
	}

	@Override
	public void onPowerupCountUpdate(int alpha, int beta, int sigma, int gamma) {
		alphaPU = alpha;
		betaPU = beta;
		sigmaPU = sigma;
		gammaPU = gamma;
	}
	
	@Override
	public void onNicknameUpdate(String nickname) {
		NICKNAME = nickname;
	}
	
	@Override 
	public void onGameEndUpdate(boolean isEnd, String message, Double score) {
		isEND = isEnd;
		END_MESSAGE = message;
		END_SCORE = score;
	}
	
	public int getEta() {
		return eta;
	}

	public int getLota() {
		return lota;
	}

	public int getTheta() {
		return theta;
	}

	public int getZeta() {
		return zeta;
	}

	public int getAlphaPU() {
		return alphaPU;
	}
	
	public int getBetaPU() {
		return betaPU;
	}
	
	public int getSigmaPU() {
		return sigmaPU;
	}
	
	public int getGammaPU() {
		return gammaPU;
	}


}
