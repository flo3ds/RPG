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
	
	public void click(Personnage perso, World world, Vector2D pos_click) {
		if(perso.getGUI().getCurrentItem().getItem().getId().equals(Tools.AXE.getId())) {
			setState("coupé");
			perso.inv.putItem(new Stack(Items.BOIS, 2));
		}
	}
	
	
}
