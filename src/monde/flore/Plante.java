package monde.flore;

import core.Rand;

public class Plante extends Feuille {

	private String forme;
	private String taille;
	private Fleur fleur;
	private Boolean bool_fleur;

	public Plante() {
		this.bool_fleur = (Math.random() < 0.5) ? true : false;
		this.forme = Forme.values()[Rand.entier(0, Forme.values().length)].toString();
		this.taille = Taille.values()[Rand.entier(0, Taille.values().length)].toString();
		if (this.forme.equals(Forme.fleur.toString()))
			this.bool_fleur = true;
		if (this.bool_fleur)
			fleur = new Fleur();

	}

	public String getDescFleur() {
		if (this.bool_fleur)
			return fleur.getDescription();
		else
			return "Cette plante n'a pas de fleur.";
	}

	public String getDescPlante() {
		return "C'est une plante de " + this.taille + " taille sous forme de " + this.forme + ".\n"
				+ this.getDescFleur() + "\n" + this.getDescFeuille() + "\n";
	}

	private enum Forme {
		fleur, buisson, liere;
	}

	private enum Taille {
		petite, grande, immense, moyenne;
	}

}
