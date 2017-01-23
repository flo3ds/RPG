package monde;

import monde.ciel.Ciel;
import monde.faune.Faune;
import monde.flore.Flore;
import monde.sol.Sol;

public class GenMonde {

	public String name = "";
	public Flore flore = new Flore();
	public Ciel ciel = new Ciel();
	public Sol sol = new Sol();
	public Faune faune = new Faune();

	public GenMonde() {

	}

	public String getDescriptionGlobal() {
		return ciel.getDescription() + "\n" + sol.getDescription() + "\n";
	}

}
