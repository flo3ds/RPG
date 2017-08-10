package recipe;

import core.Stack;
import init.Tools;
import tool.Tool;
import init.Items;

//On ne peut pas mettre de tools dans les composant d'une recette pour l'instant.

public enum RecipeCraft {

	axe("hache", new Stack(Tools.AXE, 1), new Stack[] { new Stack(Items.BOIS, 2) }, null);

	public Recipe recipe;

	RecipeCraft(String name, Stack item, Stack[] obj, Stack[] tool) {
		this.recipe = new Recipe(name, item, obj, tool);
	}

}
