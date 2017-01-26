package action;

import action.Action_CraftingTable.Action_Craft;
import base.Base;
import core.Recipe;
import core.event.Event_extends;
import craft.Craft_Category;
import craft.RecipeArmors;
import craft.RecipeItems;
import craft.RecipeTools;
import craft.RecipeWeapons;
import perso.Personnage;

public class Action_Equiper extends Action_Perso {

	private Personnage perso;
	private Base base;
	private Boolean equiper = false;

	public Action_Equiper(Personnage perso, Base base) {
		this.perso = perso;
		this.base = base;
	}

	public String base() {
		this.perso.position = this.perso.lastPosition;
		String out = "Vous etes de retour.\n";
		this.equiper = false;
		return out;
	}

	private String listeEquipable() {
		return perso.inv.liteEquipable();
	}

	public String help() {
		String out = "";
		out += this.listeEquipable();
		out += Action_equiper.retour.action.getName() + "\n";
		this.equiper = true;
		return out;
	}

	public String action(String in) {
		try {
			if (this.equiper)
				return this.equiper(in);
			else
				return this.help();
		} catch (NumberFormatException e) {

			if (Action_equiper.retour.action.test(in))
				return this.base();
			else
				return this.help();
		}

	}

	private String equiper(String in) {
		if (!in.equals("")) {
			this.perso.setEquipable(this.perso.inv.getItem(Integer.parseInt(in.substring(0, 1))));
			this.equiper = false;
			this.perso.inv.removeItem(Integer.parseInt(in.substring(0, 1)));
			return this.perso.listeStuff();
		} else
			return "erreur action_equiper";
	}

	public enum Action_equiper {

		retour("retour");

		public core.Action action;

		Action_equiper(String str) {
			this.action = new core.Action(str);
		}
	}
}
