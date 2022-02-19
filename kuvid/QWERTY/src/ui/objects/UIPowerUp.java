package ui.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;

import domain.object.Powerup;
import domain.observer.IObjectListener;

public class UIPowerUp extends UIObjectBase implements IObjectListener{
	
	private static String pathA = "./images/powerups/+alpha-b.png";
	private static String pathB = "./images/powerups/+beta-b.png";
	private static String pathG = "./images/powerups/+gamma-b.png";
	private static String pathS = "./images/powerups/+sigma-b.png";

	public UIPowerUp(int x, int y, int width, int height, int type) {
		super(x, y, width, height, pathA);
		switch (type) {
			case 0:
				setImage(pathA);
				break;
			case 1:
				setImage(pathB);
				break;
			case 2:
				setImage(pathG);
				break;
			case 3:
				setImage(pathS);
				break;
		}
	}

	@Override
	public void draw(Graphics g) {
		g.translate(x, y);
		((Graphics2D) g).drawImage(img, null, null);
		g.translate(-x, -y);
	}
	
	@Override
	public void onCoordinateUpdate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void onSizeUpdate(int width, int height) {
		this.width = width;
		this.height = height;		
	}
	
	public void initialize(Powerup powerup) {	
    	powerup.setListener(this);
	}

}