package domain.object;

import java.awt.Rectangle;

import domain.ScreenObjects;
import domain.observer.IBlockerListener;
import domain.utilities.Coordinate;
import domain.utilities.StaticFields;
import domain.utilities.StatisticTracker;

public abstract class Blocker extends Nonshooter {

	protected IBlockerListener listener;
	
	protected boolean extended = false;
	
	public Blocker(Coordinate coordinate, int typeId, double movementAngle) {
		super(coordinate, typeId, movementAngle);
	}
	
	public void setListener(IBlockerListener listener) {
		this.listener = listener;
		this.listener.onCoordinateUpdate(getCoordinate().getX(), getCoordinate().getY());
		this.listener.onSizeUpdate(getSize(), getSize());
		this.listener.onRectUpdate((int)(getExplosionArea().getWidth()), (int)(getExplosionArea().getHeight()));
	}
	
	public int getSize() {
		return StaticFields.LENGTH_L * 50 /100;
	}
	
	public Rectangle getExplosionArea() {
		int size = StaticFields.LENGTH_L+getSize();
		if(extended) {
			size = StaticFields.LENGTH_L*2+getSize();
		}
		return new Rectangle(getCoordinate().getX()-(size/2)+getSize()/2, getCoordinate().getY()-(size/2)+getSize()/2, size, size);
	}
	
	public void resizeExplosionArea() {
		extended = true;
		this.listener.onRectUpdate((int)(getExplosionArea().getWidth()), (int)(getExplosionArea().getHeight()));
	}

	public IBlockerListener getListener() {
		return listener;
	}

	@Override
	public void update() {
		travel();
		explode();
		damage();
	}
	
	public void onGround() {
		if(getCoordinate().getY() >= StaticFields.SCREEN_HEIGHT - getSize()) {
			resizeExplosionArea();
		}
	}

	public void explode() {
		for(int i = 0; i < ScreenObjects.getScreenObjects().getATOMS().size(); i++) {
			Atom atom = ScreenObjects.getScreenObjects().getATOMS().get(i);
			Rectangle ato = new Rectangle(atom.getCoordinate().getX(), atom.getCoordinate().getY(), atom.getBulletSize(), atom.getBulletSize());
			if(ato.intersects(getExplosionArea())) {
				ScreenObjects.getScreenObjects().getRemove().add(atom);
			}
		}
		
		for(int i = 0; i < ScreenObjects.getScreenObjects().getMOLECULES().size(); i++) {
			Molecule molecule = ScreenObjects.getScreenObjects().getMOLECULES().get(i);
			Rectangle mole = new Rectangle(molecule.getCoordinate().getX(), molecule.getCoordinate().getY(), molecule.getSize(), molecule.getSize());
			if(mole.intersects(getExplosionArea())) {
				ScreenObjects.getScreenObjects().getRemove().add(molecule);
			}
		}
		
	}

	public void damage() {
		Rectangle shoo = new Rectangle(ScreenObjects.getScreenObjects().getShooter().getCoordinate().getX(), ScreenObjects.getScreenObjects().getShooter().getCoordinate().getY(), ScreenObjects.getScreenObjects().getShooter().getWidth(), ScreenObjects.getScreenObjects().getShooter().getHeight());
		if(shoo.intersects(getExplosionArea())) {
			Rectangle bloc = new Rectangle(getCoordinate().getX(), getCoordinate().getY(), getSize(), getSize());
			resizeExplosionArea();
			StatisticTracker.getStatisticTracker().damage((int) findClosest(bloc, shoo));
			ScreenObjects.getScreenObjects().getRemove().add(this);
		}
	}
	
	private double findClosest(Rectangle rec1, Rectangle rec2) {
		double x1, x2, y1, y2;
		double w, h;
		if (rec1.x > rec2.x) {
			x1 = rec2.x; w = rec2.width; x2 = rec1.x;
		} else {
			x1 = rec1.x; w = rec1.width; x2 = rec2.x;
		}
		if (rec1.y > rec2.y) {
			y1 = rec2.y; h = rec2.height; y2 = rec1.y;
		} else {
			y1 = rec1.y; h = rec1.height; y2 = rec2.y;
		}
		double a = Math.max(0, x2 - x1 - w);
		double b = Math.max(0, y2 - y1 - h);
		return Math.sqrt(a*a+b*b);
	}
}
