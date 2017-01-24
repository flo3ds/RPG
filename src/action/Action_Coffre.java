package action;

import base.Base;
import perso.Personnage;

public class Action_Coffre extends Action_Perso {

	private Personnage perso;
	private Base base;
	
	public Action_Coffre(Personnage perso, Base base){
		this.perso = perso;
		this.base = base;
	}
	
	public String help() {
		String out = "";
		out += Action.coffre_liste.getName() + "\n";
		out += Action.coffre_put.getName() + "\n";
		out += Action.base.getName() + "\n";
		out += this.help_perso();
		return out;
	}

	public String base() {
		this.perso.position = Position.base;
		return "Vous etes de retour a la this.base.\n";
	}

	public String action(String in) {
		if (Action.coffre_liste.test(in))
			return this.base.coffre.liste();
		else if (Action.coffre_put.test(in)) {
			if (this.base.coffre.putItem(this.perso.inv.getItem(0)))
				return "Item non déposé.\n";
			else {
				this.perso.inv.removeItem(0);
				return "Item déposé.";
			}
		} else if (Action.base.test(in))
			return this.base();
		else if (this.actionPersoTest(in))
			return this.actionPerso(this.perso, in);
		else
			return this.help();
	}
}
