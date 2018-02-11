package action;

import items.Room;
import items.Suspect;
import items.Weapon;
import lombok.Getter;
import players.Player;

public class Action {

	@Getter
	private Player move;

	@Getter
	private Suspect suspect;

	@Getter
	private Room room;

	@Getter
	private Weapon weapon;

	@Getter
	private Player confirm;

	public Action(Player move, Suspect suspect, Room room, Weapon weapon, Player confirm) {
		this.move = move;
		this.suspect = suspect;
		this.room = room;
		this.weapon = weapon;
		this.confirm = confirm;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(move).append(", ");
		sb.append(suspect).append(", ");
		sb.append(room).append(", ");
		sb.append(weapon).append(", ");
		sb.append(confirm).append(")");
		return sb.toString();
	}
}
