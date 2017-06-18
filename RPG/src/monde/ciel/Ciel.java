package monde.ciel;

import core.Rand;

public class Ciel {

	private String color;
	private ciel ciel_enum;
	private short nb_soleil;
	private short nb_lune;

	public Ciel() {
		short rand = (short) Rand.entier(0, ciel.values().length);
		this.color = ciel.values()[rand].getName();
		this.ciel_enum = ciel.values()[rand];
		this.nb_soleil = (short) Rand.entier(1, 2);
		this.nb_lune = (short) Rand.entier(0, 3);
	}

	public String getDescription() {
		String out = "";
		out += this.color;
		if (this.ciel_enum != ciel.nuage)
			out += " Il y a " + this.nb_soleil + " soleil et " + this.nb_lune + " lune dans le ciel.\n";
		return out; // "Le ciel est " + this.color + ;
	}

	private enum ciel {
		bleu("Le ciel est bleu."), orangé("Le ciel est orangé."), verdatre("Le ciel est vert."), noir(
				"Le ciel est noir, on apercoi les étoiles."), nuage("Le ciel est nuageux.");

		private String str;

		ciel(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}

	}
}
