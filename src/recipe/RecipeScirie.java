package recipe;

import core.Stack;
import init.Tools;
import tool.Tool;
import init.Items;

//On ne peut pas mettre de tools dans les composant d'une recette pour l'instant.

public enum RecipeScirie {

	planche("planche", new Stack(Items.PLANCHE, 2), new Stack(Items.BOIS, 1) ),
	planche2("planche2", new Stack(Items.PLANCHE, 1), new Stack(Items.BRANCHE, 4) );

	public RecipeMachine recipe;

	RecipeScirie(String name, Stack item, Stack obj) {
		this.recipe = new RecipeMachine(name, item, obj);
	}

}
