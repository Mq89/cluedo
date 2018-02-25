package game.items;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import lombok.Getter;

/**
 * Singleton managing all available items of the game. Items are
 * <ul>
 * <li>Suspects</li>
 * <li>Rooms</li>
 * <li>Weapons</li>
 * </ul>
 * 
 * @author Markus Heinrich <mh@0x25.net>
 *
 */
public class ItemManager {

	private static ItemManager instance;

	private boolean finalized = false;

	/**
	 * The suspects.
	 */
	@Getter
	private HashMap<String, Suspect> suspectsMap;
	@Getter
	private Vector<Suspect> suspectsVector;
	@Getter
	private List<Suspect> suspectsList;

	/**
	 * The weapons.
	 */
	@Getter
	private HashMap<String, Weapon> weaponsMap;
	@Getter
	private Vector<Weapon> weaponsVector;
	@Getter
	private List<Weapon> weaponsList;

	/**
	 * The rooms.
	 */
	@Getter
	private HashMap<String, Room> roomsMap;
	@Getter
	private Vector<Room> roomsVector;
	@Getter
	private List<Room> roomsList;

	@Getter
	private List<Item> items;

	private ItemManager() {
		suspectsMap = new HashMap<>();
		weaponsMap = new HashMap<>();
		roomsMap = new HashMap<>();
	}

	/**
	 * Creates the suspects from a list of strings which should contain the names.
	 * 
	 * @param suspects
	 *            List of names of suspects.
	 */
	public void addSuspectsFromString(List<String> suspects) {
		if (finalized) {
			throw new IllegalStateException("Method must not be called after class has been finalized.");
		}
		for (String s : suspects) {
			this.suspectsMap.put(s, new Suspect(s));
		}
	}

	/**
	 * Find a suspect object by name.
	 * 
	 * @param name
	 *            Name of the suspect.
	 * @return The object of the suspect. Null if name does not exist.
	 */
	public Suspect getSuspect(String name) {
		return suspectsMap.get(name);
	}

	/**
	 * Creates the weapons from a list of strings which should contain the names.
	 * 
	 * @param suspectsMap
	 *            List of names of weapons.
	 */
	public void addWeaponsFromString(List<String> weapons) {
		if (finalized) {
			throw new IllegalStateException("Method must not be called after class has been finalized.");
		}
		for (String w : weapons) {
			this.weaponsMap.put(w, new Weapon(w));
		}
	}

	/**
	 * Find a weapon object by name.
	 * 
	 * @param name
	 *            Name of the weapon.
	 * @return The object of the weapon. Null if name does not exist.
	 */
	public Weapon getWeapon(String name) {
		return weaponsMap.get(name);
	}

	/**
	 * Creates the rooms from a list of strings which should contain the names.
	 * 
	 * @param suspectsMap
	 *            List of names of rooms.
	 */
	public void addRoomsFromString(List<String> rooms) {
		if (finalized) {
			throw new IllegalStateException("Method must not be called after class has been finalized.");
		}
		for (String r : rooms) {
			this.roomsMap.put(r, new Room(r));
		}
	}

	/**
	 * Find a room object by name.
	 * 
	 * @param name
	 *            Name of the room.
	 * @return The object of the room. Null if name does not exist.
	 */
	public Room getRoom(String name) {
		return roomsMap.get(name);
	}

	/**
	 * Call after adding of items has been finished. Creates complementary data
	 * structures for items. Do not add items after calling finalize anymore.
	 */
	public void finalize() {
		finalized = true;

		suspectsVector = new Vector<>(suspectsMap.values());
		suspectsVector.sort(null);
		suspectsList = new LinkedList<>(suspectsMap.values());
		suspectsList.sort(null);

		weaponsVector = new Vector<>(weaponsMap.values());
		weaponsVector.sort(null);
		weaponsList = new LinkedList<>(weaponsMap.values());
		weaponsList.sort(null);

		roomsVector = new Vector<>(roomsMap.values());
		roomsVector.sort(null);
		roomsList = new LinkedList<>(roomsMap.values());
		roomsList.sort(null);

		items = new LinkedList<>();
		items.addAll(suspectsList);
		items.addAll(weaponsList);
		items.addAll(roomsList);

	}

	public static ItemManager getInstance() {
		if (instance == null) {
			synchronized (ItemManager.class) {
				if (instance == null) {
					instance = new ItemManager();
				}
			}
		}
		return instance;
	}

	public static void main(String[] args) {
		Suspect s1 = new Suspect("s1");
		// Suspect s2 = new Suspect("s2");
		Weapon w1 = new Weapon("w1");
		s1.compareTo(w1);
	}
}
