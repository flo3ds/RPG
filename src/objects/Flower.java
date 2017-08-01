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

public class Flower extends Object {

	public Flower() {
		super("flower");
		setColisable(false);
		
	}
	
	public void click(Personnage perso, World world, Vector2D pos_click) {
		
			perso.inv.putItem(new Stack(Items.FLOWER, 2));
		
	}
	
}
