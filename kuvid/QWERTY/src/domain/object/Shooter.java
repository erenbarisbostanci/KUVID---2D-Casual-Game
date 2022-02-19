package domain.object;

import domain.Inventory;
import domain.ScreenObjects;
import domain.observer.IShooterListener;
import domain.utilities.Coordinate;
import domain.utilities.StaticFields;

public class Shooter extends ObjectBase{

	private Bullet bullet;
	private int angle = 90;
	private IShooterListener listener;

	public Shooter(Coordinate coordinate) {
		super(coordinate);
	}
	public boolean rotateShooter(int rotAngle) {
		//REQUIRES: (rotAngle + angle) < 0 || (rotAngle + angle) > 180 (angle is Shooter's angle)
		//MODIFIES: Shooter's angle and if exist its bullet's angle
		//EFFECTS: if sum of rotAngle and Shooter's angle  will be in between 0 and 180, then execute sum, if exist update bullet's angle and return true
		//		   if sum of rotAngle and Shooter's angle will be in smaller than 0 or bigger than 180, then won't sum and return false;
		if((rotAngle + angle) < 0 || (rotAngle + angle) > 180) {
			return false;
		}
		angle += rotAngle;
		if (bullet != null) {
			calculateBulletPos();
			bullet.setMovementAngle(180 - this.angle);
			//ROTATE!!!
		}
		if(listener != null) {
			listener.onAngleUpdate(angle);
		}
		return true;
	}

	int x9;
	private void calculateBulletPos() {
		int dx = (int) (Math.cos(Math.toRadians(angle))*(getHeight()+bullet.getBulletSize()/2));
		int dy = StaticFields.SCREEN_HEIGHT - (int) (Math.sin(Math.toRadians(angle))*(getHeight()+bullet.getBulletSize()));
		
		if(angle <= 45 || angle >= 135) {
			bullet.setCoordinate((x9-dx),(int) (dy - bullet.getBulletSize()/2));			
		}else {
			bullet.setCoordinate((x9-dx),dy);
		}
		bullet.updateCoordinate(bullet.getCoordinate().getX(), bullet.getCoordinate().getY());		
	}

	public boolean moveRight() {
		//REQUIRES: Shooter's coordinate's x value + movementLength must be smaller then playableGameWidth (ScreenWidth - StaticFields.RIGHT_MENU_WIDTH)
		//MODIFIES: Shooter's and its bullet's coordinate
		//EFFECTS: Shooter's and its bullet's coordinates' x values will be increased
		//		   if increased value will not be bigger than playable screen width, then increase the value and return true
		//		   if increased value will be bigger than playable screen width, then don't increase the value and return false
		if((getCoordinate().getX() + (StaticFields.RIGHT_MENU_WIDTH)) >= (StaticFields.SCREEN_WIDTH-getWidth())) {
			return false;
		}
		getCoordinate().setX(getCoordinate().getX() + StaticFields.LENGTH_L / 9);
		if (bullet != null) {
			bullet.getCoordinate().setX((int) (getCoordinate().getX() + (getWidth()/2) - (bullet.getBulletSize()/2)));
			x9 = bullet.getCoordinate().getX();
			calculateBulletPos();
		}		
		if(listener != null) {
			listener.onCoordinateUpdate(getCoordinate().getX(), getCoordinate().getY());
		}
		return true;
	}

	public boolean moveLeft() {
		//REQUIRES: Shooter's coordinate's x value - movementLength must be bigger or equal to 0
		//MODIFIES: Shooter's and its bullet's coordinate
		//EFFECTS: Shooter's and its bullet's coordinates' x values will be decreased
		//		   if decreased value will not be smaller than 0, then decrease the value and return true
		//		   if decreased value will be smaller than 0, then don't decrease the value and return false
		if((getCoordinate().getX() - StaticFields.LENGTH_L/9) < 0) {
			return false;
		}
		getCoordinate().setX(getCoordinate().getX() - StaticFields.LENGTH_L / 9);
		if (bullet != null) {
			bullet.getCoordinate().setX((int) (getCoordinate().getX() + (getWidth()/2) - (bullet.getBulletSize()/2)));
			x9 = bullet.getCoordinate().getX();
			calculateBulletPos();
		}
		if(listener != null) {
			listener.onCoordinateUpdate(getCoordinate().getX(), getCoordinate().getY());
		}
		return true;
	}

	public int getHeight() {
		return StaticFields.LENGTH_L;
	}

	public int getWidth() {
		return StaticFields.LENGTH_L/2;
	}

	public void shootItem() {
        if (bullet != null) {
            if (bullet instanceof Atom) {
                bullet.setMovementAngle(180 - this.angle);
                ScreenObjects.getScreenObjects().addAtom((Atom) bullet);
                loadAtom();
            } else if (bullet instanceof Powerup) {
                bullet.setMovementAngle(180 - this.angle);
                ScreenObjects.getScreenObjects().addPowerup((Powerup) bullet);
                loadAtom();
            }
        }
    }

	public void loadAtom() {
		Atom x = Inventory.loadRandomAtom();
		if (x != null) {
			bullet = x;
			bullet.updateCoordinate((int) (getCoordinate().getX() + (getWidth()/2) - (bullet.getBulletSize()/2)), (StaticFields.SCREEN_HEIGHT - getHeight() - bullet.getBulletSize()));
			ScreenObjects.getScreenObjects().getListener().setAtomListener((Atom) bullet);
			x9 = bullet.getCoordinate().getX();
			calculateBulletPos();
			((Atom) bullet).getListener().onSizeUpdate(bullet.getBulletSize(), bullet.getBulletSize());
			((Atom) bullet).getListener().onShieldUpdate(((Atom) bullet).getShieldList());
		} else {
			this.bullet = null;
		}
	}

	public int getAngle() {
		return angle;
	}
	
	public void setAngle(int angle) {
		this.angle = angle;
		if(listener != null) {
			listener.onAngleUpdate(this.angle);			
		}
		if(this.bullet != null) {
			calculateBulletPos();
		}
	}

	public void setBullet(Bullet bullet) {
		if(this.bullet instanceof Atom) {
			ScreenObjects.getScreenObjects().getListener().removeAtomListener(((Atom)(this.bullet)).getListener());
			((Atom)(this.bullet)).setListener(null);
		}
		if(this.bullet instanceof Powerup) {
			ScreenObjects.getScreenObjects().getListener().removePowerupListener(((Powerup)(this.bullet)).getListener());
		}
		
		this.bullet = bullet;
		if(this.bullet instanceof Atom) {
			if(((Atom)this.bullet).getListener() == null) {
				ScreenObjects.getScreenObjects().getListener().setAtomListener((Atom) this.bullet);
				((Atom) this.bullet).getListener().onSizeUpdate(this.bullet.getBulletSize(), this.bullet.getBulletSize());
			}
			((Atom)this.bullet).getListener().onShieldUpdate(((Atom)this.bullet).getShieldList());
		}
		if(this.bullet instanceof Powerup) {
			if(((Powerup)this.bullet).getListener() == null) {
				ScreenObjects.getScreenObjects().getListener().setPowerupListener((Powerup) this.bullet);
				((Powerup) this.bullet).getListener().onSizeUpdate(this.bullet.getBulletSize(), this.bullet.getBulletSize());
			}
		}
		if(this.bullet != null) {			
			this.bullet.updateCoordinate((int) (getCoordinate().getX() + (getWidth()/2) - (bullet.getBulletSize()/2)), (StaticFields.SCREEN_HEIGHT - getHeight() - bullet.getBulletSize()));
			x9 = this.bullet.getCoordinate().getX();
			calculateBulletPos();
		}
	}

	public Bullet getBullet() {
		return bullet;
	}

	public void setListener(IShooterListener listener) {
		this.listener = listener;
		this.listener.onCoordinateUpdate(getCoordinate().getX(), getCoordinate().getY());
		this.listener.onSizeUpdate(getWidth(), getHeight());
	}
	
	
	
	public void removeBullet() {
        if (bullet != null) {
            if(bullet instanceof Atom) {
                Inventory.addAtom((Atom) bullet);
                ScreenObjects.getScreenObjects().removeAtom((Atom) bullet);
            } else if (bullet instanceof Powerup) {
                Inventory.addPowerup(bullet.getTypeId(), 1);
                ScreenObjects.getScreenObjects().removePowerup((Powerup) bullet);
            }
        }
    }
	
	public void loadPowerup(int typeId) {
        if (Inventory.powerupNumbers[typeId] != 0) {
            removeBullet();
            bullet = new Powerup(new Coordinate(0,0), typeId, 180 - this.angle);
            bullet.updateCoordinate((int) (getCoordinate().getX() + (getWidth()/2) - (bullet.getBulletSize()/2)), (StaticFields.SCREEN_HEIGHT - getHeight() - bullet.getBulletSize()));
            ScreenObjects.getScreenObjects().getListener().setPowerupListener((Powerup) bullet);
            x9 = bullet.getCoordinate().getX();
            calculateBulletPos();
            ((Powerup) bullet).getListener().onSizeUpdate(bullet.getBulletSize(), bullet.getBulletSize());
            Inventory.removePowerup(typeId, 1);
        }
    }
	
	public void setLocation(int x, int y) {
		getCoordinate().setX(x);
		getCoordinate().setY(y);
		if(listener != null) {
			listener.onCoordinateUpdate(getCoordinate().getX(), getCoordinate().getY());
		}
	}
}
