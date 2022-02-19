package domain.observer;

public interface IObjectListener {
	void onCoordinateUpdate(int x, int y);
	void onSizeUpdate(int width, int height);

}
