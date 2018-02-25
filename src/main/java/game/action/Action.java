package game.action;

import game.items.Item;
import game.items.Room;
import game.items.Suspect;
import game.items.Weapon;
import game.players.Player;
import lombok.Getter;

/**
 * Class modeling one action (one move) of the game.
 * 
 * @author Markus Heinrich <mh@0x25.net>
 *
 */
public class Action {
	
	private static int COUNT = 1;
	
	/**
	 * The ID of the action.
	 */
	@Getter
	private int id;

	/**
	 * The player making the move.
	 */
	@Getter
	private Player move;

	/**
	 * The suspect after which is asked.
	 */
	@Getter
	private Suspect suspect;

	/**
	 * The room after which is asked.
	 */
	@Getter
	private Room room;

	/**
	 * The weapon after which is asked.
	 */
	@Getter
	private Weapon weapon;

	/**
	 * The player that confirms (answers) the demand of the player making the move.
	 */
	@Getter
	private Player confirm;

	/**
	 * The type of the card shown to the player making the move. Is null if the card
	 * is unkown to the user.
	 */
	@Getter
	private Item.Type which;

	public Action(Player move, Suspect suspect, Room room, Weapon weapon, Player confirm, Item.Type which) {
		this.id = COUNT++;
		this.move = move;
		this.suspect = suspect;
		this.room = room;
		this.weapon = weapon;
		this.confirm = confirm;
		this.which = which;
	}

	/**
	 * 
	 * @return If known, the item that has been shown to the player making the move.
	 *         Null otherwise.
	 */
	public Item getWhichItem() {
		switch (which) {
		case SUSPECT:
			return suspect;
		case ROOM:
			return room;
		case WEAPON:
			return weapon;
		default:
			return null;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(move).append(", ");
		sb.append(suspect).append(", ");
		sb.append(room).append(", ");
		sb.append(weapon).append(", ");
		sb.append(confirm).append(", ");
		sb.append(which).append(")");
		return sb.toString();
	}
}
