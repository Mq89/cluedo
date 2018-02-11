package items;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(of = { "name" })
public abstract class Item implements Comparable<Item> {

	@Getter
	private String name;

	public Item(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
