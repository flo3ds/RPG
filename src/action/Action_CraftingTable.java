package action;

import java.util.Arrays;

import base.Base;
import base.Craft_Category;
import core.Inventable;
import core.Recipe;
import items.Item;
import items.RecipeItems;
import perso.Personnage;
import tools.RecipeTools;
import weapons.RecipeWeapons;

public class Action_CraftingTable extends Action_Perso {

	private Craft_Category cc;

	private Personnage perso;
	private Base base;

	public Action_CraftingTable(Personnage perso, Base base) {
		this.perso = perso;
		this.base = base;
	}

	public String help() {
		String out = "";
		out += Action.build_item.getName() + "\n";
		out += Action.build_tool.getName() + "\n";
		out += Action.base.getName() + "\n";
		out += this.help_perso();
		return out;
	}

	public String buildItem() {
		this.cc = Craft_Category.items;
		return perso.inv.liste();
	}

	public String buildTool() {
		this.cc = Craft_Category.tools;
		return perso.inv.liste();
	}
	
	public String buildWeapon() {
		this.cc = Craft_Category.weapons;
		return perso.inv.liste();
	}

	public String base() {
		perso.position = Position.base;
		this.cc = null;
		return "Vous etes de retour a la this.base.\n";
	}

	public String action(String in) {
		try {
			if (this.cc == null) {
				if (Action.build_item.test(in))
					return this.buildItem();
				else if (Action.build_tool.test(in))
					return this.buildTool();
				else if (Action.build_weapon.test(in))
					return this.buildWeapon();
				else if (Action.base.test(in))
					return this.base();
				else if (this.actionPersoTest(in))
					return this.actionPerso(perso, in);
				else
					return this.help();
			} else if (this.cc == Craft_Category.items) {
				if (in.equals(""))
					in = "error";
				return this.build(RecipeItems.values()[(short) Integer.parseInt(in.substring(0, 1))].recipe);
			} else if (this.cc == Craft_Category.weapons) {
				if (in.equals(""))
					in = "error";
				return this.build(RecipeWeapons.values()[(short) Integer.parseInt(in.substring(0, 1))].recipe);
			} else if (this.cc == Craft_Category.tools) {
				if (in.equals(""))
					in = "error";
				return this.build(RecipeTools.values()[(short) Integer.parseInt(in.substring(0, 1))].recipe);
			} else
				return "error";
		} catch (NumberFormatException e) {
			if (Action.craftTable.test(in)) {
				this.cc = null;
				return "Vous revenez a la table de craft";
			} else if (this.cc == Craft_Category.items)
				return this.base.craftTable.craftTableListItem() + "\ncraft";
			else if (this.cc == Craft_Category.tools)
				return this.base.craftTable.craftTableListTool() + "\ncraft";
			else
				return "error\n";
		}
	}

	public String applyRecipe(Recipe recipe) {
		Object obj[] = recipe.getObjectForRecipe();

		for (int j = 0; j < obj.length; j++) {
			for (int i = 0; i < perso.inv.getInventaire().size(); i++) {
				Object objTest = perso.inv.getItem(i);
				if (((Inventable) obj[j]).getId().equals(((Inventable) objTest).getId())) {
					if (((Item) obj[j]).getNombre() <= ((Item) objTest).getNombre()) {
						perso.inv.subItem(i, ((Item) obj[j]).getNombre());
						break;
					}
				}
			}
		}

		perso.inv.putItem(recipe.getItem());
		return perso.inv.liste();
	}

	private String build(Recipe recipe) {

		if (!this.testTools(recipe))
			return "Vous n'avez pas les outils requis";
		if (this.testRecipe(recipe)) {
			return this.applyRecipe(recipe);
		} else
			return "Vous n'avez pas les ressources sur vous!\n";

	}

	private Boolean testRecipe(Recipe recipe) {
		Object obj[] = recipe.getObjectForRecipe();
		Boolean tab[] = new Boolean[obj.length];

		Arrays.fill(tab, Boolean.FALSE);

		for (int j = 0; j < obj.length; j++) {
			if (perso.inv.haveItem(obj[j], (short) ((Item) obj[j]).getNombre()))
				tab[j] = true;
		}

		for (int i = 0; i < tab.length; i++)
			if (tab[i] == false)
				return false;

		return true;
	}

	// A améliorer plus tard
	private Boolean testTools(Recipe recipe) {
		Object obj[] = recipe.getToolForRecipe();
		if (obj != null) {
			Boolean tab[] = new Boolean[obj.length];
			Arrays.fill(tab, Boolean.FALSE);

			for (int j = 0; j < obj.length; j++) {
				if (perso.inv.haveItem(obj[j]))
					tab[j] = true;
			}

			for (int i = 0; i < tab.length; i++) {
				if (tab[i] == false)
					return false;

			}
		}

		return true;
	}

}
