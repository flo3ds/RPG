package action;

import java.util.Arrays;

import base.Base;
import base.Craft_Category;
import core.Inventable;
import core.Recipe;
import core.event.Event_extends;
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
		out += Action_Craft.build_item.action.getName() + "\n";
		out += Action_Craft.build_weapon.action.getName() + "\n";
		out += Action_Craft.build_tool.action.getName() + "\n";
		out += Action_Craft.base.action.getName() + "\n";
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
		this.cc = null;
		this.perso.position = Position.base;
		String out = "Vous etes de retour a la base.\n";
		if(this.base.event.getEvent() != null)
		out += ((Event_extends) this.base.event.getEvent()).getIntro();
		return out;
	}

	public String action(String in) {
		try {
			if (this.cc == null) {
				if (Action_Craft.build_item.action.test(in))
					return this.buildItem();
				else if (Action_Craft.build_tool.action.test(in))
					return this.buildTool();
				else if (Action_Craft.build_weapon.action.test(in))
					return this.buildWeapon();
				else if (Action_Craft.base.action.test(in))
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
			if (Action_Craft.craftTable.action.test(in)) {
				this.cc = null;
				return "Vous revenez a la table de craft";
			} else if (this.cc == Craft_Category.items)
				return this.base.craftTable.craftTableListItem() + "\ncraft";
			else if (this.cc == Craft_Category.tools)
				return this.base.craftTable.craftTableListTool() + "\ncraft";
			else if (this.cc == Craft_Category.weapons)
				return this.base.craftTable.craftTableListWeapon() + "\ncraft";
			else
				return "error action_craftTable\n";
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

	public enum Action_Craft {

		base("base"), craftTable("table de craft"), build_item("build item"), build_tool("build tool"), build_weapon(
				"build weapon");

		public core.Action action;

		Action_Craft(String str) {
			this.action = new core.Action(str);
		}
	}

}
