package items;

public class Room extends Item {

	public Room(String name) {
		super(name);
	}

	@Override
	public int compareTo(Item o) {
		return getName().compareTo(o.getName());
	}

}
