package game.players;

import java.util.HashMap;
import java.util.List;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import game.items.Item;
import game.items.ItemManager;
import game.items.Room;
import game.items.Suspect;
import game.items.Weapon;
import game.players.Player.State;
import lombok.Getter;

/**
 * Singleton managing the players of the game.
 * 
 * @author Markus Heinrich <mh@0x25.net>
 *
 */
public class PlayerManager {

	private static PlayerManager instance;

	@Getter
	private Player myself;

	/**
	 * List of players.
	 */
	@Getter
	private EventList<Player> players;

	/**
	 * Map of players to find the by name faster.
	 */
	private HashMap<String, Player> playerMap;

	private PlayerManager() {
		this.players = new BasicEventList<>();
		this.playerMap = new HashMap<>();
	}

	/**
	 * Add a player to the game.
	 * 
	 * @param p
	 *            The player to add.
	 */
	public void addPlayer(Player p) {
		players.add(p);
		if (myself == null) {
			myself = p;
		}
		playerMap.put(p.getName(), p);
	}

	/**
	 * Create a player by name and add it to the Player Manager
	 * 
	 * @param name
	 *            The name of the player
	 */
	public void addPlayer(String name) {
		addPlayer(new Player(name));

	}

	/**
	 * Find a player by name.
	 * 
	 * @param name
	 *            Name of the player to find.
	 * @return The player. Null if a player with the given name does not exist.
	 */
	public Player getPlayer(String name) {
		return playerMap.get(name);
	}

	/**
	 * Find a player by id.
	 * 
	 * @param id
	 *            The Id of the player to find.
	 * @return The player. Null if player does not exist.
	 */
	public Player getPlayer(int id) {
		try {
			return players.get(id);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * Add an item to a player. Add the item to the has not list for all other
	 * players.
	 * 
	 * @param thePlayer
	 *            The player to add the item to.
	 * @param i
	 *            The item to add.
	 */
	public void addHasItemTo(Player thePlayer, Item i) {
		for (Player p : players) {
			if (p.equals(thePlayer)) {
				p.addHasItem(i);
			} else {
				p.addHasNotItem(i);
			}
		}

	}

	/**
	 * Add an item to all players has not list. Leave out the given player.
	 * 
	 * @param thePlayer
	 *            The player to leave out.
	 * @param i
	 *            The item to add to the has not list of all players.
	 */
	public void addHasNotToAllBut(Player thePlayer, Item i) {
		for (Player p : players) {
			if (!p.equals(thePlayer)) {
				p.addHasNotItem(i);
			}
		}
	}

	/**
	 * Create the set of suspects from which it is not known who holds them.
	 * 
	 * @return The list of suspects.
	 */
	public List<Suspect> getUnkownSuspects() {
		List<Suspect> suspects = ItemManager.getInstance().getSuspectsList();
		for (Player p : players) {
			for (Suspect s : p.getSuspects(State.HAS)) {
				suspects.remove(s);
			}
		}
		return suspects;
	}

	/**
	 * Create the set of rooms from which it is not known who holds them.
	 * 
	 * @return The list of rooms.
	 */
	public List<Room> getUnkownRooms() {
		List<Room> rooms = ItemManager.getInstance().getRoomsList();
		for (Player p : players) {
			for (Room s : p.getRooms(State.HAS)) {
				rooms.remove(s);
			}
		}
		return rooms;
	}

	/**
	 * Create the set of weapons from which it is not known who holds them.
	 * 
	 * @return The list of weapons.
	 */
	public List<Weapon> getUnkownWeapons() {
		List<Weapon> weapons = ItemManager.getInstance().getWeaponsList();
		for (Player p : players) {
			for (Weapon s : p.getWeapons(State.HAS)) {
				weapons.remove(s);
			}
		}
		return weapons;
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
