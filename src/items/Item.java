package items;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(of = { "name" })
public class Item {

	@Getter
	private String name;

	public Item(String name) {
		this.name = name;
	}

}
