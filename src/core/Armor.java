package core;

import items.Item;

//Un tool ne peut pas faire partit des composant d'une recette

abstract public class Armor extends Item implements Inventable, Equipable {

	public Armor(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	private short armor = 1;

	public short getArmor() {
		return this.armor;
	}

	public void setArmor(short armor) {
		this.armor = armor;
	}

}