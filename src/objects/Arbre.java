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
		
		try {
			Texture tex;
			tex = new Texture(Util.getResource("res/"+getName()+".png"));
			TextureManager.getInstance().register(getName(), tex);
			tex = new Texture(Util.getResource("res/"+getName()+"_1.png"));
			TextureManager.getInstance().register(getName()+"_1", tex);
		} catch (IOException e) {
			Sys.alert("Error", "Texture object not load : " + getName());
			e.printStackTrace();
			System.exit(0);
		}
		
		setDx(-32);
		setDy(-96);
		
	}
	
	public void click(Personnage perso) {
		setState(1);
	}
	
	
}
