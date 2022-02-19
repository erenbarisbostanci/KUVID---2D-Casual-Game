package ui.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

import domain.object.Atom;
import domain.observer.IAtomListener;

public class UIAtom extends UIObjectBase implements IAtomListener{

	private static String pathA = "./images/atoms/alpha.png";
	private static String pathB = "./images/atoms/beta.png";
	private static String pathG = "./images/atoms/gamma.png";
	private static String pathS = "./images/atoms/sigma.png";

	private ArrayList<ShieldVision> shields = new ArrayList<ShieldVision>();

	public UIAtom(int x, int y, int width, int height, int type) {
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
		if(shields != null) {
			updateShields();
			for(ShieldVision shield : shields) {
				shield.draw(g);
			}
		}

		g.translate(this.x, this.y);
		((Graphics2D) g).drawImage(img, null, null);
		g.translate(-this.x, -this.y);
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

	public void initialize(Atom atom) {	
		atom.setListener(this);
	}

	@Override
	public void onShieldUpdate(ArrayList<Integer> shields) {
		int r = this.height;
		for(int i = 0; i < shields.size(); i++) {
			this.shields.add(new ShieldVision(shields.get(i), (int) (this.x-(i*2.5)), (int) (this.y-(i*2.5)), r+(i*5)));
		}
		Collections.sort(this.shields);
	}
	
	private void updateShields() {
		int r = this.height;
		for(int i = 0; i < shields.size(); i++) {
			this.shields.get(i).set((int) (this.x-(i*2.5)), (int) (this.y-(i*2.5)), r+(i*5));
		}
		Collections.sort(this.shields);
	}
	
	private class ShieldVision implements Comparable<ShieldVision>{
		int typeId, x, y, r;
		private ShieldVision(int typeId, int x, int y, int r){
			this.typeId = typeId;
			this.x = x;
			this.y = y;
			this.r = r;
		}
		
		public void draw(Graphics g) {
			switch(typeId) {
			case 0:
				g.setColor(Color.CYAN);
				break;
			case 1:
				g.setColor(Color.MAGENTA);
				break;
			case 2:
				g.setColor(Color.RED);
				break;
			case 3:
				g.setColor(Color.GREEN);
				break;
			}
			g.fillOval(x, y, r, r);
		}

		@Override
		public int compareTo(ShieldVision shield) {
			if(this.r > shield.r) {
				return -1;
			}else if(this.r < shield.r) {
				return 1;
			}
			return 0;
		}
		
		public void set(int x, int y, int r) {
			this.x = x;
			this.y = y;
			this.r = r;
		}
		
		
	}

}
