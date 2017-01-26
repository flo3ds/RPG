package craft;

public class CraftTable {

	public String craftTableListItem() {
		String out = "";
		for (int i = 0; i < RecipeItems.values().length; i++)
			out += i + ":" + RecipeItems.values()[i].recipe.recipe();
		return out;
	}

	public String craftTableListTool() {
		String out = "";
		for (int i = 0; i < RecipeTools.values().length; i++)
			out += i + ":" + RecipeTools.values()[i].recipe.recipe();
		return out;
	}

	public String craftTableListWeapon() {
		String out = "";
		for (int i = 0; i < RecipeWeapons.values().length; i++)
			out += i + ":" + RecipeWeapons.values()[i].recipe.recipe();
		return out;
	}

}
