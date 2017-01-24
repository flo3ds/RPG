package items;

public class Plante extends Item {

	private String plante;
	private espece type;
	private String id = "Plante";

	public Plante(espece min, int nb) {
		this.plante = min.toString();
		this.setNombre((short) nb);
		this.setId(this.id + " " + min.toString());
	}

	
	public String getPlante() {
		return this.plante;
	}

	
	public espece getType() {
		return this.type;
	}

	public enum espece {
		blanche, rouge, verte, noir;
	}

}
