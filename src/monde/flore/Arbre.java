package monde.flore;

import core.Rand;
import items.Bois;
import items.genItems.GenBois;

public class Arbre extends Feuille {

	private String tronc_couleur;
	private String tronc_texture;
	private String tronc_taille;
	private String tronc_largeur;
	
	public Bois bois = GenBois.genBois();

	public Arbre() {
		this.tronc_couleur = Color.values()[Rand.entier(0, Color.values().length)].toString();
		this.tronc_texture = Texture.values()[Rand.entier(0, Texture.values().length)].toString();
		this.tronc_taille = Taille.values()[Rand.entier(0, Taille.values().length)].toString();
		this.tronc_largeur = Largeur.values()[Rand.entier(0, Largeur.values().length)].toString();
	}

	public String getDescArbre() {
		return "C'est un arbre avec un tronc " + this.tronc_largeur + " de couleur " + this.tronc_couleur + ", "
				+ this.tronc_texture + ", de " + this.tronc_taille + " taille." + "\n" + this.getDescFeuille() + "\n";
	}

	private enum Color {
		marron, noir, blanc;
	}

	private enum Texture {
		lisse, rugueux, rainuré;
	}

	private enum Taille {
		petite, grande, moyenne;
	}

	private enum Largeur {
		fin, large, enorme;
	}
}
