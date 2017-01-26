package action;

import base.Base;
import core.event.Event_extends;
import perso.Personnage;

public class Action_Coffre extends Action_Perso {

	private Personnage perso;
	private Base base;

	public Action_Coffre(Personnage perso, Base base) {
		this.perso = perso;
		this.base = base;
	}

	public String help() {
		String out = "";
		out += Action_coffre.coffre_liste.action.getName() + "\n";
		out += Action_coffre.coffre_put.action.getName() + "\n";
		out += Action_coffre.base.action.getName() + "\n";
		out += this.help_perso();
		return out;
	}

	public String base() {
		this.perso.position = Position.base;
		String out = "Vous etes de retour a la base.\n";
		if (this.base.event.getEvent() != null)
			out += ((Event_extends) this.base.event.getEvent()).getIntro();
		return out;
	}

	public String action(String in) {
		if (Action_coffre.coffre_liste.action.test(in))
			return this.base.coffre.liste();
		else if (Action_coffre.coffre_put.action.test(in)) {
			if (this.base.coffre.putItem(this.perso.inv.getItem(0)))
				return "Item non déposé.\n";
			else {
				this.perso.inv.removeItem(0);
				return "Item déposé.";
			}
		} else if (Action_coffre.base.action.test(in))
			return this.base();
		else if (this.actionPersoTest(in))
			return this.actionPerso(this.perso, in);
		else
			return this.help();
	}

	public enum Action_coffre {

		base("base"), coffre_liste("coffre liste"), coffre_put("coffre put");

		public core.Action action;

		Action_coffre(String str) {
			this.action = new core.Action(str);
		}
	}

}
