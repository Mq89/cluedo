package items;

import java.util.HashMap;

public class ItemManager {

	private static ItemManager instance;

	private HashMap<String, Suspect> suspects;
	private HashMap<String, Weapon> weapons;
	private HashMap<String, Room> rooms;

	private ItemManager() {
		suspects = new HashMap<>();
		weapons = new HashMap<>();
		rooms = new HashMap<>();
	}

	public void addSuspectsFromString(String... suspects) {
		for (String s : suspects) {
			addSuspect(new Suspect(s));
		}
	}

	public void addSuspect(Suspect s) {
		suspects.put(s.getName(), s);
	}

	public Suspect getSuspect(String name) {
		return suspects.get(name);
	}

	public void addWeaponsFromString(String... weapons) {
		for (String w : weapons) {
			addWeapon(new Weapon(w));
		}
	}

	public void addWeapon(Weapon w) {
		weapons.put(w.getName(), w);
	}

	public Weapon getWeapon(String name) {
		return weapons.get(name);
	}

	public void addRoomsFromString(String... rooms) {
		for (String r : rooms) {
			addRoom(new Room(r));
		}
	}

	public void addRoom(Room r) {
		rooms.put(r.getName(), r);
	}

	public Room getRoom(String name) {
		return rooms.get(name);
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
