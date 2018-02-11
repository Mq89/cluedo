package game.items;

public class Weapon extends Item {

	public Weapon(String name) {
		super(name, Type.WEAPON);
	}

	@Override
	public int compareTo(Item o) {
		return getName().compareTo(o.getName());
	}
}
