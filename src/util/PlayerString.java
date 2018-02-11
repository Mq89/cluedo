package util;

import items.Room;
import items.Suspect;
import items.Weapon;
import players.Player;

public class PlayerString {

	private Player p;

	public PlayerString(Player p) {
		this.p = p;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(p.getName()).append(" (").append(p.getId()).append(")").append("\n");
		sb.append("\t    Suspects (has): ");
		for (Suspect s : p.getSuspectManager().getHasCards()) {
			sb.append(s).append(" ");
		}
		sb.append("\n");
		sb.append("\tSuspects (has not): ");
		for (Suspect s : p.getSuspectManager().getHasNotCards()) {
			sb.append(s).append(" ");
		}
		sb.append("\n\n");
		sb.append("\t       Rooms (has): ");
		for (Room s : p.getRoomManager().getHasCards()) {
			sb.append(s).append(" ");
		}
		sb.append("\n");
		sb.append("\t   Rooms (has not): ");
		for (Room s : p.getRoomManager().getHasNotCards()) {
			sb.append(s).append(" ");
		}
		sb.append("\n\n");
		sb.append("\t     Weapons (has): ");
		for (Weapon s : p.getWeaponManager().getHasCards()) {
			sb.append(s).append(" ");
		}
		sb.append("\n");
		sb.append("\t Weapons (has not): ");
		for (Weapon s : p.getWeaponManager().getHasNotCards()) {
			sb.append(s).append(" ");
		}

		return sb.toString();
	}

}
