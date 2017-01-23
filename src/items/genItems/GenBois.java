package items.genItems;

import items.Bois;

public class GenBois {

	static public Bois genBois() {

		int nb = (short) (1 + Math.random() * 10);

		return new Bois(nb);
	}
}
