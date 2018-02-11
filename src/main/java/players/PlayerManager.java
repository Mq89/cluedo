package players;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import items.Room;
import items.Suspect;
import items.Weapon;
import lombok.Getter;

public class PlayerManager {

	private static PlayerManager instance;

	@Getter
	private List<Player> players;

	private HashMap<String, Player> playerMap;

	private PlayerManager() {
		this.players = new LinkedList<>();
		this.playerMap = new HashMap<>();
	}

	public void addPlayer(Player p) {
		players.add(p);
		playerMap.put(p.getName(), p);
	}

	public Player getPlayer(String name) {
		return playerMap.get(name);
	}

	public Player getPlayer(int id) {
		return players.get(id);
	}

	public void addHasCard(Player thePlayer, Suspect s) {
		for (Player p : players) {
			if (p.equals(thePlayer)) {
				p.getSuspectManager().addHasCard(s);
			} else {
				p.getSuspectManager().addHasNotCard(s);
			}
		}

	}

	public void addHasCard(Player thePlayer, Room r) {
		for (Player p : players) {
			if (p.equals(thePlayer)) {
				p.getRoomManager().addHasCard(r);
			} else {
				p.getRoomManager().addHasNotCard(r);
			}
		}

	}

	public void addHasCard(Player thePlayer, Weapon w) {
		for (Player p : players) {
			if (p.equals(thePlayer)) {
				p.getWeaponManager().addHasCard(w);
			} else {
				p.getWeaponManager().addHasNotCard(w);
			}
		}

	}

	public static PlayerManager getInstance() {
		if (instance == null) {
			synchronized (PlayerManager.class) {
				if (instance == null) {
					instance = new PlayerManager();
				}
			}
		}
		return instance;
	}

}
