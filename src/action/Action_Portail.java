package action;

import base.Base;
import core.event.Event_extends;
import perso.Personnage;

public class Action_Portail extends Action_Perso {

	private Personnage perso;
	private Base base;
	public Boolean sonder = false;

	public Action_Portail(Personnage perso, Base base) {
		this.perso = perso;
		this.base = base;
	}

	public String explorer() {
		this.perso.position = Position.monde;
		this.perso.addTime(1);
		return "";
	}

	public String base() {
		this.perso.position = Position.base;
		String out = "Vous etes de retour a la base.\n";
		if(this.base.event.getEvent() != null)
		out += ((Event_extends) this.base.event.getEvent()).getIntro();
		return out;
	}

	public String sonder() {
		this.perso.addTime(1);
		return "";// L'action sonder et gerer dans GUIMain
	}

	public String help() {
		String out = "";

		out += Action_portail.explorer.action.getName() + "\n";
		out += Action_portail.base.action.getName() + "\n";
		out += Action_portail.sonder.action.getName() + "\n";

		out += this.help_perso();
		return out;
	}

	public String action(String in) {
		if (Action_portail.explorer.action.test(in))
			return this.explorer();
		else if (Action_portail.sonder.action.test(in))
			return this.sonder();
		else if (Action_portail.base.action.test(in))
			return this.base();
		else if (this.actionPersoTest(in))
			return this.actionPerso(this.perso, in);
		else
			return this.help();
	}

	public enum Action_portail {

		base("base"), explorer("explorer"), sonder("sonder");

		public core.Action action;

		Action_portail(String str) {
			this.action = new core.Action(str);
		}
	}
}
