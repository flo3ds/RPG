package monde.flore;

import core.Rand;

public class Fleur {

	private String color;
	private String forme;
	private String texture;
	private String spec;

	public Fleur() {
		this.color = Color.values()[Rand.entier(0, Color.values().length)].toString();
		this.color = this.color + " " + ColorAttr.values()[Rand.entier(0, ColorAttr.values().length)].toString();
	}

	public String getDescription() {
		return "Les fleurs sont " + this.color + ".";
	}

	private enum Color {
		vert, bleu, rouge, orange, violet, marron, jaune;
	}

	private enum ColorAttr {
		clair, foncé, pale;
	}
}
