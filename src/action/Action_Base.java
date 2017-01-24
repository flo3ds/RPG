package action;

import perso.Personnage;

public class Action_Base extends Action_Perso {

	private Personnage perso;
	
	public Action_Base(Personnage perso){
		this.perso = perso;
	}
	
	public String explorer() {
		this.perso.position = Position.monde;
		return "";
	}

	public String coffre() {
		this.perso.position = Position.coffre;
		return "Vous etes a votre coffre.\n";
	}

	public String craftTable() {
		this.perso.position = Position.craft;
		return "Vous etes a votre table de craft.\n";
	}

	public String help() {
		String out = "";

		out += Action.explorer.getName() + "\n";
		out += Action.coffre.getName() + "\n";
		out += Action.craftTable.getName() + "\n";

		out += this.help_perso();
		return out;
	}

	public String action(String in) {
		if (Action.explorer.test(in))
			return this.explorer();
		else if (Action.coffre.test(in))
			return this.coffre();
		else if (Action.craftTable.test(in))
			return this.craftTable();
		else if (this.actionPersoTest(in))
			return this.actionPerso(this.perso, in);
		else
			return this.help();
	}
}
