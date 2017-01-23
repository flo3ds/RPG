package items;

public class PalissadeBois extends Item {

	private String id = "PalissadeBois";

	public PalissadeBois() {
		this.setId(this.id);
	}

	public PalissadeBois(int nb) {
		this.setNombre((short) nb);
		this.setId(this.id);
	}

}
