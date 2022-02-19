package ui.objects;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import domain.object.Atom;
import domain.object.Blocker;
import domain.object.Molecule;
import domain.object.Powerup;
import domain.object.Shooter;
import domain.observer.IAtomListener;
import domain.observer.IBlockerListener;
import domain.observer.ICreationListener;
import domain.observer.IObjectListener;

public class AnimationPanel extends JPanel implements ICreationListener{
	private static final long serialVersionUID = 1L;
	
	private Background bg;
	private UIShooter shooter;
	
	private static ArrayList<UIAtom> atoms = new ArrayList<UIAtom>();
	private static ArrayList<UIMolecule> molecules = new ArrayList<UIMolecule>();
	private static ArrayList<UIPowerUp> powerups = new ArrayList<UIPowerUp>();
	private static ArrayList<UIBlocker> blockers = new ArrayList<UIBlocker>();
	
	int width, height;
	
	public AnimationPanel(int width, int height) {
		this.width = width;
		this.height = height;
		bg = new Background(StatsUIHolder.GAME_WIDTH, StatsUIHolder.GAME_HEIGHT);
		this.setLayout(null);
		this.setBounds(0, 0, this.width, this.height);
		new Timer(20, e-> {repaint();}).start();  
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		bg.draw(g);
		
		for(int i = 0; i < atoms.size(); i++) {
			atoms.get(i).draw(g);
		}
		
		for(int i = 0; i < molecules.size(); i++) {
			molecules.get(i).draw(g);
		}
		
		for(int i = 0; i < blockers.size(); i++) {
			blockers.get(i).draw(g);
		}
		
		for(int i = 0; i < powerups.size(); i++) {
			powerups.get(i).draw(g);
		}
		
		if(this.shooter != null) {
			shooter.draw(g);			
		}
		
	}

	public static ArrayList<UIAtom> getAtomsList() {
		return atoms;
	}

	public static ArrayList<UIMolecule> getMoleculesList() {
		return molecules;
	}
	
	public static ArrayList<UIPowerUp> getPowerUpsList() {
		return powerups;
	}

	public static ArrayList<UIBlocker> getBlockersList() {
		return blockers;
	}

	@Override
	public void setShooter(Shooter shooter) {
		this.shooter = new UIShooter(shooter.getCoordinate().getX(), shooter.getCoordinate().getY(), shooter.getWidth(), shooter.getHeight());
		shooter.setListener(this.shooter);
	}
	
	@Override
	public void setAtomListener(Atom atom) {
		UIAtom listener = new UIAtom(atom.getCoordinate().getX(), atom.getCoordinate().getY(), atom.getBulletSize(), atom.getBulletSize(), atom.getTypeId());
		listener.initialize(atom);
		atoms.add((UIAtom) atom.getListener());
	}

	@Override
	public void setMoleculeListener(Molecule molecule) {
		UIMolecule listener = new UIMolecule(molecule.getCoordinate().getX(), molecule.getCoordinate().getY(), molecule.getSize(), molecule.getSize(), molecule.getTypeId(), StatsUIHolder.isStraight);
		listener.initialize(molecule);
		molecules.add((UIMolecule) molecule.getListener());
		
	}

	@Override
	public void setBlockerListener(Blocker blocker) {
		UIBlocker listener = new UIBlocker(blocker.getCoordinate().getX(), blocker.getCoordinate().getY(), blocker.getSize(), blocker.getSize(), blocker.getTypeId());
		listener.initialize(blocker);
		blockers.add((UIBlocker) blocker.getListener());
	}

	@Override
	public void setPowerupListener(Powerup powerup) {
		UIPowerUp listener = new UIPowerUp(powerup.getCoordinate().getX(), powerup.getCoordinate().getY(), powerup.getBulletSize(), powerup.getBulletSize(), powerup.getTypeId());
		listener.initialize(powerup);
		powerups.add((UIPowerUp) powerup.getListener());
	}
	
	@Override
	public void removeAtomListener(IAtomListener list) {
		atoms.remove(list);
	}


	@Override
	public void removeMoleculeListener(IObjectListener list) {
		molecules.remove(list);
		
	}

	@Override
	public void removeBlockerListener(IBlockerListener list) {
		blockers.remove(list);
	}

	@Override
	public void removePowerupListener(IObjectListener list) {
		powerups.remove(list);
	}
}
