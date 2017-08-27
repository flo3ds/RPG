package layout;

import org.lwjgl.input.Mouse;

import core.Stack;
import graphicEngine.SpriteBatch;
import perso.Inventaire;
import perso.Personnage;
import recipe.Recipe;
import recipe.RecipeCraft;
import tileEntity.TileEntityChest;
import tileEntity.TileEntityCrafting_station;

public class Layout_crafting extends Layout {
	
	private Container[] con_inv;
	private Container[] con_bar;
	
	private ContainerVoid[] con_item;
	
	private Bouton flecheL;
	private Bouton flecheR;

	private TileEntityCrafting_station tileEntity;
	private int inv_size;
	private Boolean clicked2 = false;
	
	public Layout_crafting(TileEntityCrafting_station tileEntity, Personnage perso) {
		super("crafting");
		inv_size = perso.inv.getSize();
		con_inv = new Container[inv_size];
		con_bar = new Container[9];
		con_item = new ContainerVoid[5];
		
		flecheL = new Bouton("res/layout/left.png");
		flecheR = new Bouton("res/layout/right.png");
		
		for(int i=0; i<inv_size; i++)
			con_inv[i] = addContainer( perso.inv.getItem(i));
		
		for(int i=0; i<9; i++)
			con_bar[i] = addContainer( perso.getGUI().getItemBar()[i]);
		
		for(int i=0; i<5; i++)
			con_item[i] = new ContainerVoid();
		
	}

	public void draw(SpriteBatch batch, int x, int y, Inventaire inv) {
		x -= 320;
		y -= 250;
		draw_extends(batch, x, y);
		
		int xx = 0;
		
		for(int i=0; i<5; i++) {
			con_item[i].draw(batch, (x+130) + (i*42), (y+80) + ((i/9)*45), RecipeCraft.values()[i].recipe.getResult());
			xx++;
			if(xx==9)
				xx=0;
			}
		
		flecheL.draw(batch, x+65, y+78);
		flecheR.draw(batch, x+540, y+78);

		xx = 0;

		for(int i=0; i<inv.getSize(); i++) {
			con_inv[i].draw(batch, (x+130)+(xx*42), (y+230) + ((i/9)*45));
			xx++;
			if(xx==9)
				xx=0;
		}
		
		for(int i=0; i<9; i++) 
			con_bar[i].draw(batch, (x+130) + (i*42), (y+370));
		
		draw_post_extends(batch);
	}
	
	public void click (int x, int y, Personnage perso) {
		super.click(x, y, perso);
		if (Mouse.isButtonDown(0) && clicked2  == false) {
			clicked2 = true;
			}else{
				
				if (Mouse.isButtonDown(0) == false && clicked2 == true){
					for(int i=0; i<con_item.length; i++) {
						if( con_item[i].clicked(x, y)) {
							craft(i, perso);
						}
					}
					
					clicked2 = false;
					}
				}
	}
	
	public void craft(int recipeI, Personnage perso) {
		Recipe recipe = RecipeCraft.values()[recipeI].recipe;
		
		if(recipe.getToolForRecipe() != null)
		for(int i=0; i<recipe.getToolForRecipe().length; i++)
			if(!perso.inv.haveItem(recipe.getToolForRecipe()[i]))
				return;
		
		for(int i=0; i<recipe.getObjectForRecipe().length; i++)
			if(!perso.inv.haveItem(recipe.getObjectForRecipe()[i]))
				return;
		
		for(int i=0; i<recipe.getObjectForRecipe().length; i++){
			int x = perso.inv.findItem(recipe.getObjectForRecipe()[i]);
			perso.inv.getItem(x).subNombre(recipe.getObjectForRecipe()[i].getNombre());
		}
		
		perso.inv.putItem(recipe.getResult());

	}

}
