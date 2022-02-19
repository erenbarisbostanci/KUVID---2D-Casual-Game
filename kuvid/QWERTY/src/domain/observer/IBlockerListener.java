package domain.observer;

public interface IBlockerListener extends IObjectListener{
	void onRectUpdate(int width, int height);
}
