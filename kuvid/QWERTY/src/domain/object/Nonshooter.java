package domain.object;

import domain.utilities.*;

public abstract class Nonshooter extends ObjectBase {
	
	protected double movementAngle;
	private int typeId;
	
	public Nonshooter(Coordinate coordinate, int typeId, double movementAngle) {
		super(coordinate);
		this.typeId = typeId;
		this.movementAngle = movementAngle;
	}

	public int getTypeId() {
		return typeId;
	}
	
	public double getMovementAngle() {
		return movementAngle;
	}

	public void setMovementAngle(double movementAngle) {
		this.movementAngle = movementAngle;
	}
	
	public abstract void update();
	
	public void travel() {
		this.getCoordinate().setX(this.getCoordinate().getX() + (int) (StaticFields.LENGTH_L * Math.cos(Math.toRadians(movementAngle))/10));
		this.getCoordinate().setY(this.getCoordinate().getY() + (int) (StaticFields.LENGTH_L * -Math.sin(Math.toRadians(movementAngle))/10));
	}
	
	public void willCollide(int xMov) {
		if (this.movementAngle < 90) {
			if (this.getCoordinate().getX() + xMov > StaticFields.SCREEN_WIDTH - 2*StaticFields.LENGTH_L) {
				this.movementAngle += 90;
			}
		} else if (this.movementAngle > 90) {
			if (this.getCoordinate().getX() + xMov < StaticFields.LEFT_BORDER) {
				this.movementAngle -= 90;
			}
		}
	}
	private int alternateCounter = 0;
	public void alternate() {
		if(alternateCounter == 0) {
			alternateCounter = (int) (StaticFields.LENGTH_L/10);
			if (this.movementAngle < 270) {
				this.movementAngle += 90;
			} else if (this.movementAngle == 270) this.movementAngle = 315;
			else this.movementAngle -= 90;	
		}
		alternateCounter--;
	}
}
