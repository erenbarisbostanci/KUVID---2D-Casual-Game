package domain.observer;

public interface IShooterListener extends IObjectListener {
	void onAngleUpdate(int angle);
	void onFireUpdate();
	void onAtomLoad(int typeId);

}
