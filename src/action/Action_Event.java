package action;

import base.Base;
import core.event.Event_extends;
import core.event.I_Event;
import gui.layout.StructRet;
import perso.Personnage;

// 
/**
 * Cette classe gere les actions du personnage C'est a dire les actions qui ne
 * dependent pas d'un lieux et qu'il peut faire en permanance Exemple : Regarder
 * son inventaire...
 */
public class Action_Event implements Actionable {

	private Personnage perso;
	private Base base;

	public Action_Event(Personnage perso, Base base) {
		this.perso = perso;
		this.base = base;
	}

	public StructRet init() {
		if (this.base.event.getEvent() != null)
			return ((Event_extends) this.base.event.getEvent()).getHelp();
		else {
			StructRet out = new StructRet();
			out.setHeader("RAS");
			return out;
		}
	}

	public StructRet action(int id) {
		if (((Event_extends) this.base.event.getEvent()).test(id))
			return ((I_Event) this.base.event.getEvent()).execute(id, this.perso, this.base);
		else
			return this.error();
	}

	private StructRet error() {
		StructRet out = new StructRet();
		out.add("error", 0);
		return out;
	}
}
