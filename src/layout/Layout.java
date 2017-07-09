package layout;

import java.io.IOException;

import gameData.GameData;
import graphicEngine.SpriteBatch;
import graphicEngine.Texture;
import objects.Arbre;
import objects.Chest;
import objects.Object;
import objects.Pierre;

public class Layout {
	
	public static final GameData REGISTRY = GameData.getMain();
	
	Texture tex;

	public Layout (Texture tex)
	{
		this.tex = tex;
	}
	
	public void draw (SpriteBatch batch, int x, int y) {
	}
	
	public void draw_extends(SpriteBatch batch, float x, float y) {
		batch.draw(tex, x, y);
	}
	
	 public static void registerLayouts()
	    {
	        try {
	        	
				registerLayout(1, new Layout_sac());
				
			} catch (IOException e) {
				e.printStackTrace();
			}
	      
	    }
	
	
	
	
	 private static void registerLayout(int id, Layout layout_)
	    {
	        REGISTRY.registerLayout(id, layout_);
	    }
}
