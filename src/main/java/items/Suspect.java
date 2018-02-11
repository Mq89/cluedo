package items;

public class Suspect extends Item {

	public Suspect(String name) {
		super(name);
	}

	@Override
	public int compareTo(Item o) {
		return getName().compareTo(o.getName());
	}

}
