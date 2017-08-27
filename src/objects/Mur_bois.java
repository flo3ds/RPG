package objects;

import java.io.IOException;

import org.lwjgl.Sys;

import graphicEngine.Texture;
import graphicEngine.TextureManager;
import graphicEngine.TextureRegion;
import graphicEngine.Util;
import graphicEngine.Vector2D;
import graphicEngine.world.Worldable;
import init.Tools;
import perso.Personnage;
import tileEntity.TileEntityGenerator;

public class Mur_bois extends Object {

	public Mur_bois() {
		super("mur_bois");
	}
	
	public void click(Personnage perso, Worldable world, Vector2D pos_click) {
		if(perso.getGUI().getCurrentItem().compareID(Tools.PICK))
			breakObj(perso, world, pos_click);
		}
	
}
