package action;

import java.util.Arrays;

import base.Base;
import core.Inventable;
import core.Item;
import core.Recipe;
import craft.Craft_Category;
import craft.RecipeArmors;
import craft.RecipeItems;
import craft.RecipeCraft;
import craft.RecipeWeapons;
import gui.layout.StructRet;
import perso.Personnage;

public class Action_CraftingTable implements Actionable {

	private Craft_Category cc = null;

	private Personnage perso;
	private Base base;

	public Action_CraftingTable(Personnage perso, Base base) {
		this.perso = perso;
		this.base = base;
	}

	private StructRet error() {
		StructRet out = new StructRet();
		out.add("error", 0);
		return out;
	}

	public StructRet buildItem(String header) {
		this.cc = Craft_Category.items;
		StructRet out = this.base.craftTable.craftTableListItem();
		out.setHeader(header);
		out.add(Action_Craft.craftTable.action.getName(), Action_Craft.craftTable.action.getId());
		return out;
	}

	public StructRet buildTool(String header) {
		this.cc = Craft_Category.tools;
		StructRet out = this.base.craftTable.craftTableListTool();
		out.setHeader(header);
		out.add(Action_Craft.craftTable.action.getName(), Action_Craft.craftTable.action.getId());
		return out;
	}

	public StructRet buildWeapon(String header) {
		this.cc = Craft_Category.weapons;
		StructRet out = this.base.craftTable.craftTableListWeapon();
		out.setHeader(header);
		out.add(Action_Craft.craftTable.action.getName(), Action_Craft.craftTable.action.getId());
		return out;
	}

	public StructRet buildArmor(String header) {
		this.cc = Craft_Category.armors;
		StructRet out = this.base.craftTable.craftTableListArmor();
		out.setHeader(header);
		out.add(Action_Craft.craftTable.action.getName(), Action_Craft.craftTable.action.getId());
		return out;
	}

	public StructRet init() {
		this.cc = null;
		StructRet out = new StructRet();
		out.setHeader("Category :");
		out.add(Action_Craft.build_item.action.getName(), Action_Craft.build_item.action.getId());
		out.add(Action_Craft.build_weapon.action.getName(), Action_Craft.build_weapon.action.getId());
		out.add(Action_Craft.build_armor.action.getName(), Action_Craft.build_armor.action.getId());
		out.add(Action_Craft.build_tool.action.getName(), Action_Craft.build_tool.action.getId());
		return out;
	}

	public StructRet action(int id) {
		System.out.println("id = " + id);
		if (this.cc == null) {
			String head = "Items :";
			if (Action_Craft.build_item.action.test(id))
				return this.buildItem(head);
			else if (Action_Craft.build_tool.action.test(id))
				return this.buildTool(head);
			else if (Action_Craft.build_weapon.action.test(id))
				return this.buildWeapon(head);
			else if (Action_Craft.build_armor.action.test(id))
				return this.buildArmor(head);
			else
				return this.error();
		} else if (Action_Craft.craftTable.action.test(id)) {
			return this.init();
		} else if (this.cc == Craft_Category.items) {
			return this.build(RecipeItems.values()[(short) id].recipe);
		} else if (this.cc == Craft_Category.weapons) {

			return this.build(RecipeWeapons.values()[(short) id].recipe);
		} else if (this.cc == Craft_Category.armors) {

			return this.build(RecipeArmors.values()[(short) id].recipe);
		} else if (this.cc == Craft_Category.tools) {

			return this.build(RecipeCraft.values()[(short) id].recipe);
		}

		return error();
	}

	public StructRet applyRecipe(Recipe recipe) {
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
		String head = recipe.getItem().toString() + " creer.";
		if (cc == Craft_Category.items)
			return this.buildItem(head);
		else if (cc == Craft_Category.tools)
			return this.buildTool(head);
		else if (cc == Craft_Category.weapons)
			return this.buildWeapon(head);
		else if (cc == Craft_Category.armors)
			return this.buildArmor(head);
		else
			return error();
	}

	private StructRet build(Recipe recipe) {
		if (!this.testTools(recipe)) {
			String head = "Vous n'avez pas les outils !";
			if (cc == Craft_Category.items)
				return this.buildItem(head);
			else if (cc == Craft_Category.tools)
				return this.buildTool(head);
			else if (cc == Craft_Category.weapons)
				return this.buildWeapon(head);
			else if (cc == Craft_Category.armors)
				return this.buildArmor(head);
			else
				return error();
		}
		if (this.testRecipe(recipe)) {
			return this.applyRecipe(recipe);
		} else {
			String head = "Vous n'avez pas les ressources !";
			if (cc == Craft_Category.items)
				return this.buildItem(head);
			else if (cc == Craft_Category.tools)
				return this.buildTool(head);
			else if (cc == Craft_Category.weapons)
				return this.buildWeapon(head);
			else if (cc == Craft_Category.armors)
				return this.buildArmor(head);
			else
				return error();
		}

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

		build_item("build_item", 0), build_tool("build_tool", 1), build_weapon("build weapon",
				2), build_armor("build armor", 3), craftTable("retour", 99);

		public core.Action action;

		Action_Craft(String str, int id) {
			this.action = new core.Action(str, id);
		}
	}

}
