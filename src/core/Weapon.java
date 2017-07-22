package core;

import items.Item;

//Un tool ne peut pas faire partit des composant d'une recette

abstract public class Weapon extends Item implements Inventable, Equipable {

	public Weapon(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	private short degat = 5;

	public short getDegat() {
		return this.degat;
	}

	public void setDegat(short degat) {
		this.degat = degat;
	}

}