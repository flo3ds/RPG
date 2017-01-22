package tools;

import core.Recipe;
import items.Bois;

public enum RecipeTools {

	
	marteau("marteau", new Marteau(), new Object[]{new Bois(2)}, null);
	
	public Recipe recipe;
	
	RecipeTools(String name, Object item, Object[] obj, Object[] tool){
		this.recipe = new Recipe(name, item, obj, tool);
	}
	
}
