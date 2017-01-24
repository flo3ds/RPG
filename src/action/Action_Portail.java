package action;

import perso.Personnage;

public class Action_Portail extends Action_Perso {

	private Personnage perso;
	
	public Boolean sonder = false;
	
	public Action_Portail(Personnage perso){
		this.perso = perso;
	}
	
	public String explorer() {
		this.perso.position = Position.monde;
		return "";
	}

	public String base() {
		this.perso.position = Position.base;
		return "Vous rentrez a votre base.\n";
	}
	
	public String sonder() {
		return "";//L'action sonder et gerer dans GUIMain
	}

	public String help() {
		String out = "";

		out += Action.explorer.getName() + "\n";
		out += Action.base.getName() + "\n";
		out += Action.sonder.getName() + "\n";

		out += this.help_perso();
		return out;
	}

	public String action(String in) {
		if (Action.explorer.test(in))
			return this.explorer();
		else if (Action.sonder.test(in))
			return this.sonder();
		else if (Action.base.test(in))
			return this.base();
		else if (this.actionPersoTest(in))
			return this.actionPerso(this.perso, in);
		else
			return this.help();
	}
}
