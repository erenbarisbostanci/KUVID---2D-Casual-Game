package domain.observer;

import java.util.ArrayList;

public interface IAtomListener extends IObjectListener {
	void onShieldUpdate(ArrayList<Integer> shields);
}
