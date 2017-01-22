package action;

import base.Base;
import base.Craft_Category;
import core.Inventable;
import core.Recipe;
import items.Item;
import items.RecipeItems;
import perso.Personnage;
import tools.RecipeTools;
import tools.Tool;

public class Action_CraftingTable extends Action_Perso {

	private Craft_Category cc;
	
	public String help() {
		String out = "";
		out += Action.build_item.getName() + "\n";
		out += Action.build_tool.getName() + "\n";
		out += Action.base.getName() + "\n";
		out += this.help_perso();
		return out;
	}
	
	public String buildItem(Base base, Personnage perso){
		this.cc = Craft_Category.items;
		return base.craftTable.craftTableListItem() + "\nEntrer le numero de l'item a crafter :";
	}
	
	public String buildTool(Base base, Personnage perso){
		this.cc = Craft_Category.tools;
		return base.craftTable.craftTableListTool() + "\nEntrer le numero de l'item a crafter :";
	}

	public String base(Personnage perso) {
		perso.position = Position.base;
		this.cc = null;
		return "Vous etes de retour a la base.\n";
	}

	public String action(Personnage perso, Base base, String in) {
		try{
		if(this.cc == null){
		if (Action.build_item.test(in))
			return this.buildItem(base, perso);
		if (Action.build_tool.test(in))
			return this.buildTool(base, perso);
		else if (Action.base.test(in))
			return this.base(perso);
		else if (this.actionPersoTest(in))
			return this.actionPerso(perso, in);
		else
			return this.help();
		}else if(this.cc == Craft_Category.items)
			return this.build(perso, RecipeItems.values()[(short) Integer.parseInt(in)].recipe);
		else if(this.cc == Craft_Category.tools)
			return this.build(perso, RecipeTools.values()[(short) Integer.parseInt(in)].recipe);
		else
			return "error";
	}catch(NumberFormatException e){
		if(in.equals("craft")){
			this.cc = null;
			return "Vous revenez a la table de craft";
		}else
		return "entrer un numero ou craft pour revenir a la table.\n";
	}
	}
	
	
	
	public String applyRecipe(Personnage perso, Recipe recipe){
		Object obj[] = recipe.getObjectForRecipe();		
		
		for(int j=0; j < obj.length; j++){
			for(int i=0; i < perso.inv.getInventaire().size(); i++){
				Object objTest = perso.inv.getItem(i);
				if(((Inventable)obj[j]).getId().equals(((Inventable)objTest).getId())){ 
					if(((Item)obj[j]).getNombre() <= ((Item) objTest).getNombre() ){
						perso.inv.subItem(i, ((Item)obj[j]).getNombre());
						break;
					}
				}
			}
		}
		
		perso.inv.putItem(recipe.getItem());
		return "Vous avez creé : " + recipe.getItem().toString();
	}

	private String build(Personnage perso, Recipe recipe) {
		
		if(!this.testTools(perso, recipe))
			return "Vous n'avez pas les outils requis";
		if(this.testRecipe(perso, recipe)){
			return this.applyRecipe(perso, recipe);
		}
		else
			return "Vous n'avez pas les ressources sur vous!\n";
		
		
	}
	
	private Boolean testRecipe(Personnage perso, Recipe recipe){
		Object obj[] = recipe.getObjectForRecipe();
		Boolean tab[] = new Boolean[obj.length];
		
		for(int i=0; i <tab.length; i++){
			tab[i] = false;
		}
		
		for(int j=0; j < obj.length; j++){
			for(int i=0; i < perso.inv.getInventaire().size(); i++){
				Object objTest = perso.inv.getItem(i);
				if(((Inventable)obj[j]).getId().equals(((Inventable)objTest).getId())){ 
					if(((Item)obj[j]).getNombre() <= ((Item) objTest).getNombre() ){
						tab[j] = true;
						break;
					}
				}
				tab[j] = false;
			}
		}
		
		for(int i=0; i <tab.length; i++){
			if(tab[i] == false){
				return false;
			}
				
		}
		
		return true;
	}
	
	
	//A améliorer plus tard
	private Boolean testTools(Personnage perso, Recipe recipe){
		Object obj[] = recipe.getToolForRecipe();
		if(obj != null){
		Boolean tab[] = new Boolean[obj.length];
		
		for(int i=0; i <tab.length; i++){
			tab[i] = false;
		}
		
		for(int j=0; j < obj.length; j++){
			for(int i=0; i < perso.inv.getInventaire().size(); i++){
				Object objTest = perso.inv.getItem(i);
				if(((Inventable)obj[j]).getId().equals(((Inventable)objTest).getId())){ 
						tab[j] = true;
						break;
				}
				tab[j] = false;
			}
		}
		
		for(int i=0; i <tab.length; i++){
			if(tab[i] == false){
				return false;
			}
				
		}
		}
		
		return true;
	}
	
}
