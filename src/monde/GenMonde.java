package monde;

import monde.athmosphere.Atmosphere;
import monde.ciel.Ciel;
import monde.dangerRare.Danger_Rare;
import monde.faune.Faune;
import monde.flore.Flore;
import monde.sol.Sol;

public class GenMonde {

	public String name = "";
	public Flore flore = new Flore();
	public Ciel ciel = new Ciel();
	public Sol sol = new Sol();
	public Faune faune = new Faune();
	public Atmosphere atmo = new Atmosphere();
	public Danger_Rare dangerRare = new Danger_Rare();

	public GenMonde() {

	}

	public String getDescriptionGlobal() {
		return ciel.getDescription() + "\n" + sol.getDescription() + "\n";
	}
	
	public String getDescriptionSonde() {
		return "Rapport de la sonde :\n" + atmo.getDescription() + dangerRare.getDescription();
	}

}
