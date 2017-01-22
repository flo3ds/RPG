package tools;

import base.Craft_Category;
import items.Bois;
import items.Minerai;
import items.PlancheBois;
import items.Plaque_Acier;
import items.Vis;
import items.Minerai.matiere;

public enum RecipeITools {

	//Avec des saut a la ligne pour rendre plus lisible :
	vis("vis", // le nom
		new Vis(), //L'Item fabriquer
		new Object[]{// Le tableau des items requis pour la recette
				new Minerai(Minerai.matiere.cuivre, 2), // le 1er items de la recette minerai de cuivre x2
				new Minerai(Minerai.matiere.fer, 3)}	// le 2eme items de la recette minerai de fer x3
	), //fermer la parenthese et une virgule
	
	//et sur une seule ligne sa donne sa:
	plaque_acier("Plaque d'acier",new Plaque_Acier(), new Object[]{new Vis(2), new Minerai(Minerai.matiere.fer, 2)}), // point virgule a la fin fin
	
	planche_bois("Planche en bois", new PlancheBois(), new Object[]{new Bois(2)});
	
	private String name;
	private Object[] obj;
	private Object item;
	
	RecipeITools(String name, Object item, Object[] obj){
		this.name = name;
		this.obj = obj;
		this.item = item;
	}
	
	public String recipe(Craft_Category cc){
		String out = "";
		out += this.name + " => ";
		for(int i=0; i < this.obj.length; i++){
				out += this.obj[i].toString() +" | ";
		}
			
		return out + "\n";
	}
	
	public Object[] getObjectForRecipe(){
		return this.obj;
	}
	
	public Object getItem(){
		return this.item;
	}
}
