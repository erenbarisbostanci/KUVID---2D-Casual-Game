package ui.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import domain.object.Shooter;
import domain.observer.IShooterListener;

public class UIShooter extends UIObjectBase implements IShooterListener{
	
	private static String path = "./images/shooter.png";
	private int angle = 0;
	
	public UIShooter(int x, int y, int width, int height) {
		super(x, y, width, height, path);
	}
	
	public void setAngle(int angle) {
		this.angle = angle - 90;
	}
	
	@Override
	public void draw(Graphics g) {
		g.translate(x, y);
		AffineTransform at = AffineTransform.getTranslateInstance(0, 0);
		at.rotate(Math.toRadians(angle), width/2, height);
		((Graphics2D) g).drawImage(img, at, null);
		g.translate(-x, -y);
	}
	
	
	public void initialize(Shooter shooter) {	
    	shooter.setListener(this);
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

	@Override
	public void onAngleUpdate(int angle) {
		this.angle = angle - 90;
		
	}

	@Override
	public void onFireUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAtomLoad(int typeId) {
		// TODO Auto-generated method stub
		
	}

	
}

