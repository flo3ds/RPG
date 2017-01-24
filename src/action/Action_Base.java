package action;

import event.Event;
import event.Event_extends;
import event.GenEvent;
import perso.Personnage;

public class Action_Base extends Action_Perso {

	private Personnage perso;
	private GenEvent event;

	public Action_Base(Personnage perso, GenEvent event) {
		this.perso = perso;
		this.event = event;
	}

	public String portail() {
		this.perso.position = Position.portail;
		return "Vous ete a votre portail";
	}

	public String coffre() {
		this.perso.position = Position.coffre;
		return "Vous etes a votre coffre.\n";
	}
	
	public String rapport(){
		String out = "";
		out += "rapport :\n";
		if(event.getEvent() != null)
			out += ((Event_extends)this.event.getEvent()).getIntro();
		else
			out += "Rien a signaler.\n";
		return out;
	}

	public String craftTable() {
		this.perso.position = Position.craft;
		return "Vous etes a votre table de craft.\n";
	}

	public String help() {
		String out = "";
		
		if(event.getEvent() != null)
			out += ((Event_extends)this.event.getEvent()).getHelp();
		out += Action.portail.getName() + "\n";
		out += Action.coffre.getName() + "\n";
		out += Action.rapport.getName() + "\n";
		out += Action.craftTable.getName() + "\n";

		out += this.help_perso();
		return out;
	}

	public String action(String in) {
		if (Action.portail.test(in))
			return this.portail();
		else if (Action.coffre.test(in))
			return this.coffre();
		else if (Action.craftTable.test(in))
			return this.craftTable();
		else if (Action.rapport.test(in))
				return this.rapport();
		else if (this.actionPersoTest(in))
			return this.actionPerso(this.perso, in);
		else
			return this.help();
	}
}
