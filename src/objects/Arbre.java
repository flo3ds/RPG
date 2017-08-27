package objects;

import java.io.IOException;

import org.lwjgl.Sys;

import core.Stack;
import graphicEngine.Texture;
import graphicEngine.TextureManager;
import graphicEngine.TextureRegion;
import graphicEngine.Util;
import graphicEngine.Vector2D;
import graphicEngine.world.World;
import graphicEngine.world.Worldable;
import init.Items;
import init.Tools;
import perso.Personnage;

public class Arbre extends Object {

	public Arbre() {
		super("arbre");
		
		addState("coupé");
		
		setDecalageSpriteX(-32);
		setDecalageSpriteY(-96);
		
	}
	

	public void click(Personnage perso, Worldable world, Vector2D pos_click) {
		//System.out.print("click = "+pos_click.x+" : "+pos_click.y+"\n");
		if(perso.getGUI().getCurrentItem().compareID(Tools.AXE)) {
			perso.inv.putItem(new Stack(Items.BOIS, 2));
			destroy(world, pos_click);
		}else {
			setState("coupé");
			perso.inv.putItem(new Stack(Items.BRANCHE, 2));
		}
	}
	
	
}
