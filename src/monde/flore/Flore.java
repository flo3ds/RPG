package monde.flore;


public class Flore {

	public Arbre arbres;
	private Plante plantes;


	public Flore() {

		arbres = new Arbre();
		plantes = new Plante();
	}

	public String getDescriptionArbre() {
		return arbres.getDescArbre();
	}

	public String getDescriptionPlante() {
		return plantes.getDescPlante();
	}

	public String getAllDescription() {
		String out = "";

		out += this.getDescriptionArbre() + "\n";
		out += this.getDescriptionPlante() + "\n";

		return out;
	}

}
