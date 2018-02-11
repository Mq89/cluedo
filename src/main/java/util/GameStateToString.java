package util;

import game.items.Room;
import game.items.Suspect;
import game.items.Weapon;
import game.players.PlayerManager;

/**
 * Class for prettyprinting the game state in a string.
 * 
 * @author Markus Heinrich <mh@0x25.net>
 *
 */
public class GameStateToString {

	public GameStateToString() {

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Game State").append("\n");
		sb.append("\tUnkown suspects: ");
		for (Suspect s : PlayerManager.getInstance().getUnkownSuspects()) {
			sb.append(s).append(" ");
		}
		sb.append("\n");
		sb.append("\t   Unkown rooms: ");
		for (Room s : PlayerManager.getInstance().getUnkownRooms()) {
			sb.append(s).append(" ");
		}
		sb.append("\n");
		sb.append("\t Unkown weapons: ");
		for (Weapon s : PlayerManager.getInstance().getUnkownWeapons()) {
			sb.append(s).append(" ");
		}
		sb.append("\n");

		return sb.toString();
	}

}
