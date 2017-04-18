package core;

public class Enemy {

	private short vie = 10;
	private short degat = 10;

	public short getVie() {
		return vie;
	}

	public void setVie(short vie) {
		this.vie = vie;
	}

	public void takeDamage(short degat) {
		this.vie -= degat;
	}

	public short getDegat() {
		return degat;
	}

	public void setDegat(short degat) {
		this.degat = degat;
	}
}