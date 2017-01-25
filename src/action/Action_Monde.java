package action;

import action.action_monde.Action_Faune;
import action.action_monde.Action_Flore;
import base.Base;
import core.event.Event_extends;
import monde.GenMonde;
import perso.Personnage;
import tools.Hache;

public class Action_Monde extends Action_Perso {

	public GenMonde monde;
	public Action_Faune faune;
	public Action_Flore flore;

	private Personnage perso;
	private Base base;

	public Action_Monde(Personnage perso, Base base) {
		this.perso = perso;
		this.base = base;
		faune = new Action_Faune(this.perso, this.base);
		flore = new Action_Flore(this.perso, this.base);
	}

	public String analyseSol() {
		return this.monde.sol.analyseSol();
	}

	public String analyseFaune() {
		this.perso.position = Position.faune;

		return this.monde.faune.getAllDescription();
	}

	public String analyseFlore() {
		this.perso.position = Position.flore;

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
		String out = "Vous etes de retour a la base.\n";
		if(this.base.event.getEvent() != null)
		out += ((Event_extends) this.base.event.getEvent()).getIntro();
		return out;
	}

	public String getDescriptionGlobal() {
		return this.monde.getDescriptionGlobal();
	}

	public String getDescriptionSonde() {
		return this.monde.getDescriptionSonde();
	}

	public String help() {
		String out = "";
		out += Action_monde.miner.action.getName() + "\n";
		out += Action_monde.base.action.getName() + "\n";
		out += Action_monde.analyser_sol.action.getName() + "\n";
		out += Action_monde.analyser_faune.action.getName() + "\n";
		out += Action_monde.analyser_flore.action.getName() + "\n";
		out += Action_monde.couper_bois.action.getName() + "\n";
		out += this.help_perso();
		return out;
	}

	public String action(String in) {
		if (this.perso.position == Position.flore) {
			return this.flore.action(in, this.monde);
		} else if (this.perso.position == Position.faune) {
			return this.faune.action(in, this.monde);
		} else {
			if (Action_monde.analyser_sol.action.test(in))
				return this.analyseSol();
			else if (Action_monde.miner.action.test(in))
				return this.miner();
			else if (Action_monde.analyser_faune.action.test(in)) {
				this.perso.position = Position.faune;
				return this.analyseFaune();
			} else if (Action_monde.analyser_flore.action.test(in))
				return this.analyseFlore();
			else if (Action_monde.base.action.test(in))
				return this.base();
			else if (this.actionPersoTest(in))
				return this.actionPerso(this.perso, in);
			else if (Action_monde.couper_bois.action.test(in))
				return this.couperBois();
			else
				return this.help();
		}
	}

	public enum Action_monde {

		analyser_sol("analyser_sol"), miner("miner"), analyser_faune("analyser_faune"), analyser_flore(
				"analyser_flore"), base("base"), couper_bois("couper_bois");

		public core.Action action;

		Action_monde(String str) {
			this.action = new core.Action(str);
		}
	}

}
