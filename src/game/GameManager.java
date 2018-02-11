package game;

import items.ItemManager;
import players.Player;
import players.PlayerManager;
import util.PlayerString;

public class GameManager {

	private static GameManager instance;

	private PlayerManager pm;
	private ItemManager im;

	private GameManager() {

		pm = PlayerManager.getInstance();
		im = ItemManager.getInstance();
	}

	public void init() {
		// init players
		Player p0;
		pm.addPlayer(p0 = new Player("p0"));
		pm.addPlayer(new Player("p1"));
		pm.addPlayer(new Player("p2"));
		pm.addPlayer(new Player("p3"));

		// init items
		im.addSuspectsFromString("s0", "s1", "s2", "s3", "s4");
		im.addRoomsFromString("r0", "r1", "r2", "r3", "r4");
		im.addWeaponsFromString("w0", "w1", "w2", "w3", "w4");

		// draw my (p0) cards
		pm.addHasCard(p0, im.getSuspect("s0"));
		pm.addHasCard(p0, im.getRoom("r0"));
		pm.addHasCard(p0, im.getWeapon("w0"));

		p0.getSuspectManager().addHasNotCard(im.getSuspect("s1"));
		p0.getSuspectManager().addHasNotCard(im.getSuspect("s2"));
		p0.getSuspectManager().addHasNotCard(im.getSuspect("s3"));
		p0.getSuspectManager().addHasNotCard(im.getSuspect("s4"));

		p0.getRoomManager().addHasNotCard(im.getRoom("r1"));
		p0.getRoomManager().addHasNotCard(im.getRoom("r2"));
		p0.getRoomManager().addHasNotCard(im.getRoom("r3"));
		p0.getRoomManager().addHasNotCard(im.getRoom("r4"));

		p0.getWeaponManager().addHasNotCard(im.getWeapon("w1"));
		p0.getWeaponManager().addHasNotCard(im.getWeapon("w2"));
		p0.getWeaponManager().addHasNotCard(im.getWeapon("w3"));
		p0.getWeaponManager().addHasNotCard(im.getWeapon("w4"));

		System.out.println(new PlayerString(p0));

	}

	public static GameManager getInstance() {
		if (instance == null) {
			synchronized (GameManager.class) {
				if (instance == null) {
					instance = new GameManager();
				}
			}
		}
		return instance;
	}

	public static void main(String[] args) {
		GameManager gm = GameManager.getInstance();
		gm.init();

	}
}
