package monde.flore;

import core.Rand;

public class Feuille {

	private String color;
	private String forme;
	private String texture;
	private String spec;

	public Feuille() {
		this.color = Color.values()[Rand.entier(0, Color.values().length)].toString();
		this.color = this.color + " " + ColorAttr.values()[Rand.entier(0, ColorAttr.values().length)].toString();

		this.forme = Forme.values()[Rand.entier(0, Forme.values().length)].toString();

		this.texture = Texture.values()[Rand.entier(0, Texture.values().length)].toString();

		this.spec = Spec.values()[Rand.entier(0, Spec.values().length)].toString();
	}

	public String getDescFeuille() {
		return "C'est une feuille " + this.color + ", en forme " + this.forme + ". Elle est " + this.texture + ".";
	}

	private enum Color {
		vert, bleu, rouge, orange, violette, marron, jaune;
	}

	private enum ColorAttr {
		clair, foncé, pale;
	}

	private enum Forme {
		triangulaire, ronde, allongé, ovale;
	}

	private enum Texture {
		piquante, douce, rapeuse, nervuré;
	}

	private enum Spec {
		lumineuse, fragile, résistente;
	}

}
