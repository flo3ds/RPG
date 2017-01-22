package monde.ciel;

import core.Rand;

public class Ciel {

	private String color;
	private short nb_soleil;
	private short nb_lune;

	public Ciel() {
		this.color = Color.values()[Rand.entier(0, Color.values().length)].toString();
		this.nb_soleil = (short) Rand.entier(1, 2);
		this.nb_lune = (short) Rand.entier(0, 3);
	}

	public String getDescription() {
		return "Le ciel est " + this.color + ". Il y a " + this.nb_soleil + " soleil et " + this.nb_lune
				+ " lune dans le ciel.\n";
	}

	private enum Color {
		bleu, orangé, verdatre;
	}
}
