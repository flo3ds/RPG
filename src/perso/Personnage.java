package perso;

import action.Position;
import core.Armor;
import core.Time;
import core.Weapon;

public class Personnage {

	private Time time;
	private int gold = 20;
	private int vie = 100;
	private short degat_base = 5;

	private Armor armor = null;
	private Weapon weapon = null;

	public Inventaire inv = new Inventaire();
	public Position position = Position.base;
	public Position lastPosition = Position.base;

	public short oxygen;

	public Position getPosition(){
		return this.position;
	}
	
	public void setPosition(Position pos){
		this.lastPosition = this.position;
		this.position = pos;
	}
	
	
	public short getOxygen() {
		return oxygen;
	}

	public void setOxygen(short oxygen) {
		this.oxygen = oxygen;
	}

	public String listeStuff() {
		String out = "";
		out += "Armure : ";
		if (this.armor != null)
			out += this.armor.getId();
		out += "\nArme : ";
		if (this.weapon != null)
			out += this.weapon.getId();
		return out + "\n";
	}

	public short getDegat() {
		short degat = this.degat_base;
		if (this.weapon != null)
			degat += this.weapon.getDegat();
		return degat;
	}

	public int getVie() {
		return vie;
	}

	public void setVie(int vie) {
		this.vie = vie;
	}

	public void takeDamage(int dmg) {
		short armor = 0;
		if (this.armor != null)
			armor = this.armor.getArmor();
		int degat = dmg - armor;
		if (degat < 0)
			degat = 0;
		this.vie -= dmg;
	}

	public Personnage(Time time) {
		this.time = time;
	}

	public void addTime(long jours) {
		this.time.addTime(jours);
	}

	public Time getTime() {
		return this.time;
	}

	public int getGold() {
		return this.gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public void addGold(int gold) {
		this.gold += gold;
	}

	public void subGold(int gold) {
		this.gold -= gold;
		if (this.gold < 0)
			this.gold = 0;
	}

	public Armor getArmor() {
		return armor;
	}

	public void setEquipable(Object obj) {
		if (obj instanceof Armor) {
			if (this.armor != null)
				this.inv.putItem(this.armor);
			this.armor = (Armor) obj;

		} else if (obj instanceof Weapon) {
			if (this.weapon != null)
				this.inv.putItem(this.weapon);
			this.weapon = (Weapon) obj;
		}
	}

	public String malusOxygen() {
		if (this.oxygen < 66 && this.oxygen > 33)
			return "La composition atmospherique faible en oxygen vous ralenti pénalise votre tache.\n";
		else if (this.oxygen < 33)
			return "Il y a trop peu d'oxygen pour entreprendre une tache aussi physique.\n";
		return "";
	}

}
