package craft;

import gui.layout.StructRet;

public class CraftTable {

	public StructRet craftTableListItem() {
		StructRet out = new StructRet();
		for (int i = 0; i < RecipeItems.values().length; i++)
			out.add(RecipeItems.values()[i].recipe.recipe(), i);
		return out;
	}

	public StructRet craftTableListTool() {
		StructRet out = new StructRet();
		for (int i = 0; i < RecipeTools.values().length; i++)
			out.add(RecipeTools.values()[i].recipe.recipe(), i);
		return out;
	}

	public StructRet craftTableListWeapon() {
		StructRet out = new StructRet();
		for (int i = 0; i < RecipeWeapons.values().length; i++)
			out.add(RecipeWeapons.values()[i].recipe.recipe(), i);
		return out;
	}

	public StructRet craftTableListArmor() {
		StructRet out = new StructRet();
		for (int i = 0; i < RecipeArmors.values().length; i++)
			out.add(RecipeArmors.values()[i].recipe.recipe(), i);
		return out;
	}

}
