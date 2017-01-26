package craft;

import core.Recipe;
import items.Bois;
import weapons.Arc;
import weapons.Epee_Fer;

//On ne peut pas mettre de tools dans les composant d'une recette pour l'instant.

public enum RecipeWeapons {

	arc("arc", new Arc(), new Object[] { new Bois(2) }, null),
	epee_fer("epée en fer", new Epee_Fer(), new Object[] { new Bois(2) }, null);

	public Recipe recipe;

	RecipeWeapons(String name, Object item, Object[] obj, Object[] tool) {
		this.recipe = new Recipe(name, item, obj, tool);
	}

}
