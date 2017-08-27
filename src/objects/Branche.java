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
import perso.Personnage;

public class Branche extends Object {

	public Branche() {
		super("branche");
		setColisable(false);
	}
	
	public void click(Personnage perso, Worldable world, Vector2D pos_click) {
		perso.inv.putItem(new Stack(Items.BRANCHE, 2));
		destroy(world, pos_click);
	}
	
}
