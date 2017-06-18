package items.genItems;

import core.Rand;
import items.Plante;

public class GenPlante {

	static public Plante GenPlante() {

		Plante.espece type = Plante.espece.values()[Rand.entier(0, Plante.espece.values().length)];

		int nb = Rand.entier(2, 8);

		return new Plante(type, nb);
	}
}