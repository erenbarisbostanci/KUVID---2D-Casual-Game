package domain.utilities;

import java.util.Timer;

import domain.Time;
import domain.observer.IStatsListener;

public class StatisticTracker {
	
	private int health;
	private double score;
	private Timer time;
	private boolean timerRuns;
	private Time curTime;
	private int seconds;
	private String NICKNAME;
	
	private static StatisticTracker Stats;
	private IStatsListener listener;

	private StatisticTracker() {
		time = new Timer();
		curTime = new Time();
		time.schedule(curTime, 0, 100);
		this.score = 0.0;
		this.health = 100;
		this.timerRuns = true;
	}
	
	public static StatisticTracker getStatisticTracker() {
		if(Stats==null) {
			Stats = new StatisticTracker();
		}
		return Stats;
	}

	public void damage(int amount) {
		health -= amount;
		listener.onHealthUpdate(this.health);
	}
	
	public void score(double amount) {
		score += amount;
		listener.onScoreUpdate(this.score);
	}

	public void pauseTimer() {
		curTime.timerPause();
	}

	public void resumeTimer() {
		curTime.timerStart();
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
		listener.onHealthUpdate(this.health);
	}

	public double getScore() {
		return score;
	}
	
	public void setScore(double score) {
		this.score = Math.round(score * 100.0) / 100.0;;
		listener.onScoreUpdate(this.score);
	}

	public boolean isTimerRuns() {
		return timerRuns;
	}
	
	public Time getCurTime() {
		return curTime;
	}
	
	public void setSeconds(int seconds) {
		this.seconds = seconds;
		listener.onTimeUpdate(this.seconds);
	}

	public String getNickname() {
		return NICKNAME;
	}

	public void setNickname(String nickname) {
		NICKNAME = nickname;
		listener.onNicknameUpdate(nickname);
	}

	public void setListener(IStatsListener listener) {
		this.listener = listener;
		listener.onTimeUpdate(this.seconds);
		listener.onScoreUpdate(this.score);
		listener.onHealthUpdate(this.health);
	}

	public void endGame(String str) {
		listener.onGameEndUpdate(true, str, getScore());
	}

}
