package craft;

import armor.Armure_Cuir;
import armor.Armure_Fer;
import core.Recipe;
import items.Bois;

//On ne peut pas mettre de tools dans les composant d'une recette pour l'instant.

public enum RecipeArmors {

	armure_cuir("armure de cuire", new Armure_Cuir(), new Object[] { new Bois(2) }, null), armure_fer("armure de fer",
			new Armure_Fer(), new Object[] { new Bois(2) }, null);

	public Recipe recipe;

	RecipeArmors(String name, Object item, Object[] obj, Object[] tool) {
		this.recipe = new Recipe(name, item, obj, tool);
	}

}
