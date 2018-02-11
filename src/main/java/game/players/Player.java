package game.players;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import game.items.Item;
import game.items.Room;
import game.items.Suspect;
import game.items.Weapon;
import game.items.Item.Type;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Class modelling a player of the game. The class keeps track of the cards a
 * player holds or definitely holds not during the game.
 * 
 * @author Markus Heinrich <mh@0x25.net>
 *
 */
@EqualsAndHashCode(of = { "id" })
public class Player {

	/**
	 * Static instance counter. Id of instance is derived from the counter.
	 */
	public static int COUNT = 0;

	/**
	 * Name of the player.
	 */
	@Getter
	private String name;

	/**
	 * Id of the player. Also determines the order of turns.
	 */
	@Getter
	private int id;

	/**
	 * Items the player holds or holds not.
	 */
	private HashMap<Item, State> items;

	public Player(String name) {
		this.name = name;
		this.id = COUNT;
		COUNT++;

		items = new HashMap<>();
	}

	/**
	 * 
	 * @param i
	 *            The item to check.
	 * @return Whether the player holds the item.
	 */
	public boolean hasItem(Item i) {
		return items.containsKey(i) && items.get(i).equals(State.HAS);
	}

	/**
	 * 
	 * @param i
	 *            The item to check.
	 * @return Whether the player does not hold the item.
	 */
	public boolean hasNotItem(Item i) {
		return items.containsKey(i) && items.get(i).equals(State.HAS_NOT);
	}

	/**
	 * Marks an item as owned by this player.
	 * 
	 * @param i
	 *            The item to mark.
	 * @return Whether the item was in the list before.
	 */
	public boolean addHasItem(Item i) {
		boolean result = !items.containsKey(i);
		items.put(i, State.HAS);
		return result;
	}

	/**
	 * Marks an item as NOT owned by this player.
	 * 
	 * @param i
	 *            The item to mark.
	 * @return Whether the item was in the list before.
	 */
	public boolean addHasNotItem(Item i) {
		boolean result = !items.containsKey(i);
		items.put(i, State.HAS_NOT);
		return result;
	}

	/**
	 * Creates a list of suspects the player has or has not.
	 * 
	 * @param state
	 *            The state of the items to find.
	 * @return A list of suspects with the defined state.
	 */
	public List<Suspect> getSuspects(State state) {
		List<Suspect> list = new LinkedList<>();
		for (Item i : items.keySet()) {
			if (i.getType() == Type.SUSPECT && items.get(i) == state) {
				list.add((Suspect) i);
			}
		}
		return list;
	}

	/**
	 * Creates a list of rooms the player has or has not.
	 * 
	 * @param state
	 *            The state of the items to find.
	 * @return A list of rooms with the defined state.
	 */
	public List<Room> getRooms(State state) {
		List<Room> list = new LinkedList<>();
		for (Item i : items.keySet()) {
			if (i.getType() == Type.ROOM && items.get(i) == state) {
				list.add((Room) i);
			}
		}
		return list;
	}

	/**
	 * Creates a list of weapons the player has or has not.
	 * 
	 * @param state
	 *            The state of the items to find.
	 * @return A list of weapons with the defined state.
	 */
	public List<Weapon> getWeapons(State state) {
		List<Weapon> list = new LinkedList<>();
		for (Item i : items.keySet()) {
			if (i.getType() == Type.WEAPON && items.get(i) == state) {
				list.add((Weapon) i);
			}
		}
		return list;
	}

	@Override
	public String toString() {
		return name;
	}

	public static enum State {
		HAS, HAS_NOT, UNKOWN
	}

}
