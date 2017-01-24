package weapons;

import items.Item;

public class Arc extends Item {

	private String id = "Arc";

	public Arc() {
		this.setId(this.id);
	}

	public Arc(int nb) {
		this.setNombre((short) nb);
		this.setId(this.id);
	}
}