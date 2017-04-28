package monde;

import monde.athmosphere.Atmosphere;
import monde.ciel.Ciel;
import monde.dangerRare.Danger_Rare;
import monde.faune.Faune;
import monde.flore.Tronc;
import monde.flore.Feuillage_arbre;
import monde.flore.Flore;
import monde.sol.Pierre;
import monde.sol.Sol;

public class Monde {

	private GestionId gid= new GestionId();
	public String name = "";
	public Flore flore = new Flore();
	public Ciel ciel = new Ciel();
	public Sol sol = new Sol(gid);
	public Pierre pierre = new Pierre(gid);
	//public Feuillage_arbre fa = new Feuillage_arbre(gid);
	public Tronc tronc = new Tronc(gid);
	public Faune faune = new Faune();
	public Atmosphere atmo = new Atmosphere();
	public Danger_Rare dangerRare = new Danger_Rare();

	public Monde() {
	}

	public String getDescriptionGlobal() {
		return ciel.getDescription() + "\n" + sol.getDescription() + "\n";
	}

	public String getDescriptionSonde() {
		return "Rapport de la sonde :\n" + atmo.getDescription() + dangerRare.getDescription();
	}
}
