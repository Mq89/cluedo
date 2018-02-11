package players;

import java.util.HashSet;

import items.Item;
import lombok.Getter;

public class CardManager<T extends Item> {

	@Getter
	private HashSet<T> hasCards;
	
	@Getter
	private HashSet<T> hasNotCards;

	public CardManager() {
		hasCards = new HashSet<>();
		hasNotCards = new HashSet<>();
	}


	public boolean hasCard(T item) {
		return hasCards.contains(item);
	}

	public boolean hasNotCard(T item) {
		return hasNotCards.contains(item);
	}

	public void addHasCard(T item) {
		hasCards.add(item);
	}

	public void addHasNotCard(T item) {
		hasNotCards.add(item);
	}

}
