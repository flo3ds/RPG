package action.action_monde;

import action.Action_Perso;
import action.Position;
import base.Base;
import core.event.Event_extends;
import monde.GenMonde;
import perso.Personnage;

public class Action_Faune extends Action_Perso {

	private Personnage perso;
	private Base base;

	public Action_Faune(Personnage perso, Base base) {
		this.perso = perso;
		this.base = base;
	}

	public String action(String in, GenMonde monde) {
		if (Action_faune.chasser.action.test(in)) {
			return this.Chasser(monde);
		} else if (Action_faune.base.action.test(in))
			return this.base();
		else if (Action_faune.arrêter_chasse.action.test(in))
			return this.arretChasse();
		else if (this.actionPersoTest(in))
			return this.actionPerso(this.perso, in);
		else
			return this.help();

	}

	public String arretChasse() {
		this.perso.position = Position.monde;
		return "Vous revenez de votre chasse.\n";
	}

	public String base() {
		this.perso.position = Position.base;
		String out = "Vous etes de retour a la base.\n";
		if(this.base.event.getEvent() != null)
		out += ((Event_extends) this.base.event.getEvent()).getIntro();
		return out;
	}

	public String Chasser(GenMonde monde) {
		if (monde.faune.nb_type_animal > 0) {
			this.perso.inv.putItem(monde.faune.steak);
			return "Vous récuperez " + monde.faune.steak.getNombre() + " Steaks " + ".\n";
		} else
			return ("Vous perdez une heure a chasser le vent qui vous siffle aux oreilles");

	}

	public String help() {
		String out = "";
		out += Action_faune.chasser.action.getName() + "\n";
		out += Action_faune.arrêter_chasse.action.getName() + "\n";
		out += Action_faune.base.action.getName() + "\n";
		out += this.help_perso();
		return out;
	}

	public enum Action_faune {

		chasser("chasser"), arrêter_chasse("arrêter chasse"), base("base");

		public core.Action action;

		Action_faune(String str) {
			this.action = new core.Action(str);
		}
	}
}
