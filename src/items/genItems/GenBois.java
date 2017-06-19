package items.genItems;

import core.Rand;
import items.Bois;

public class GenBois {

	static public Bois genBois() {

		int nb = Rand.entier(2, 8);

		return new Bois(nb);
	}
}
