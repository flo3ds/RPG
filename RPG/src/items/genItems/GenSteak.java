package items.genItems;

import core.Rand;
import items.Steak;

public class GenSteak {

	static public Steak genSteak() {

		int nb = Rand.entier(2, 8);

		return new Steak(nb);
	}

}
