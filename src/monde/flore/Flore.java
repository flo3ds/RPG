package monde.flore;

import core.Rand;

public class Flore {

	private short nb_type_arbre;
	private short nb_type_plante;
	public Arbre[] arbres;
	private Plante[] plantes;

	public Flore() {
		this.nb_type_arbre = (short) Rand.entier(1, 3);
		this.nb_type_plante = (short) Rand.entier(1, 3);

		arbres = new Arbre[this.nb_type_arbre + 1];
		plantes = new Plante[this.nb_type_plante + 1];

		for (int i = 0; i < this.nb_type_arbre; i++)
			arbres[i] = new Arbre();
		for (int i = 0; i < this.nb_type_plante; i++)
			plantes[i] = new Plante();
	}

	public short getNbTypeArbre() {
		return this.nb_type_arbre;
	}

	public short getNbTypePlante() {
		return this.nb_type_plante;
	}

	public String getDescriptionType() {
		return "Il y a " + this.nb_type_arbre + " type d'arbre different et " + this.nb_type_plante
				+ " type de plante.\n";
	}

	public String getDescriptionArbre(int i) {
		return "Description du type arbre " + (i + 1) + " :\n" + arbres[i].getDescArbre();
	}

	public String getDescriptionPlante(int i) {
		return "Description du type de plante " + (i + 1) + " :\n" + plantes[i].getDescPlante();
	}

	public String getAllDescription() {
		String out = this.getDescriptionType() + "\n";

		for (int i = 0; i < this.nb_type_arbre; i++)
			out += this.getDescriptionArbre(i) + "\n";
		for (int i = 0; i < this.nb_type_plante; i++)
			out += this.getDescriptionPlante(i) + "\n";

		return out;
	}

}
