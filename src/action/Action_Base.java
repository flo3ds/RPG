package action;

import base.Base;
import core.event.Event_extends;
import perso.Personnage;

public class Action_Base extends Action_Perso {

	private Personnage perso;
	private Base base;

	public Action_Base(Personnage perso, Base base) {
		this.perso = perso;
		this.base = base;
	}

	public String portail() {
		this.perso.position = Position.portail;
		return "Vous ete a votre portail";
	}

	public String coffre() {
		this.perso.position = Position.coffre;
		return "Vous etes a votre coffre.\n";
	}

	public String rapport() {
		String out = "";
		out += "rapport :\n";
		if (this.base.event.getEvent() != null) {
			out += ((Event_extends) this.base.event.getEvent()).getRapport();
		} else
			out += "Rien a signaler.\n";
		return out;
	}

	public String craftTable() {
		this.perso.position = Position.craft;
		return "Vous etes a votre table de craft.\n";
	}

	public String actionEvent() {
		this.perso.position = Position.event;
		return ((Event_extends) this.base.event.getEvent()).contact();
	}

	public String help() {
		String out = "";

		if (this.base.event.getEvent() != null && ((Event_extends) this.base.event.getEvent()).getEventReady())
				out += ((Event_extends) this.base.event.getEvent()).getHelpBase() + "\n";
		out += Action_base.portail.action.getName() + "\n";
		out += Action_base.coffre.action.getName() + "\n";
		out += Action_base.rapport.action.getName() + "\n";
		out += Action_base.craftTable.action.getName() + "\n";

		out += this.help_perso();
		return out;
	}

	public String action(String in) {
		if (Action_base.portail.action.test(in))
			return this.portail();
		else if (Action_base.coffre.action.test(in))
			return this.coffre();
		else if (Action_base.craftTable.action.test(in))
			return this.craftTable();
		else if (Action_base.rapport.action.test(in))
			return this.rapport();
		else if (this.actionPersoTest(in))
			return this.actionPerso(this.perso, in);
		else if (this.base.event.getEvent() != null && ((Event_extends) this.base.event.getEvent()).getEventReady()){ //pour l'event rien
			if (((Event_extends) this.base.event.getEvent()).getHelpBase().equals(in)) {
				return this.actionEvent();
			} else {
				return this.help();
			}

		} else
			return this.help();
	}

	public enum Action_base {

		portail("portail"), coffre("coffre"), craftTable("table de craft"), rapport("rapport");

		public core.Action action;

		Action_base(String str) {
			this.action = new core.Action(str);
		}
	}

}
