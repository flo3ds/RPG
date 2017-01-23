package action;

import base.Base;
import perso.Personnage;

public class Action_Coffre extends Action_Perso {

	public String help() {
		String out = "";
		out += Action.coffre_liste.getName() + "\n";
		out += Action.coffre_put.getName() + "\n";
		out += Action.base.getName() + "\n";
		out += this.help_perso();
		return out;
	}

	public String base(Personnage perso) {
		perso.position = Position.base;
		return "Vous etes de retour a la base.\n";
	}

	public String action(Personnage perso, Base base, String in) {
		if (Action.coffre_liste.test(in))
			return base.coffre.liste();
		else if (Action.coffre_put.test(in)) {
			if (base.coffre.putItem(perso.inv.getItem(0)))
				return "Item non déposé.\n";
			else {
				perso.inv.removeItem(0);
				return "Item déposé.";
			}
		} else if (Action.base.test(in))
			return this.base(perso);
		else if (this.actionPersoTest(in))
			return this.actionPerso(perso, in);
		else
			return this.help();
	}
}
