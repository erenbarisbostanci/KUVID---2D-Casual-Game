package ui.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import domain.object.Molecule;
import domain.observer.IObjectListener;

public class UIMolecule extends UIObjectBase implements IObjectListener{
	
	private static String pathA = "./images/molecules/alpha-1.png";
	private static String pathA_S = "./images/molecules/alpha-2.png";
	private static String pathB = "./images/molecules/beta-1.png";
	private static String pathB_S = "./images/molecules/beta-2.png";
	private static String pathG = "./images/molecules/gamma-.png";
	private static String pathS = "./images/molecules/sigma-.png";

	public UIMolecule(int x, int y, int width, int height, int type, boolean isStraight) {
		super(x, y, width, height, pathA);
		switch (type) {
			case 0:
				if(isStraight) {
					setImage(pathA_S);					
				}else {
					setImage(pathA);
				}
				break;
			case 1:
				if(isStraight) {
					setImage(pathB_S);					
				}else {
					setImage(pathB);
				}
				break;
			case 2:
				setImage(pathG);
				break;
			case 3:
				setImage(pathS);
				break;
		}
	}
	int angle = 0;
	@Override
	public void draw(Graphics g) {
		g.translate(x, y);
		if(StatsUIHolder.areMoleculesSpin && StatsUIHolder.isGameRunning) {
			angle = (angle+10 < 360) ? angle+10 : 0;
			AffineTransform at = AffineTransform.getTranslateInstance(0, 0);
			at.rotate(Math.toRadians(angle), width/2, height/2);			
			((Graphics2D) g).drawImage(img, at, null);
		}else {
			((Graphics2D) g).drawImage(img, null, null);
		}
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
	
	public void initialize(Molecule molecule) {	
    	molecule.setListener(this);
	}


}
