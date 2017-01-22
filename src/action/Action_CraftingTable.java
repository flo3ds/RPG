package action;

import base.Base;
import base.Craft_Category;
import items.Item;
import items.RecipeItems;
import perso.Personnage;

public class Action_CraftingTable extends Action_Perso {

	private Craft_Category cc;
	
	public String help() {
		String out = "";
		out += Action.build.getName() + "\n";
		out += Action.base.getName() + "\n";
		out += this.help_perso();
		return out;
	}
	
	public String build(Base base, Personnage perso){
		this.cc = Craft_Category.items;
		return base.craftTable.craftTableList(this.cc) + "\nEntrer le numero de l'item a crafter :";
	}

	public String base(Personnage perso) {
		perso.position = Position.base;
		this.cc = null;
		return "Vous etes de retour a la base.\n";
	}

	public String action(Personnage perso, Base base, String in) {
		if(this.cc == null){
		if (Action.build.test(in))
			return this.build(base, perso);
		else if (Action.base.test(in))
			return this.base(perso);
		else if (this.actionPersoTest(in))
			return this.actionPerso(perso, in);
		else
			return this.help();
		}else if(this.cc == Craft_Category.items){
			return this.buildItem(in, base, perso);
		}else
			return "error";
	}
	
	public String applyRecipe(short x, Personnage perso){
		Object obj[] = RecipeItems.values()[x].getObjectForRecipe();		
		
		for(int j=0; j < obj.length; j++){
			for(int i=0; i < perso.inv.getInventaire().size(); i++){
				Object objTest = perso.inv.getItem(i);
				if(((Item)obj[j]).getId().equals(((Item)objTest).getId())){ 
					if(((Item)obj[j]).getNombre() <= ((Item) objTest).getNombre() ){
						perso.inv.subItem(i, ((Item)obj[j]).getNombre());
						break;
					}
				}
				
			}
		}
		
		perso.inv.putItem(RecipeItems.values()[x].getItem());
		return "Vous avez creé : " + RecipeItems.values()[x].getItem().toString();
	}

	private String buildItem(String in, Base base, Personnage perso) {
		try{
		short x = (short) Integer.parseInt(in);
		if(this.testRecipe(x, perso)){
			return this.applyRecipe(x, perso);
		}
		else
			return "Vous n'avez pas les ressources sur vous!\n";
		}catch(NumberFormatException e){
			if(in.equals("craft")){
				this.cc = null;
				return "Vous revenez a la table de craft";
			}else
			return "entrer un numero ou craft pour revenir a la table.\n";
		}
		
	}
	
	private Boolean testRecipe(short x, Personnage perso){
		Object obj[] = RecipeItems.values()[x].getObjectForRecipe();
		Boolean tab[] = new Boolean[obj.length];
		
		for(int i=0; i <tab.length; i++){
			tab[i] = false;
		}
		
		for(int j=0; j < obj.length; j++){
			for(int i=0; i < perso.inv.getInventaire().size(); i++){
				Object objTest = perso.inv.getItem(i);
				if(((Item)obj[j]).getId().equals(((Item)objTest).getId())){ 
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
	
}
