package game.items;

public class Suspect extends Item {

	public Suspect(String name) {
		super(name, Type.SUSPECT);
	}

	@Override
	public int compareTo(Item o) {
		return getName().compareTo(o.getName());
	}
}
