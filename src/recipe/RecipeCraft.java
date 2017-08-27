package recipe;

import core.Stack;
import init.Tools;
import tool.Tool;
import init.Items;
import init.Objects;

//On ne peut pas mettre de tools dans les composant d'une recette pour l'instant.

public enum RecipeCraft {

	axe("hache", new Stack(Tools.AXE, 1), new Stack[] { new Stack(Items.BOIS, 2) }, null),
	mur_bois("mur de bois", new Stack(Objects.MUR_BOIS, 1), new Stack[] { new Stack(Items.PLANCHE, 2) }, null),
	porte("porte", new Stack(Objects.DOOR, 1), new Stack[] { new Stack(Items.PLANCHE, 4) }, null),
	pick("pick", new Stack(Tools.PICK, 1), new Stack[] { new Stack(Items.BOIS, 2) }, null),
	chest("chest", new Stack(Objects.CHEST, 1), new Stack[] { new Stack(Items.BOIS, 2), new Stack(Items.PLANCHE, 8) }, null)
	;
	public Recipe recipe;

	RecipeCraft(String name, Stack item, Stack[] obj, Stack[] tool) {
		this.recipe = new Recipe(name, item, obj, tool);
	}

}
