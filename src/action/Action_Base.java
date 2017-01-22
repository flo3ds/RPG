package action;

import perso.Personnage;

public class Action_Base extends Action_Perso {

	public String explorer(Personnage perso) {
		perso.position = Position.monde;
		return "";
	}

	public String coffre(Personnage perso) {
		perso.position = Position.coffre;
		return "Vous etes a votre coffre.\n";
	}
	
	public String craftTable(Personnage perso) {
		perso.position = Position.craft;
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

	public String action(Personnage perso, String in) {
		if (Action.explorer.test(in))
			return this.explorer(perso);
		else if (Action.coffre.test(in))
			return this.coffre(perso);
		else if (Action.craftTable.test(in))
			return this.craftTable(perso);
		else if (this.actionPersoTest(in))
			return this.actionPerso(perso, in);
		else
			return this.help();
	}
}
