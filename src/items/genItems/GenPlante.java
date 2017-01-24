package items.genItems;

import core.Rand;
import items.Plante;

public class GenPlante {

	static public Plante GenPlante() {
		
		Plante.espece type = Plante.espece.values()[Rand.entier(0, Plante.espece.values().length)];

		int nb = (short) (1 + Math.random() * 5);

		return new Plante(type, nb);
	}
}