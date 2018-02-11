package game;

import config.Config;
import config.JSONLoader;
import game.action.Action;
import game.action.ActionManager;
import game.items.ItemManager;
import game.items.Item.Type;
import game.players.Player;
import game.players.PlayerManager;
import util.GameStateToString;
import util.PlayerToString;

public class GameManager {

	private static GameManager instance;

	private PlayerManager pm;
	private ItemManager im;
	private ActionManager am;

	private GameManager() {

		pm = PlayerManager.getInstance();
		im = ItemManager.getInstance();
		am = ActionManager.getInstance();

	}

	public void init() {
		// init players
		Player p0;
		pm.addPlayer(p0 = new Player("p0"));
		pm.addPlayer(new Player("p1"));
		pm.addPlayer(new Player("p2"));
		pm.addPlayer(new Player("p3"));

		// init items

		Config config = new JSONLoader("config/config.json");
		config.load();

		im.addSuspectsFromString(config.getSuspects());
		im.addRoomsFromString(config.getRooms());
		im.addWeaponsFromString(config.getWeapons());

		// draw my (p0) cards
		pm.addHasItemTo(p0, im.getSuspect("s0"));
		pm.addHasItemTo(p0, im.getRoom("r0"));
		pm.addHasItemTo(p0, im.getWeapon("w0"));

		p0.addHasNotItem(im.getSuspect("s1"));
		p0.addHasNotItem(im.getSuspect("s2"));
		p0.addHasNotItem(im.getSuspect("s3"));
		p0.addHasNotItem(im.getSuspect("s4"));

		p0.addHasNotItem(im.getRoom("r1"));
		p0.addHasNotItem(im.getRoom("r2"));
		p0.addHasNotItem(im.getRoom("r3"));
		p0.addHasNotItem(im.getRoom("r4"));

		p0.addHasNotItem(im.getWeapon("w1"));
		p0.addHasNotItem(im.getWeapon("w2"));
		p0.addHasNotItem(im.getWeapon("w3"));
		p0.addHasNotItem(im.getWeapon("w4"));

		System.out.println(new PlayerToString(p0));

		System.out.println(new GameStateToString());

	}

	public void play() {
		am.processAction(new Action(pm.getPlayer(1), im.getSuspect("s2"), im.getRoom("r0"), im.getWeapon("w3"),
				pm.getPlayer(2), null));
		am.processAction(new Action(pm.getPlayer(0), im.getSuspect("s0"), im.getRoom("r0"), im.getWeapon("w1"),
				pm.getPlayer(1), Type.WEAPON));
		am.processAction(new Action(pm.getPlayer(0), im.getSuspect("s3"), im.getRoom("r3"), im.getWeapon("w2"),
				pm.getPlayer(2), Type.WEAPON));
		am.processAction(new Action(pm.getPlayer(0), im.getSuspect("s0"), im.getRoom("r0"), im.getWeapon("w3"),
				pm.getPlayer(3), Type.WEAPON));

		System.out.println(new PlayerToString(pm.getPlayer(1)));
		System.out.println(new PlayerToString(pm.getPlayer(2)));
		System.out.println(new GameStateToString());

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
		gm.play();

	}
}
