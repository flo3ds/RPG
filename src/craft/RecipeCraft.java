package craft;

import core.Stack;
import core.Recipe;
import init.Tools;
import tool.Tool;
import init.Items;

//On ne peut pas mettre de tools dans les composant d'une recette pour l'instant.

public enum RecipeCraft {

	axe("hache", Tools.AXE, new Stack[] { new Stack(Items.BOIS, 2) }, null);

	public Recipe recipe;

	RecipeCraft(String name, Tool item, Stack[] obj, Tool[] tool) {
		this.recipe = new Recipe(name, item, obj, tool);
	}

}
