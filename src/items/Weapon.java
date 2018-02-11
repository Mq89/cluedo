package items;

public class Weapon extends Item {

	public Weapon(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Item o) {
		return getName().compareTo(o.getName());
	}
}
