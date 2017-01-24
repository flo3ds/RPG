package weapons;

import core.Recipe;
import items.Bois;

//On ne peut pas mettre de tools dans les composant d'une recette pour l'instant.

public enum RecipeWeapons {

	arc("arc", new Arc(), new Object[] { new Bois(2) }, null);

	public Recipe recipe;

	RecipeWeapons(String name, Object item, Object[] obj, Object[] tool) {
		this.recipe = new Recipe(name, item, obj, tool);
	}

}
