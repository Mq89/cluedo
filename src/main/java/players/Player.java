package players;

import items.Room;
import items.Suspect;
import items.Weapon;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(of = { "id" })
public class Player {

	/**
	 * Static instance counter. Id of instance is derived from the counter.
	 */
	private static int count = 0;

	@Getter
	private String name;

	@Getter
	private int id;

	@Getter
	private CardManager<Room> roomManager;

	@Getter
	private CardManager<Suspect> suspectManager;

	@Getter
	private CardManager<Weapon> weaponManager;

	public Player(String name) {
		this.name = name;
		this.id = count;
		count++;

		roomManager = new CardManager<>();
		suspectManager = new CardManager<>();
		weaponManager = new CardManager<>();
	}

	public CardManager<Room> getCardManager(Room r) {
		return roomManager;
	}

	public CardManager<Weapon> getCardManager(Weapon w) {
		return weaponManager;
	}

	public CardManager<Suspect> getCardManager(Suspect r) {
		return suspectManager;
	}

	@Override
	public String toString() {
		return name;
	}

}
