package action.action_monde;

import action.Action_Perso;
import action.Position;
import base.Base;
import core.Item;
import core.event.Event_extends;
import monde.GenMonde;
import perso.Personnage;

public class Action_Flore extends Action_Perso {

	private Personnage perso;
	private Base base;

	public Action_Flore(Personnage perso, Base base) {
		this.perso = perso;
		this.base = base;
	}

	public String action(String in, GenMonde monde) {
		if (Action_flore.cueillir.action.test(in)) {
			return this.cueillir(monde);
		} else if (Action_flore.base.action.test(in))
			return this.base();
		else if (Action_flore.arrêter_cueillir.action.test(in))
			return this.arretcueillir();
		else if (this.actionPersoTest(in))
			return this.actionPerso(this.perso, in);
		else
			return this.help();

	}

	public String arretcueillir() {
		this.perso.position = Position.monde;
		return "Vous revenez de votre cueillette.\n";
	}

	public String base() {
		this.perso.position = Position.base;
		String out = "Vous etes de retour a la base.\n";
		if (this.base.event.getEvent() != null)
			out += ((Event_extends) this.base.event.getEvent()).getIntro();
		return out;
	}

	public String cueillir(GenMonde monde) {
		Object obj = monde.flore.plante;
		if (this.perso.getOxygen() < 66 && this.perso.getOxygen() > 33)
			((Item) obj).setNombre((short) (((Item) obj).getNombre() / 2));
		else if (this.perso.getOxygen() < 33)
			return this.perso.malusOxygen();
		this.perso.inv.putItem(monde.flore.plante);
		return "Vous récuperez " + monde.flore.plante.getNombre() + " Plantes " + monde.flore.plante.getPlante() + ".\n"
				+ this.perso.malusOxygen();

	}

	public String help() {
		String out = "";
		out += Action_flore.cueillir.action.getName() + "\n";
		out += Action_flore.arrêter_cueillir.action.getName() + "\n";
		out += Action_flore.base.action.getName() + "\n";
		out += this.help_perso();
		return out;
	}

	public enum Action_flore {

		cueillir("cueillir"), arrêter_cueillir("arrêter cueillir"), base("base");

		public core.Action action;

		Action_flore(String str) {
			this.action = new core.Action(str);
		}
	}
}
