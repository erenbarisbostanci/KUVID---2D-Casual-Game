package ui.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import domain.object.Blocker;
import domain.observer.IBlockerListener;

public class UIBlocker extends UIObjectBase implements IBlockerListener{
	
	private static String pathA = "./images/blockers/alpha-b.png";
	private static String pathB = "./images/blockers/beta-b.png";
	private static String pathG = "./images/blockers/gamma-b.png";
	private static String pathS = "./images/blockers/sigma-b.png";
	
	int RectWidth;
	int RectHeight;

	public UIBlocker(int x, int y, int width, int height, int type) {
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
		g.setColor(Color.RED);
		if(y >= StatsUIHolder.GAME_HEIGHT-height) {
			((Graphics2D) g).fillRect(x-RectWidth/2 +width/2, y-RectHeight/2+height/2, RectWidth, RectHeight);
		}else {			
			g.drawRect(x-RectWidth/2 +width/2, y-RectHeight/2+height/2, RectWidth, RectHeight);
		}
		
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
	
	public void initialize(Blocker blocker) {	
    	blocker.setListener(this);
	}

	@Override
	public void onRectUpdate(int width, int height) {
		this.RectWidth = width;
		this.RectHeight = height;
		
	}

}