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

	private GestionId gid;
	
	public Colision col;
	
	public String name = "";
	public Flore flore;
	public Ciel ciel;
	public Sol sol;
	public Pierre pierre;
	// public Feuillage_arbre fa = new Feuillage_arbre(gid);
	public Tronc tronc;
	public Faune faune;
	public Atmosphere atmo;
	public Danger_Rare dangerRare;

	public Monde(int w, int h) {
		gid = new GestionId();
		
		col = new Colision(gid, w, h);
		
		name = "";
		flore = new Flore();
		ciel = new Ciel();
		sol = new Sol(gid, w, h);
		pierre = new Pierre(gid, w, h);
		// public Feuillage_arbre fa = new Feuillage_arbre(gid);
		tronc = new Tronc(gid, w, h);
		faune = new Faune();
		atmo = new Atmosphere();
		dangerRare = new Danger_Rare();
	}

	public String getDescriptionGlobal() {
		return ciel.getDescription() + "\n" + sol.getDescription() + "\n";
	}

	public String getDescriptionSonde() {
		return "Rapport de la sonde :\n" + atmo.getDescription() + dangerRare.getDescription();
	}
}
