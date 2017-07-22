package layout;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

import gameData.GameData;
import graphicEngine.SpriteBatch;
import graphicEngine.Texture;
import graphicEngine.TextureManager;
import graphicEngine.Util;
import objects.Arbre;
import objects.Chest;
import objects.Object;
import objects.Pierre;
import perso.Inventaire;
import perso.Personnage;

public class Layout {
	
	/**
	 * 
	 */

	public static final GameData REGISTRY = GameData.getMain();
	private String name;
	
	public Layout (String name)
	{
		this.name = name;
		if( ! TextureManager.getInstance().exist("layout-"+name)){
			try {
				TextureManager.getInstance().register("layout-"+name, new Texture(Util.getResource("res/layout/"+name+".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void draw (SpriteBatch batch, int x, int y) {
	}
	
	public void draw (SpriteBatch batch, int x, int y, Inventaire inv) {
	}
	
	public void update () {
		
	}
	
	public void click (int x, int y, Personnage perso) {
		
	}
	
	public void draw_extends(SpriteBatch batch, float x, float y) {
		//System.out.println("layout : "+ name);
			
			
		batch.draw(TextureManager.getInstance().getTexture("layout-"+name), x, y);
	}
	
	
	
	
	
	
	
}
