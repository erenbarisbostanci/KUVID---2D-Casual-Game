package domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import domain.factories.AtomFactory;
import domain.object.AlphaAtom;
import domain.object.Atom;
import domain.object.BetaAtom;
import domain.object.Blocker;
import domain.object.Eta;
import domain.object.GammaAtom;
import domain.object.Lota;
import domain.object.Molecule;
import domain.object.Powerup;
import domain.object.SigmaAtom;
import domain.object.Theta;
import domain.object.Zeta;
import domain.utilities.Coordinate;
import domain.utilities.StaticFields;
import domain.utilities.StatisticTracker;

public class SaveLoadFileSystem implements ISaveLoadAdapter {

	String path;
	int l;
	
	public SaveLoadFileSystem() {
		path = "../";
	}
	

	@Override
	public void save(String save) {
		FileWriter writer = null;
		try {
			File saveFile = new File(path + "kuvid_save_" + save + ".txt");
			if (saveFile.createNewFile()) {
				System.out.println("File created: " + saveFile.getName());
			} else {
				System.out.println("File already exists.");
			}

			writer = new FileWriter(saveFile);
			writeInstancer(writer);
			writeStats(writer);
			writeInventory(writer);
			writeObjects(writer);
			writeShooter(writer);
			writer.close();

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	@Override
	public void load(String load) {
		Scanner reader = null;
		try {
			File saveFile = new File(path + "kuvid_save_" + load + ".txt");
			if (saveFile.exists()) {
				reader = new Scanner(saveFile);
				if (reader.hasNext()) {
					if (reader.nextLine().equalsIgnoreCase("Instancer")) {
						readInstancer(reader);
					}
				}
				if (reader.hasNext()) {
					if (reader.nextLine().equalsIgnoreCase("StatisticTracker")) {
						readStats(reader);
					}
				}
				if (reader.hasNext()) {
					if (reader.nextLine().equalsIgnoreCase("Inventory")) {
						readInventory(reader);
					}
				}
				if (reader.hasNext()) {
					if (reader.nextLine().equalsIgnoreCase("ScreenObjects")) {
						readObjects(reader);
					}
				}
				if (reader.hasNext()) {
					if (reader.nextLine().equalsIgnoreCase("Shooter")) {
						readShooter(reader);
					}
				}
				reader.close();
			} else {
				return;
			}

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	@Override
	public ArrayList<String> getSaveFileNames() {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> fileNames = new ArrayList<String>();

		for (File file : listOfFiles) {
			if (file.isFile()) {
				if (file.getName().contains("kuvid_save") && file.getName().contains(".txt")) {
					String str = file.getName().replace("kuvid_save_", "");
					str = str.replace(".txt", "");
					fileNames.add(str);
				}
			}
		}
		return fileNames;
	}

	@Override
	public void remove(String remove) {
		File file = new File(path + "kuvid_save_" + remove + ".txt");

		if (file.delete()) {
			System.out.println("File deleted successfully");
		} else {
			System.out.println("Failed to delete the file");
		}
	}

	private void writeShooter(FileWriter writer) throws IOException {
		writer.write("Shooter\n");
		writer.write(ScreenObjects.getScreenObjects().getShooter().getCoordinate().getX() + ":"
				+ ScreenObjects.getScreenObjects().getShooter().getCoordinate().getY() + ":"
				+ ScreenObjects.getScreenObjects().getShooter().getAngle() + ":");
		if (ScreenObjects.getScreenObjects().getShooter().getBullet() == null) {
			writer.write(0 + ":" + 0 + "\n");
		}
		if (ScreenObjects.getScreenObjects().getShooter().getBullet() instanceof Atom) {
			writer.write(1 + "\n");
			writeAtom(writer, (Atom) ScreenObjects.getScreenObjects().getShooter().getBullet());
		}
		if (ScreenObjects.getScreenObjects().getShooter().getBullet() instanceof Powerup) {
			writer.write(2 + ":" + ScreenObjects.getScreenObjects().getShooter().getBullet().getTypeId() + "\n");
		}
	}

	
	private void writeInstancer(FileWriter writer) throws IOException {
		writer.write("Instancer\n");
		writer.write(StaticFields.LENGTH_L + ":" + StaticFields.difficulty + ":"
				+ StaticFields.MOLECULES[0] + ":" + StaticFields.MOLECULES[1] + ":" + StaticFields.MOLECULES[2] + ":" + StaticFields.MOLECULES[3] + ":"
				+ StaticFields.BLOCKERS[0] + ":" + StaticFields.BLOCKERS[1] + ":" + StaticFields.BLOCKERS[2] + ":" + StaticFields.BLOCKERS[3] + ":"
				+ StaticFields.POWERUPS[0] + ":" + StaticFields.POWERUPS[1] + ":" + StaticFields.POWERUPS[2] + ":" + StaticFields.POWERUPS[3] + "\n");
	}

	private void writeStats(FileWriter writer) throws IOException {
		writer.write("StatisticTracker\n");
		writer.write(StatisticTracker.getStatisticTracker().getHealth() + ":"
				+ StatisticTracker.getStatisticTracker().getScore() + ":"
				+ StatisticTracker.getStatisticTracker().getCurTime().getTime() + ":"
				+ StatisticTracker.getStatisticTracker().getNickname() + "\n");
	}

	private void writeInventory(FileWriter writer) throws IOException {
		writer.write("Inventory\n");
		for (Atom atom : Inventory.alphaAtoms) {
			writeAtom(writer, atom);
		}
		for (Atom atom : Inventory.betaAtoms) {
			writeAtom(writer, atom);
		}
		for (Atom atom : Inventory.gammaAtoms) {
			writeAtom(writer, atom);
		}
		for (Atom atom : Inventory.sigmaAtoms) {
			writeAtom(writer, atom);
		}
		writer.write("---\n");
		writer.write(Inventory.powerupNumbers[0] + ":" + Inventory.powerupNumbers[1] + ":" + Inventory.powerupNumbers[2] + ":" + Inventory.powerupNumbers[3] + ":"
						+ Inventory.shieldNumbers[0] + ":" + Inventory.shieldNumbers[1] + ":" + Inventory.shieldNumbers[2] + ":" + Inventory.shieldNumbers[3] + "\n");

	}

	private void writeObjects(FileWriter writer) throws IOException {
		writer.write("ScreenObjects\n");
		writer.write("Atoms\n");
		for (Atom atom : ScreenObjects.getScreenObjects().getATOMS()) {
			writeAtom(writer, atom);
		}
		writer.write("---\n");

		writer.write("Molecules\n");
		for (Molecule molecule : ScreenObjects.getScreenObjects().getMOLECULES()) {
			writer.write(molecule.getTypeId() + ":" + molecule.getCoordinate().getX() + ":"
					+ molecule.getCoordinate().getY() + ":" + molecule.getMovementAngle() + "\n");
		}
		writer.write("---\n");

		writer.write("Powerups\n");
		for (Powerup powerup : ScreenObjects.getScreenObjects().getPOWERUPS()) {
			writer.write(powerup.getTypeId() + ":" + powerup.getCoordinate().getX() + ":"
					+ powerup.getCoordinate().getY() + ":" + powerup.getMovementAngle() + ":");
			if(powerup.getIsBullet()) {
				writer.write("1"+"\n");
			}else {
				writer.write("0"+"\n");
			}
		}
		writer.write("---\n");

		writer.write("Blockers\n");
		for (Blocker blocker : ScreenObjects.getScreenObjects().getBLOCKERS()) {
			writer.write(blocker.getTypeId() + ":" + blocker.getCoordinate().getX() + ":"
					+ blocker.getCoordinate().getY() + ":" + blocker.getMovementAngle() + "\n");
		}
		writer.write("---\n");
	}

	private void writeAtom(FileWriter writer, Atom atom) throws IOException {
		if(atom.getCoordinate() != null) {
			writer.write(atom.getTypeId() + ":" + atom.getCoordinate().getX() + ":" + atom.getCoordinate().getY() + ":" + atom.getMovementAngle() + ":" + atom.getProtons() + ":"+ atom.getNeutrons() + "\n");
		}else {
			writer.write(atom.getTypeId() + ":" + 0 + ":" + 0 + ":" + atom.getMovementAngle() + ":" + atom.getProtons() + ":"+ atom.getNeutrons() + "\n");			
		}
		if(!atom.getShieldList().isEmpty()) {
			writer.write(""+atom.getShieldList().size());
		}else {
			writer.write("0:");
		}
		for (int i = 0; i < atom.getShieldList().size(); i++) {
			writer.write(":"+atom.getShieldList().get(i));
		}
		writer.write("\n");
	}

	private Atom readAtom(Scanner reader, StringTokenizer st) {
		int type = Integer.parseInt(st.nextToken());
		Coordinate coor = new Coordinate(calcNewPos(Integer.parseInt(st.nextToken())), calcNewPos(Integer.parseInt(st.nextToken())));
		double movementAngle = Double.parseDouble(st.nextToken());
		Atom atom = AtomFactory.atomFactory.generateAtom(coor, type);
		atom.setMovementAngle(movementAngle);
		atom.setProtons(Integer.parseInt(st.nextToken()));
		atom.setNeutrons(Integer.parseInt(st.nextToken()));

		if(atom instanceof AlphaAtom) {
			atom.setEfficiency((1 - (Math.abs(atom.getNeutrons() - 8) /  8)) * 0.85);
		}else if(atom instanceof BetaAtom) {
			atom.setEfficiency(0.9 - (0.5 * Math.abs(atom.getNeutrons() - 16) /  16));
		}else if(atom instanceof SigmaAtom) {
			atom.setEfficiency(1.7 / 2 + (Math.abs(atom.getNeutrons() - 64) / 64));
		}else if(atom instanceof GammaAtom) {
			atom.setEfficiency(0.8 +  (Math.abs(atom.getNeutrons() - 32) / 64));
		}

		String str = reader.nextLine();
		st = new StringTokenizer(str, ":");
		int k = Integer.parseInt(st.nextToken());
		for(int i = 0; i < k; i++) {
			switch(Integer.parseInt(st.nextToken())) {
				case 0:
					atom = new Eta(atom);
					break;
				case 1:
					atom = new Lota(atom);
					break;
				case 2:
					atom = new Theta(atom);
					break;
				case 3: 
					atom = new Zeta(atom);
					break;
			}
		}
		return atom;
	}

	private void readShooter(Scanner reader) {
		String str = reader.nextLine();

		StringTokenizer st = new StringTokenizer(str, ":");
		ScreenObjects.getScreenObjects().getShooter().setLocation(calcNewPos(Integer.parseInt(st.nextToken())),
				calcNewPos(Integer.parseInt(st.nextToken())));
		ScreenObjects.getScreenObjects().getShooter().setAngle(Integer.parseInt(st.nextToken()));
		int bullet = Integer.parseInt(st.nextToken());
		switch (bullet) {
		case 0 :
			ScreenObjects.getScreenObjects().getShooter().setBullet(null);
			break;
		case 1:
			st = new StringTokenizer(reader.nextLine(),":");
			Atom atom = readAtom(reader, st);
			ScreenObjects.getScreenObjects().getShooter().setBullet(atom);
			break;
		case 2:
			int type = Integer.parseInt(st.nextToken());
			Coordinate coor = new Coordinate(0, 0);
			ScreenObjects.getScreenObjects().getShooter().setBullet(new Powerup(coor, type, 0));
			break;
		}
	}

	private void readInstancer(Scanner reader) {
		String str = reader.nextLine();
		StringTokenizer st = new StringTokenizer(str, ":");
		l = Integer.parseInt(st.nextToken());
		StaticFields.difficulty = Integer.parseInt(st.nextToken());
		StaticFields.MOLECULES[0] = Integer.parseInt(st.nextToken());
		StaticFields.MOLECULES[1] = Integer.parseInt(st.nextToken());
		StaticFields.MOLECULES[2] = Integer.parseInt(st.nextToken());
		StaticFields.MOLECULES[3] = Integer.parseInt(st.nextToken());
		StaticFields.BLOCKERS[0] = Integer.parseInt(st.nextToken());
		StaticFields.BLOCKERS[1] = Integer.parseInt(st.nextToken());
		StaticFields.BLOCKERS[2] = Integer.parseInt(st.nextToken());
		StaticFields.BLOCKERS[3] = Integer.parseInt(st.nextToken());
		StaticFields.POWERUPS[0] = Integer.parseInt(st.nextToken());
		StaticFields.POWERUPS[1] = Integer.parseInt(st.nextToken());
		StaticFields.POWERUPS[2] = Integer.parseInt(st.nextToken());
		StaticFields.POWERUPS[3] = Integer.parseInt(st.nextToken());
	}

	private void readStats(Scanner reader) {
		String str = reader.nextLine();
		StringTokenizer st = new StringTokenizer(str, ":");
		StatisticTracker.getStatisticTracker().setHealth(Integer.parseInt(st.nextToken()));
		StatisticTracker.getStatisticTracker().setScore(Double.parseDouble(st.nextToken()));
		StatisticTracker.getStatisticTracker().getCurTime().setTime(Long.parseLong(st.nextToken()));
		StatisticTracker.getStatisticTracker().setNickname(st.nextToken());
	}

	private void readInventory(Scanner reader) {
		Inventory.clean();
		String str = reader.nextLine();
		while (!str.equalsIgnoreCase("---")) {
			StringTokenizer st = new StringTokenizer(str, ":");
			Atom atom = readAtom(reader, st);
			Inventory.addAtom(atom);
			str = reader.nextLine();
		}
		str = reader.nextLine();
		StringTokenizer st = new StringTokenizer(str, ":");
		Inventory.addPowerup(0, Integer.parseInt(st.nextToken()));
		Inventory.addPowerup(1, Integer.parseInt(st.nextToken()));
		Inventory.addPowerup(2, Integer.parseInt(st.nextToken()));
		Inventory.addPowerup(3, Integer.parseInt(st.nextToken()));
		Inventory.addShield(0, Integer.parseInt(st.nextToken()));
		Inventory.addShield(1, Integer.parseInt(st.nextToken()));
		Inventory.addShield(2, Integer.parseInt(st.nextToken()));
		Inventory.addShield(3, Integer.parseInt(st.nextToken()));
	}

	private void readObjects(Scanner reader) {
		ScreenObjects.getScreenObjects().cleanObjects();
		String str = reader.nextLine();
		if (str.equalsIgnoreCase("Atoms")) {
			str = reader.nextLine();
			while (!str.equalsIgnoreCase("---")) {
				StringTokenizer st = new StringTokenizer(str, ":");
				Atom atom = readAtom(reader, st);
				ScreenObjects.getScreenObjects().addAtom(atom);
				str = reader.nextLine();
			}
		}

		str = reader.nextLine();
		if (str.equalsIgnoreCase("Molecules")) {
			str = reader.nextLine();
			while (!str.equalsIgnoreCase("---")) {
				StringTokenizer st = new StringTokenizer(str, ":");
				int type = Integer.parseInt(st.nextToken());
				Coordinate coor = new Coordinate(calcNewPos(Integer.parseInt(st.nextToken())),
						calcNewPos(Integer.parseInt(st.nextToken())));
				double movementAngle = Double.parseDouble(st.nextToken());
				ScreenObjects.getScreenObjects().addMolecule(coor, type).setMovementAngle(movementAngle);
				str = reader.nextLine();
			}
		}

		str = reader.nextLine();
		if (str.equalsIgnoreCase("Powerups")) {
			str = reader.nextLine();
			while (!str.equalsIgnoreCase("---")) {
				StringTokenizer st = new StringTokenizer(str, ":");
				int type = Integer.parseInt(st.nextToken());
				Coordinate coor = new Coordinate(calcNewPos(Integer.parseInt(st.nextToken())),
						calcNewPos(Integer.parseInt(st.nextToken())));
				double movementAngle = Double.parseDouble(st.nextToken());
				Powerup pup = ScreenObjects.getScreenObjects().addPowerup(coor, type);
				pup.setMovementAngle(movementAngle);
				if(Integer.parseInt(st.nextToken()) == 1){
					pup.setIsBullet(true);
				}else {
					pup.setIsBullet(false);
				}
				str = reader.nextLine();
			}
		}

		str = reader.nextLine();
		if (str.equalsIgnoreCase("Blockers")) {
			str = reader.nextLine();
			while (!str.equalsIgnoreCase("---")) {
				StringTokenizer st = new StringTokenizer(str, ":");
				int type = Integer.parseInt(st.nextToken());
				Coordinate coor = new Coordinate(calcNewPos(Integer.parseInt(st.nextToken())),
						calcNewPos(Integer.parseInt(st.nextToken())));
				double movementAngle = Double.parseDouble(st.nextToken());
				ScreenObjects.getScreenObjects().addBlocker(coor, type).setMovementAngle(movementAngle);
				str = reader.nextLine();
			}
		}
	}

	private int calcNewPos(int x) {
		return (int) (x * StaticFields.LENGTH_L / l);
	}

}
