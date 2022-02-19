package domain;

import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

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

public class SaveLoadMongoDBSystem implements ISaveLoadAdapter {

	Logger mongoLogger;
	MongoClient mongoClient;
	MongoDatabase database; 
	MongoCollection<Document> collection;
	ArrayList<String> list = new ArrayList<String>();

	private int l;
	
	public SaveLoadMongoDBSystem() {
		System.out.println("Connecting MongoDataBase...");
		mongoLogger = Logger.getLogger( "org.mongodb.driver" );
		mongoLogger.setLevel(Level.SEVERE);	
		mongoClient = MongoClients.create("mongodb+srv://comp302_user:comp302_password@sandbox.v2mqr.mongodb.net/");
		database = mongoClient.getDatabase("Comp302");
		collection = database.getCollection("QWERTYCollection");
		collection.find().iterator().forEachRemaining(e ->{
			list.add(e.getString("save"));
		});
		System.out.println("Connected MongoDataBase");
	}
	
	@Override
	public void save(String save) {
		Document doc = new Document();
		doc.append("save", save);
		doc.append("user", StatisticTracker.getStatisticTracker().getNickname());
		writeInstancer(doc);
		writeStats(doc);
		writeInventory(doc);
		writeObjects(doc);
		writeShooter(doc);
		if(list.contains(save)) {
			collection.replaceOne(eq("save", save), doc);
		}else {			
			list.add(save);
			collection.insertOne(doc);	
		}
	}

	@Override
	public void load(String load) {
		Document doc = collection.find(eq("save", load)).first();
		StatisticTracker.getStatisticTracker().setNickname(doc.getString("user"));
		readInstancer(doc);
		readStats(doc);
		readInventory(doc);
		readObjects(doc);
		readShooter(doc);

	}



	@Override
	public ArrayList<String> getSaveFileNames() {
		return list;
	}

	@Override
	public void remove(String remove) {
		list.remove(remove);
		collection.deleteOne(eq("save", remove));
	}



	private void writeInstancer(Document doc) {
		Document instancer = new Document();
		instancer.append("L", StaticFields.LENGTH_L);
		instancer.append("difficulty", StaticFields.difficulty);
		instancer.append("MOLECULES0", StaticFields.MOLECULES[0]);
		instancer.append("MOLECULES1", StaticFields.MOLECULES[1]);
		instancer.append("MOLECULES2", StaticFields.MOLECULES[2]);
		instancer.append("MOLECULES3", StaticFields.MOLECULES[3]);
		instancer.append("BLOCKERS0", StaticFields.BLOCKERS[0]);
		instancer.append("BLOCKERS1", StaticFields.BLOCKERS[1]);
		instancer.append("BLOCKERS2", StaticFields.BLOCKERS[2]);
		instancer.append("BLOCKERS3", StaticFields.BLOCKERS[3]);
		instancer.append("POWERUPS0", StaticFields.POWERUPS[0]);
		instancer.append("POWERUPS1", StaticFields.POWERUPS[1]);
		instancer.append("POWERUPS2", StaticFields.POWERUPS[2]);
		instancer.append("POWERUPS3", StaticFields.POWERUPS[3]);
		doc.append("Instancer", instancer);
	}

	private void readInstancer(Document doc) {
		Document instancer = (Document) doc.get("Instancer");
		l = instancer.getInteger("L");
		StaticFields.difficulty = instancer.getInteger("difficulty");
		StaticFields.MOLECULES[0] = instancer.getInteger("MOLECULES0");
		StaticFields.MOLECULES[1] = instancer.getInteger("MOLECULES1");
		StaticFields.MOLECULES[2] = instancer.getInteger("MOLECULES2");
		StaticFields.MOLECULES[3] = instancer.getInteger("MOLECULES3");
		StaticFields.BLOCKERS[0] = instancer.getInteger("BLOCKERS0");
		StaticFields.BLOCKERS[1] = instancer.getInteger("BLOCKERS1");
		StaticFields.BLOCKERS[2] = instancer.getInteger("BLOCKERS2");
		StaticFields.BLOCKERS[3] = instancer.getInteger("BLOCKERS3");
		StaticFields.POWERUPS[0] = instancer.getInteger("POWERUPS0");
		StaticFields.POWERUPS[1] = instancer.getInteger("POWERUPS1");
		StaticFields.POWERUPS[2] = instancer.getInteger("POWERUPS2");
		StaticFields.POWERUPS[3] = instancer.getInteger("POWERUPS3");
	}


	private void writeStats(Document doc) {
		Document stats = new Document();
		stats.append("health", StatisticTracker.getStatisticTracker().getHealth());
		stats.append("score", StatisticTracker.getStatisticTracker().getScore());
		stats.append("time", StatisticTracker.getStatisticTracker().getCurTime().getTime());
		doc.append("Stats", stats);
	}

	private void readStats(Document doc) {
		Document stats = (Document) doc.get("Stats");
		StatisticTracker.getStatisticTracker().setHealth(stats.getInteger("health"));
		StatisticTracker.getStatisticTracker().setScore(stats.getDouble("score"));
		StatisticTracker.getStatisticTracker().getCurTime().setTime(stats.getLong("time"));
	}


	private void writeInventory(Document doc) {
		Document inventory = new Document();
		Document atoms = new Document();
		int i = -1;
		for(Atom atom : Inventory.alphaAtoms) {
			i++;
			atoms.append("atom"+i, writeAtom(atom));
		}
		for(Atom atom : Inventory.betaAtoms) {
			i++;
			atoms.append("atom"+i, writeAtom(atom));
		}
		for(Atom atom : Inventory.gammaAtoms) {
			i++;
			atoms.append("atom"+i, writeAtom(atom));
		}
		for(Atom atom : Inventory.sigmaAtoms) {
			i++;
			atoms.append("atom"+i, writeAtom(atom));
		}
		atoms.append("number", i);
		inventory.append("atoms", atoms);
		
		Document powerups = new Document();
		powerups.append("0", Inventory.powerupNumbers[0]);
		powerups.append("1", Inventory.powerupNumbers[1]);
		powerups.append("2", Inventory.powerupNumbers[2]);
		powerups.append("3", Inventory.powerupNumbers[3]);
		inventory.append("powerups", powerups);
		
		Document shields = new Document();
		shields.append("0", Inventory.shieldNumbers[0]);
		shields.append("1", Inventory.shieldNumbers[1]);
		shields.append("2", Inventory.shieldNumbers[2]);
		shields.append("3", Inventory.shieldNumbers[3]);
		inventory.append("shields", shields);
		doc.append("Inventory", inventory);

	}

	private void readInventory(Document doc) {
		Inventory.clean();
		Document inventory = (Document) doc.get("Inventory");
		Document atoms = (Document) inventory.get("atoms");
		int k = atoms.getInteger("number");
		for(int i = 0; i <= k; i++) {
			Atom atom = readAtom((Document) atoms.get("atom"+i));
			Inventory.addAtom(atom);
		}
		Document powerups = (Document) inventory.get("powerups");
		Inventory.addPowerup(0, powerups.getInteger("0"));
		Inventory.addPowerup(1, powerups.getInteger("1"));
		Inventory.addPowerup(2, powerups.getInteger("2"));
		Inventory.addPowerup(3, powerups.getInteger("3"));
		
		Document shields = (Document) inventory.get("shields");
		Inventory.addShield(0, shields.getInteger("0"));
		Inventory.addShield(1, shields.getInteger("1"));
		Inventory.addShield(2, shields.getInteger("2"));
		Inventory.addShield(3, shields.getInteger("3"));
		
	}
	
	private Atom readAtom(Document atom) {
		int type = atom.getInteger("typeId");
		Coordinate coor = new Coordinate(calcNewPos(atom.getInteger("x")), calcNewPos(atom.getInteger("y")));
		double movementAngle = atom.getDouble("angle");
		Atom ato = AtomFactory.getAtomFactory().generateAtom(coor, type);
		ato.setMovementAngle(movementAngle);
		ato.setProtons(atom.getInteger("protons"));
		ato.setNeutrons(atom.getInteger("neutrons"));
		if(ato instanceof AlphaAtom) {
			ato.setEfficiency((1 - (Math.abs(ato.getNeutrons() - 8) /  8)) * 0.85);
		}else if(ato instanceof BetaAtom) {
			ato.setEfficiency(0.9 - (0.5 * Math.abs(ato.getNeutrons() - 16) /  16));
		}else if(ato instanceof SigmaAtom) {
			ato.setEfficiency(1.7 / 2 + (Math.abs(ato.getNeutrons() - 64) / 64));
		}else if(ato instanceof GammaAtom) {
			ato.setEfficiency(0.8 +  (Math.abs(ato.getNeutrons() - 32) / 64));
		}
		
		Document shields = (Document) atom.get("shield");
		int k = shields.getInteger("number");
		for(int i = 0; i <= k; i++) {
			switch(shields.getInteger("shield"+i)) {
				case 0:
					ato = new Eta(ato);
					break;
				case 1:
					ato = new Lota(ato);
					break;
				case 2:
					ato = new Theta(ato);
					break;
				case 3: 
					ato = new Zeta(ato);
					break;
			}
		}
		
		return ato;
	}
	
	private Document writeAtom(Atom atom) {
		Document ato = new Document();
		ato.append("typeId", atom.getTypeId());
		if(atom.getCoordinate() != null) {
			ato.append("x", atom.getCoordinate().getX());
			ato.append("y", atom.getCoordinate().getY());
		}else {
			ato.append("x", 0);
			ato.append("y", 0);			
		}
		ato.append("angle", atom.getMovementAngle());
		ato.append("protons", atom.getProtons());
		ato.append("neutrons", atom.getNeutrons());
		Document shields = new Document();
		int j = -1;
		for(int k : atom.getShieldList()) {
			j++;
			shields.append("shield"+j, k);
		}
		shields.append("number", j);
		ato.append("shield", shields);
		return ato;
	}

	private void writeObjects(Document doc) {
		Document screenObjects = new Document();

		Document atoms = new Document();
		int i = -1;
		for(Atom atom : ScreenObjects.getScreenObjects().getATOMS()) {
			i++;
			atoms.append("atom"+i, writeAtom(atom));
		}
		atoms.append("number", i);
		screenObjects.append("atoms", atoms);

		Document molecules = new Document();
		i = -1;
		for(Molecule molecule : ScreenObjects.getScreenObjects().getMOLECULES()) {
			i++;
			Document mole = new Document();
			mole.append("typeId", molecule.getTypeId());
			mole.append("x", molecule.getCoordinate().getX());
			mole.append("y", molecule.getCoordinate().getY());
			mole.append("angle", molecule.getMovementAngle());
			molecules.append("molecule"+i, mole);
		}
		molecules.append("number", i);
		screenObjects.append("molecules", molecules);

		Document powerups = new Document();
		i = -1;
		for(Powerup powerup : ScreenObjects.getScreenObjects().getPOWERUPS()) {
			i++;
			Document powerUp = new Document();
			powerUp.append("typeId", powerup.getTypeId());
			powerUp.append("x", powerup.getCoordinate().getX());
			powerUp.append("y", powerup.getCoordinate().getY());
			powerUp.append("angle", powerup.getMovementAngle());
			powerUp.append("isBullet", powerup.getIsBullet());
			powerups.append("powerup"+i, powerUp);
		}
		powerups.append("number", i);
		screenObjects.append("powerups", powerups);


		Document blockers = new Document();
		i = -1;
		for(Blocker blocker : ScreenObjects.getScreenObjects().getBLOCKERS()) {
			i++;
			Document blockr = new Document();
			blockr.append("typeId", blocker.getTypeId());
			blockr.append("x", blocker.getCoordinate().getX());
			blockr.append("y", blocker.getCoordinate().getY());
			blockr.append("angle", blocker.getMovementAngle());
			blockers.append("blocker"+i, blockr);
		}
		blockers.append("number", i);
		screenObjects.append("blockers", blockers);

		doc.append("ScreenObjects", screenObjects);
	}


	private void readObjects(Document doc) {
		ScreenObjects.getScreenObjects().cleanObjects();
		Document screenObjects = (Document) doc.get("ScreenObjects");
		Document atoms = (Document) screenObjects.get("atoms");
		Document molecules = (Document) screenObjects.get("molecules");
		Document powerups = (Document) screenObjects.get("powerups");
		Document blockers = (Document) screenObjects.get("blockers");

		int k = atoms.getInteger("number");
		for(int i = 0; i <= k; i++) {
			Document atom = (Document) atoms.get("atom"+i);
			Atom ato = readAtom(atom);
			ScreenObjects.getScreenObjects().addAtom(ato);
		}

		k = molecules.getInteger("number");
		for(int i = 0; i <= k; i++) {
			Document molecule = (Document) molecules.get("molecule"+i);
			int type = molecule.getInteger("typeId");
			Coordinate coor = new Coordinate(calcNewPos(molecule.getInteger("x")), calcNewPos(molecule.getInteger("y")));
			double movementAngle = molecule.getDouble("angle");
			ScreenObjects.getScreenObjects().addMolecule(coor, type).setMovementAngle(movementAngle);
		}

		k = powerups.getInteger("number");
		for(int i = 0; i <= k; i++) {
			Document powerup = (Document) powerups.get("powerup"+i);
			int type = powerup.getInteger("typeId");
			Coordinate coor = new Coordinate(calcNewPos(powerup.getInteger("x")), calcNewPos(powerup.getInteger("y")));
			double movementAngle = powerup.getDouble("angle");
			Powerup pup = ScreenObjects.getScreenObjects().addPowerup(coor, type);
			pup.setMovementAngle(movementAngle);
			pup.setIsBullet(powerup.getBoolean("isBullet"));
		}

		k = blockers.getInteger("number");
		for(int i = 0; i <= k; i++) {
			Document blocker = (Document) blockers.get("blocker"+i);
			int type = blocker.getInteger("typeId");
			Coordinate coor = new Coordinate(calcNewPos(blocker.getInteger("x")), calcNewPos(blocker.getInteger("y")));
			double movementAngle = blocker.getDouble("angle");
			ScreenObjects.getScreenObjects().addBlocker(coor, type).setMovementAngle(movementAngle);
		}

	}

	private void writeShooter(Document doc) {
		Document shooter = new Document();
		shooter.append("x", ScreenObjects.getScreenObjects().getShooter().getCoordinate().getX());
		shooter.append("y", ScreenObjects.getScreenObjects().getShooter().getCoordinate().getY());
		shooter.append("angle", ScreenObjects.getScreenObjects().getShooter().getAngle());

		Document bullet = new Document();
		if(ScreenObjects.getScreenObjects().getShooter().getBullet() == null) {
			bullet.append("bulletType", 0);
			bullet.append("typeId", 0);
		}
		if(ScreenObjects.getScreenObjects().getShooter().getBullet() instanceof Atom) {
			bullet.append("bulletType", 1);
			bullet.append("atom", writeAtom((Atom) ScreenObjects.getScreenObjects().getShooter().getBullet()));
		}
		if(ScreenObjects.getScreenObjects().getShooter().getBullet() instanceof Powerup) {
			bullet.append("bulletType", 2);
			bullet.append("typeId", ScreenObjects.getScreenObjects().getShooter().getBullet().getTypeId());		
		}
		shooter.append("bullet", bullet);
		doc.append("Shooter", shooter);
	}

	private void readShooter(Document doc) {
		Document shooter = (Document) doc.get("Shooter");

		ScreenObjects.getScreenObjects().getShooter().setLocation(calcNewPos(shooter.getInteger("x")), calcNewPos(shooter.getInteger("y")));
		ScreenObjects.getScreenObjects().getShooter().setAngle(shooter.getInteger("angle"));

		Document bullet = (Document) shooter.get("bullet");
		Coordinate coor = new Coordinate(0,0);
		switch(bullet.getInteger("bulletType")) {
		case 0:
			ScreenObjects.getScreenObjects().getShooter().setBullet(null);
			break;
		case 1:
			Atom atom = readAtom((Document) bullet.get("atom"));
			ScreenObjects.getScreenObjects().getShooter().setBullet(atom);
			break;
		case 2:
			ScreenObjects.getScreenObjects().getShooter().setBullet(new Powerup(coor, bullet.getInteger("typeId"), 0));
			break;		
		}
	}

	private int calcNewPos(int x) {
		return (int) (x*StaticFields.LENGTH_L/l);
	}

}
