package monde;

import monde.athmosphere.Atmosphere;
import monde.ciel.Ciel;
import monde.dangerRare.Danger_Rare;
import monde.faune.Faune;
import monde.flore.Flore;


public class Monde {

	
	
	public String name = "";
	public Flore flore;
	public Ciel ciel;

	public Faune faune;
	public Atmosphere atmo;
	public Danger_Rare dangerRare;

	public Monde(int w, int h) {
	
		name = "";
		flore = new Flore();
		ciel = new Ciel();
	
		faune = new Faune();
		atmo = new Atmosphere();
		dangerRare = new Danger_Rare();
	}

	public String getDescriptionGlobal() {
		return ciel.getDescription() + "\n" + "\n";
	}

	public String getDescriptionSonde() {
		return "Rapport de la sonde :\n" + atmo.getDescription() + dangerRare.getDescription();
	}
}
