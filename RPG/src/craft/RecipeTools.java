package craft;

import core.Recipe;
import items.Bois;
import tools.Hache;
import tools.Marteau;

//On ne peut pas mettre de tools dans les composant d'une recette pour l'instant.

public enum RecipeTools {

	marteau("marteau", new Marteau(), new Object[] { new Bois(2) }, null), hache("hache", new Hache(),
			new Object[] { new Bois(2) }, null);

	public Recipe recipe;

	RecipeTools(String name, Object item, Object[] obj, Object[] tool) {
		this.recipe = new Recipe(name, item, obj, tool);
	}

}
