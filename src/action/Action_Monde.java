package action;

import monde.GenMonde;
import perso.Personnage;

public class Action_Monde extends Action_Perso {

	public GenMonde monde;

	public String analyseSol() {
		return this.monde.sol.analyseSol();
	}
	
	public String analyseFaune() {
		return this.monde.faune.getAllDescription();
	}
	
	public String analyseFlore() {
		return this.monde.flore.getAllDescription();
	}

	public void newMonde() {
		monde = new GenMonde();
	}

	public String miner(Personnage perso) {
		perso.inv.putItem(this.monde.sol.minerais);
		return "Vous minez " + this.monde.sol.minerais.getNombre() + "K de " + this.monde.sol.minerais.getMatiere()
				+ ".\n";
	}
	
	public String couperBois(Personnage perso) {
		perso.inv.putItem(this.monde.sol.Bois);
		return "Vous coupez " + this.monde.sol.minerais.getNombre() + "K de " + this.monde.sol.minerais.getMatiere()
				+ ".\n";
	}

	
	
	public String base(Personnage perso) {
		perso.position = Position.base;
		return "Vous rentrez a votre base.\n";
	}

	public String getDescriptionGlobal() {
		return this.monde.getDescriptionGlobal();
	}

	public String help() {
		String out = "";
		out += Action.miner.getName() + "\n";
		out += Action.base.getName() + "\n";
		out += Action.analyser_sol.getName() + "\n";
		out += Action.analyser_faune.getName() + "\n";
		out += Action.analyser_flore.getName() + "\n";
		out += this.help_perso();
		return out;
	}

	public String action(Personnage perso, String in) {
		if (Action.analyser_sol.test(in))
			return this.analyseSol();
		else if (Action.miner.test(in))
			return this.miner(perso);
		else if (Action.analyser_faune.test(in))
			return this.analyseFaune();
		else if (Action.analyser_flore.test(in))
			return this.analyseFlore();
		else if (Action.base.test(in))
			return this.base(perso);
		else if (this.actionPersoTest(in))
			return this.actionPerso(perso, in);
		else
			return this.help();
	}

}
