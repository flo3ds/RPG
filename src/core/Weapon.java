package core;

//Un tool ne peut pas faire partit des composant d'une recette

abstract public class Weapon extends Inventable implements Equipable {

	private short degat = 5;
	
	public short getDegat(){
		return this.degat;
	}
	
	public void setDegat(short degat){
		this.degat = degat;
	}
	
}