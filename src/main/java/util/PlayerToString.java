package util;

import items.Room;
import items.Suspect;
import items.Weapon;
import players.Player;
import players.Player.State;

/**
 * Class for prettyprinting the state of a player to a string.
 * 
 * @author Markus Heinrich <mh@0x25.net>
 *
 */
public class PlayerToString {

	private Player p;

	public PlayerToString(Player p) {
		this.p = p;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(p.getName()).append(" (").append(p.getId()).append(")").append("\n");
		sb.append("\t    Suspects (has): ");
		for (Suspect s : p.getSuspects(State.HAS)) {
			sb.append(s).append(" ");
		}
		sb.append("\n");
		sb.append("\tSuspects (has not): ");
		for (Suspect s : p.getSuspects(State.HAS_NOT)) {
			sb.append(s).append(" ");
		}
		sb.append("\n\n");
		sb.append("\t       Rooms (has): ");
		for (Room s : p.getRooms(State.HAS)) {
			sb.append(s).append(" ");
		}
		sb.append("\n");
		sb.append("\t   Rooms (has not): ");
		for (Room s : p.getRooms(State.HAS_NOT)) {
			sb.append(s).append(" ");
		}
		sb.append("\n\n");
		sb.append("\t     Weapons (has): ");
		for (Weapon s : p.getWeapons(State.HAS)) {
			sb.append(s).append(" ");
		}
		sb.append("\n");
		sb.append("\t Weapons (has not): ");
		for (Weapon s : p.getWeapons(State.HAS_NOT)) {
			sb.append(s).append(" ");
		}

		return sb.toString();
	}

}
