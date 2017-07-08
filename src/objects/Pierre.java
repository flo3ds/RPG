package objects;

import java.io.IOException;

import org.lwjgl.Sys;

import graphicEngine.Texture;
import graphicEngine.TextureManager;
import graphicEngine.TextureRegion;
import graphicEngine.Util;

public class Pierre extends Object {

	public Pierre() {
		super("pierre");
		
		try {
			Texture tex;
			tex = new Texture(Util.getResource("res/"+getName()+".png"), Texture.NEAREST);
			TextureRegion tile = new TextureRegion(tex, 0, 32, 32, 32);
			TextureManager.getInstance().register(getName(), tile);
		} catch (IOException e) {
			Sys.alert("Error", "Texture object not load : " + getName());
			e.printStackTrace();
			System.exit(0);
		}
		
		
	}
	
	
}
