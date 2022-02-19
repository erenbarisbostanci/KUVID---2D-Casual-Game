package ui.objects;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class UIObjectBase {
	protected int x, y, width, height;
	protected Image img;
	
	public UIObjectBase(int x, int y, int width, int height, String imgPath) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		ImageIcon imageIcon = new ImageIcon(imgPath);
		Image image = imageIcon.getImage();
		img = image.getScaledInstance(width, height,  Image.SCALE_SMOOTH);
	}
	
	public void setImage(String path) {
		ImageIcon imageIcon = new ImageIcon(path);
		Image image = imageIcon.getImage();
		img = image.getScaledInstance(width, height,  Image.SCALE_SMOOTH);
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public abstract void draw(Graphics g);
}
