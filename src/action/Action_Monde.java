package action;

import action.action_monde.Action_Faune;
import monde.GenMonde;
import perso.Personnage;
import tools.Hache;

public class Action_Monde extends Action_Perso {

	public GenMonde monde;
	public Action_Faune faune;

	private Personnage perso;

	public Action_Monde(Personnage perso) {
		this.perso = perso;
		faune = new Action_Faune(this.perso);
	}

	public String analyseSol() {
		return this.monde.sol.analyseSol();
	}

	public String analyseFaune() {
		this.perso.position = Position.faune;

		return this.monde.faune.getAllDescription();
	}

	public String analyseFlore() {
		return this.monde.flore.getAllDescription();
	}

	public void newMonde() {
		monde = new GenMonde();
	}

	public String miner() {
		this.perso.inv.putItem(this.monde.sol.minerais);
		return "Vous minez " + this.monde.sol.minerais.getNombre() + "K de " + this.monde.sol.minerais.getMatiere()
				+ ".\n";
	}

	public String couperBois() {
		if (this.perso.inv.haveItem(new Hache())) {
			this.perso.inv.putItem(this.monde.flore.arbres[0].bois);
			return "Vous coupez " + this.monde.flore.arbres[0].bois.getNombre() + " bois.\n";
		} else
			return "Vous n'avez pas de hache";
	}

	public String base() {
		this.perso.position = Position.base;
		return "Vous rentrez a votre base.\n";
	}

	public String getDescriptionGlobal() {
		return this.monde.getDescriptionGlobal();
	}

	public String getDescriptionSonde() {
		return this.monde.getDescriptionSonde();
	}

	public String help() {
		String out = "";
		out += Action.miner.getName() + "\n";
		out += Action.base.getName() + "\n";
		out += Action.analyser_sol.getName() + "\n";
		out += Action.analyser_faune.getName() + "\n";
		out += Action.analyser_flore.getName() + "\n";
		out += Action.couper_bois.getName() + "\n";
		out += this.help_perso();
		return out;
	}

	public String action(String in) {
		if (this.perso.position == Position.faune) {

			return this.faune.action(in, this.monde);
		} else {
			if (Action.analyser_sol.test(in))
				return this.analyseSol();
			else if (Action.miner.test(in))
				return this.miner();
			else if (Action.analyser_faune.test(in)) {
				this.perso.position = Position.faune;
				return this.analyseFaune();
			} else if (Action.analyser_flore.test(in))
				return this.analyseFlore();
			else if (Action.base.test(in))
				return this.base();
			else if (this.actionPersoTest(in))
				return this.actionPerso(this.perso, in);
			else if (Action.couper_bois.test(in))
				return this.couperBois();
			else
				return this.help();
		}
	}
}