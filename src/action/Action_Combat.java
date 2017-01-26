package action;

import base.Base;
import core.event.Event_extends;
import perso.Personnage;

public class Action_Combat extends Action_Perso {

	private Personnage perso;
	private Base base;
	public Boolean sonder = false;
	public Object enemy;

	public Action_Combat(Personnage perso, Base base) {
		this.perso = perso;
		this.base = base;
	}
	
	public String coup(){
		
		return "";
	}
	
	public String fuir(){
		return "";
	}
	

	public String help() {
		String out = "";

		out += Action_combat.coup.action.getName() + "\n";
		out += Action_combat.fuir.action.getName() + "\n";

		out += this.help_perso();
		return out;
	}

	public String action(String in) {
		if (Action_combat.coup.action.test(in))
			return this.coup();
		else if (Action_combat.fuir.action.test(in))
			return this.fuir();
		else if (this.actionPersoTest(in))
			return this.actionPerso(this.perso, in);
		else
			return this.help();
	}

	public enum Action_combat {

		coup("coup"),
		fuir("fuir");

		public core.Action action;

		Action_combat(String str) {
			this.action = new core.Action(str);
		}
	}
}
