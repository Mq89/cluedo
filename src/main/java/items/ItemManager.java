package items;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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

	/**
	 * The suspects.
	 */
	private HashMap<String, Suspect> suspects;

	/**
	 * The rooms.
	 */
	private HashMap<String, Room> rooms;

	/**
	 * The weapons.
	 */
	private HashMap<String, Weapon> weapons;

	private ItemManager() {
		suspects = new HashMap<>();
		weapons = new HashMap<>();
		rooms = new HashMap<>();
	}

	/**
	 * Creates the suspects from a list of strings which should contain the names.
	 * 
	 * @param suspects
	 *            List of names of suspects.
	 */
	public void addSuspectsFromString(List<String> suspects) {
		for (String s : suspects) {
			this.suspects.put(s, new Suspect(s));
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
		return suspects.get(name);
	}

	/**
	 * 
	 * @return The set of all available suspects.
	 */
	public HashSet<Suspect> getSuspects() {
		HashSet<Suspect> result = new HashSet<>();
		result.addAll(suspects.values());
		return result;
	}

	/**
	 * Creates the rooms from a list of strings which should contain the names.
	 * 
	 * @param suspects
	 *            List of names of rooms.
	 */
	public void addRoomsFromString(List<String> rooms) {
		for (String r : rooms) {
			this.rooms.put(r, new Room(r));
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
		return rooms.get(name);
	}

	/**
	 * 
	 * @return The set of all available rooms.
	 */
	public HashSet<Room> getRooms() {
		HashSet<Room> result = new HashSet<>();
		result.addAll(rooms.values());
		return result;
	}

	/**
	 * Creates the weapons from a list of strings which should contain the names.
	 * 
	 * @param suspects
	 *            List of names of weapons.
	 */
	public void addWeaponsFromString(List<String> weapons) {
		for (String w : weapons) {
			this.weapons.put(w, new Weapon(w));
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
		return weapons.get(name);
	}

	/**
	 * 
	 * @return The set of all available weapons.
	 */
	public HashSet<Weapon> getWeapons() {
		HashSet<Weapon> result = new HashSet<>();
		result.addAll(weapons.values());
		return result;
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
