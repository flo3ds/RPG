package action;

import action.Action_Base.Action_base;
import base.Base;
import event.Event_extends;
import perso.Personnage;

// 
/**
 * Cette classe gere les actions du personnage C'est a dire les actions qui ne
 * dependent pas d'un lieux et qu'il peut faire en permanance Exemple : Regarder
 * son inventaire...
 */
public class Action_Event extends Action_Perso {

	private Personnage perso;
	private Base base;
	
	public Action_Event(Personnage perso, Base base){
		this.perso = perso;
		this.base = base;
	}
	
	
	public String help() {
		String out = "";
		out += ((Event_extends)this.base.event.getEvent()).getHelp();
		out += this.help_perso();
		return out;
	}

	public String action(String in) {
		if (((Event_extends) this.base.event.getEvent()).test(in))
			return ((Event_extends)this.base.event.getEvent()).action(in);
		else if (this.actionPersoTest(in))
			return this.actionPerso(this.perso, in);
		else
			return this.help();
	}
}
