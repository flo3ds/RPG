package craft;

import core.Recipe;
import items.Bois;
import items.Minerai;
import items.PalissadeBois;
import items.PlancheBois;
import items.Plaque_Acier;
import items.Vis;
import tools.Marteau;

//On ne peut pas mettre de tools dans les composant d'une recette pour l'instant.

public enum RecipeItems {

	// Avec des saut a la ligne pour rendre plus lisible :
	vis("vis", // le nom
			new Vis(), // L'Item fabriquer
			new Object[] { // Le tableau des items requis pour la recette
					new Minerai(Minerai.matiere.cuivre, 2), // le 1er items de
															// la recette
															// minerai de cuivre
															// x2
					new Minerai(Minerai.matiere.fer, 3) } // le 2eme items de la
															// recette minerai
															// de fer x3
			, null // ici le ou les outils requis, null si il n'y a pas d'outil
					// requis
	), // fermer la parenthese et une virgule

	planche_bois("Planche en bois", new PlancheBois(), new Object[] { new Bois(2) },
			null), palissade_Bois("Palissade en Bois", new PalissadeBois(), new Object[] { new PlancheBois(6) }, null),

	// et sur une seule ligne sa donne sa:
	plaque_acier("Plaque d'acier", new Plaque_Acier(), new Object[] { new Vis(2), new Minerai(Minerai.matiere.fer, 2) },
			new Object[] { new Marteau() }); // point virgule a la fin fin

	public Recipe recipe;

	RecipeItems(String name, Object item, Object[] obj, Object[] tool) {
		this.recipe = new Recipe(name, item, obj, tool);
	}
}
