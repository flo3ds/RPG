package items;

import base.Craft_Category;
import core.Recipe;
import tools.Marteau;

public enum RecipeItems {

	//Avec des saut a la ligne pour rendre plus lisible :
	vis("vis", // le nom
		new Vis(), //L'Item fabriquer
		new Object[]{// Le tableau des items requis pour la recette
				new Minerai(Minerai.matiere.cuivre, 2), // le 1er items de la recette minerai de cuivre x2
				new Minerai(Minerai.matiere.fer, 3)}	// le 2eme items de la recette minerai de fer x3
	,null // ici le ou les outils requis, null si il n'y a pas d'outil requis
	), //fermer la parenthese et une virgule
	
	//et sur une seule ligne sa donne sa:
	plaque_acier("Plaque d'acier",new Plaque_Acier(), new Object[]{new Vis(2), new Minerai(Minerai.matiere.fer, 2)}, new Object[]{new Marteau()}), // point virgule a la fin fin
	
	planche_bois("Planche en bois", new PlancheBois(), new Object[]{new Bois(2)}, null);
	
public Recipe recipe;
	
	RecipeItems(String name, Object item, Object[] obj, Object[] tool){
		this.recipe = new Recipe(name, item, obj, tool);
	}
}
