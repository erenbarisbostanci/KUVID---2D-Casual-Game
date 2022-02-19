package domain.observer;

import domain.Inventory;
import domain.utilities.StatisticTracker;

public interface IStatsListener {
	void onItemCountUpdate(int alpha, int beta, int sigma, int gamma);
	void onShieldCountUpdate(int alpha, int beta, int sigma, int gamma);
	void onPowerupCountUpdate(int alpha, int beta, int sigma, int gamma);
	void onTimeUpdate(int time);
	void onScoreUpdate(double score);
	void onHealthUpdate(int health);
	void init(StatisticTracker tracker);
	void init(Inventory inventory);
	void onNicknameUpdate(String nickname);
	void onGameEndUpdate(boolean isEnd, String message, Double score);
}
