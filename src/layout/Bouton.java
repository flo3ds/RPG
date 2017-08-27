package layout;

import java.io.IOException;

import core.Inventable;
import core.Stack;
import graphicEngine.BitmapFont;
import graphicEngine.SpriteBatch;
import graphicEngine.Texture;
import graphicEngine.TextureManager;
import graphicEngine.Util;
import items.Item;

public class Bouton {

	private Texture tex;
	
	private int x, y, w, h = 0;
	
	public Bouton (String path) {
		
		try {
			tex =  new Texture(Util.getResource(path));
			w = tex.getWidth();
			h = tex.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
		public void draw(SpriteBatch batch, int x, int y) {
			this.x = x;
			this.y = y;
			batch.draw(tex, x, y);
	
		}

		public boolean clicked(int x, int y) {
			//System.out.println(x+":"+y);
			if(x > this.x && x < this.x+w && y>this.y && y < this.y+h)
				return true;
			return false;
		}
}
