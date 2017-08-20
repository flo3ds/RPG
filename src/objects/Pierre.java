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

public class Pierre extends Object {

	public Pierre() {
		super("pierre");
		
	}
	
	public void click(Personnage perso, Worldable world, Vector2D pos_click) {
		
			perso.inv.putItem(new Stack(Items.CAILLOU, 2));
		
	}
	
}
