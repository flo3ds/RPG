package action;

import monde.Monde;

public class Monde_Actif {

	private Monde monde;

	public Monde getMonde() {
		return this.monde;
	}

	public void setMonde(Monde monde) {
		this.monde = monde;
	}

	public void genMonde(int width, int height) {
		this.monde = new Monde(width, height);
	}

}
