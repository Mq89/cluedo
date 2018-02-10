package items;

import java.util.HashMap;

public class ItemManager {

	private static ItemManager instance;

	private HashMap<String, Suspect> suspects;
	private HashMap<String, Weapon> weapons;
	private HashMap<String, Room> rooms;

	public Suspect getSuspect(String name) {
		return suspects.get(name);
	}

	public Weapon getWeapon(String name) {
		return weapons.get(name);
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

}
