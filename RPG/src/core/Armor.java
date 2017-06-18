package core;

//Un tool ne peut pas faire partit des composant d'une recette

abstract public class Armor extends Inventable implements Equipable {

	private short armor = 1;

	public short getArmor() {
		return this.armor;
	}

	public void setArmor(short armor) {
		this.armor = armor;
	}

}