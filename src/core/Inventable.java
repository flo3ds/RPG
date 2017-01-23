package core;

public abstract class Inventable implements Cloneable {

	private String id;

	public String getId() {
		return this.id;
	}

	public String setId(String id) {
		return this.id = id;
	}

	public String toString() {
		return this.id;
	}

	public Object clone() {
		Object o = null;
		try {
			// On r�cup�re l'instance � renvoyer par l'appel de la
			// m�thode super.clone()
			o = super.clone();
		} catch (CloneNotSupportedException cnse) {
			// Ne devrait jamais arriver car nous impl�mentons
			// l'interface Cloneable
			cnse.printStackTrace(System.err);
		}
		// on renvoie le clone
		return o;
	}
}
