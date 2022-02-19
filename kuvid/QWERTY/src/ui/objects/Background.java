package ui.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Background extends UIObjectBase{
	
	private static String path = "./images/kuvid_bc.png";
	
	public Background(int width, int height) {
		super(0, 0, width, height, path);
	}
	
	@Override
	public void draw(Graphics g) {
		((Graphics2D) g).drawImage(img, null, null);
	}
}
