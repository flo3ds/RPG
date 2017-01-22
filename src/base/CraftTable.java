package base;

import items.RecipeItems;

public class CraftTable {

	public String craftTableList(Craft_Category cc){
		String out = "";
		for(int i=0; i<RecipeItems.values().length; i++)
			out += RecipeItems.values()[i].recipe(cc);
		return out;
	}
	
}
