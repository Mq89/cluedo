package items;

public class Room extends Item {

	public Room(String name) {
		super(name, Type.ROOM);
	}

	@Override
	public int compareTo(Item o) {
		return getName().compareTo(o.getName());
	}
}
