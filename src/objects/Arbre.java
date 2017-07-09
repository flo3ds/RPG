package objects;

import java.io.IOException;

import org.lwjgl.Sys;

import graphicEngine.Texture;
import graphicEngine.TextureManager;
import graphicEngine.TextureRegion;
import graphicEngine.Util;
import perso.Personnage;

public class Arbre extends Object {

	public Arbre() {
		super("arbre");
		
		addState("coupé");
		
		setDecalageSpriteX(-32);
		setDecalageSpriteY(-96);
		
	}
	
	public void click(Personnage perso) {
		setState("coupé");
	}
	
	
}
