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

public class Mine extends Object {

	public Mine() {
		super("mine");
		addState("open");
		
	}
	
	public void click(Personnage perso, Worldable world, Vector2D pos_click) {
			setState("open");
	}
	
}
