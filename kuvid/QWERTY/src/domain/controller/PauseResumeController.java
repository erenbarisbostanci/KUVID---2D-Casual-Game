package domain.controller;

import domain.utilities.StatisticTracker;

public class PauseResumeController {

	public PauseResumeController() {}

	public void pauseAction() {
		StatisticTracker.getStatisticTracker().pauseTimer();
	}
	
	public void resumeAction() {
		StatisticTracker.getStatisticTracker().resumeTimer();
	}
}
