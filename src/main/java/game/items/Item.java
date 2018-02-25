package game.items;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Abstract class modelling an item of the game.
 * 
 * @author Markus Heinrich <mh@0x25.net>
 *
 */
@EqualsAndHashCode(of = { "name", "type" })
public abstract class Item implements Comparable<Item> {

	@Getter
	private String name;

	@Getter
	private Type type;

	public Item(String name, Type type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public String toString() {
		return name;
	}

	public static enum Type {
		SUSPECT,  WEAPON,ROOM;

		@Override
		public String toString() {
			switch (this) {
			case SUSPECT:
				return "Verdächtiger";
			case WEAPON:
				return "Tatwaffe";
			case ROOM:
				return "Tatort";
			default:
				return "---";
			}
		}
	}
}
