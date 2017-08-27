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

public class Door extends Object {

	public Door() {
		super("door");
		addState("open");
	}
	
	public void click(Personnage perso, Worldable world, Vector2D pos_click) {
		if(perso.getGUI().getCurrentItem().compareID(Tools.PICK))
			breakObj(perso, world, pos_click);
		else
		if(this.getActivStateId() == 0){
			setState("open");
			setColisable(false);
		}else{
			setState(0);
			setColisable(true);
		}
	}
	
	
}
