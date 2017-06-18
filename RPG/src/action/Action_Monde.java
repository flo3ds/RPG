package action;

import action.action_monde.Action_Faune;
import action.action_monde.Action_Flore;
import base.Base;
import core.Item;
import core.event.Event_extends;
import perso.Personnage;
import tools.Hache;

public class Action_Monde {

	public Action_Faune faune;
	public Action_Flore flore;

	private Personnage perso;
	private Base base;
	private Monde_Actif monde;

	public Action_Monde(Personnage perso, Base base, Monde_Actif monde) {
		this.perso = perso;
		this.base = base;
		this.monde = monde;
		faune = new Action_Faune(this.perso, this.base);
		flore = new Action_Flore(this.perso, this.base);
	}

	public String analyseSol() {
		return this.monde.getMonde().sol.analyseSol();
	}

	public String analyseFaune() {
		this.perso.position = Position.faune;
		return this.monde.getMonde().faune.getAllDescription();
	}

	public String analyseFlore() {
		this.perso.position = Position.flore;
		return this.monde.getMonde().flore.getAllDescription();
	}

	public String miner() {
		Object obj = this.monde.getMonde().sol.minerais;
		if (this.perso.getOxygen() < 66 && this.perso.getOxygen() > 33)
			((Item) obj).setNombre((short) (((Item) obj).getNombre() / 2));
		else if (this.perso.getOxygen() < 33)
			return this.perso.malusOxygen();
		this.perso.inv.putItem(this.monde.getMonde().sol.minerais);
		return "Vous minez " + this.monde.getMonde().sol.minerais.getNombre() + " de "
				+ this.monde.getMonde().sol.minerais.getMatiere() + ".\n" + this.perso.malusOxygen();

	}

	public String couperBois() {
		if (this.perso.inv.haveItem(new Hache())) {
			Object obj = this.monde.getMonde().flore.arbres.bois;
			if (this.perso.getOxygen() < 66 && this.perso.getOxygen() > 33)
				((Item) obj).setNombre((short) (((Item) obj).getNombre() / 2));
			else if (this.perso.getOxygen() < 33)
				return this.perso.malusOxygen();
			this.perso.inv.putItem(this.monde.getMonde().flore.arbres.bois);
			return "Vous coupez " + this.monde.getMonde().flore.arbres.bois.getNombre() + " bois.\n"
					+ this.perso.malusOxygen();
		} else
			return "Vous n'avez pas de hache";
	}

	public String base() {
		this.perso.position = Position.base;
		String out = "Vous etes de retour a la base.\n";
		if (this.base.event.getEvent() != null)
			out += ((Event_extends) this.base.event.getEvent()).getIntro();
		return out;
	}

	public String getDescriptionGlobal() {
		return this.monde.getMonde().getDescriptionGlobal();
	}

	public String getDescriptionSonde() {
		return this.monde.getMonde().getDescriptionSonde();
	}

	public String help() {
		String out = "";
		out += Action_monde.miner.action.getName() + "\n";
		out += Action_monde.base.action.getName() + "\n";
		out += Action_monde.analyser_sol.action.getName() + "\n";
		out += Action_monde.analyser_faune.action.getName() + "\n";
		out += Action_monde.analyser_flore.action.getName() + "\n";
		out += Action_monde.couper_bois.action.getName() + "\n";
		return out;
	}

	public String action(int id) {
		if (this.perso.position == Position.flore) {
			return this.flore.action(id, this.monde);
		} else if (this.perso.position == Position.faune) {
			return this.faune.action(id, this.monde);
		} else {
			if (Action_monde.analyser_sol.action.test(id))
				return this.analyseSol();
			else if (Action_monde.miner.action.test(id))
				return this.miner();
			else if (Action_monde.analyser_faune.action.test(id)) {
				this.perso.position = Position.faune;
				return this.analyseFaune();
			} else if (Action_monde.analyser_flore.action.test(id))
				return this.analyseFlore();
			else if (Action_monde.base.action.test(id))
				return this.base();
			else if (Action_monde.couper_bois.action.test(id))
				return this.couperBois();
			else
				return this.help();
		}
	}

	public enum Action_monde {

		analyser_sol("analyser_sol", 0), miner("miner", 1), analyser_faune("analyser_faune",
				2), analyser_flore("analyser_flore", 3), base("base", 99), couper_bois("couper_bois", 4);

		public core.Action action;
		private int id;

		public int getId() {
			return id;
		}

		Action_monde(String str, int id) {
			this.action = new core.Action(str, id);
		}
	}

}
