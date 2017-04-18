package items.genItems;

import core.Rand;
import items.Fil;

public class GenFil {

	static public Fil genFil() {

		int nb = Rand.entier(2, 8);

		return new Fil(nb);
	}

}
