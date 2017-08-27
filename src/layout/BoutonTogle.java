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

public class BoutonTogle {

	private Texture tex, tex_clicked;
	
	private Boolean clicked = false;
	
	private int x, y, w, h = 0;
	
	
	public BoutonTogle (String path) {
		
		try {
			tex =  new Texture(Util.getResource(path+".png"));
			tex_clicked =  new Texture(Util.getResource(path+"_clicked.png"));
			w = tex.getWidth();
			h = tex.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
		public void draw(SpriteBatch batch, int x, int y) {
			this.x = x;
			this.y = y;
			if(clicked)
				batch.draw(tex_clicked, x, y);
			else
				batch.draw(tex, x, y);
	
		}
		
		public void draw(SpriteBatch batch, int x, int y, Boolean i) {
			this.x = x;
			this.y = y;
			if(i)
				batch.draw(tex_clicked, x, y);
			else
				batch.draw(tex, x, y);
	
		}

		public boolean clicked(int x, int y) {
			//System.out.println(x+":"+y+" || "+this.x+":"+this.y+" -- "+w+":"+h);
			if(x > this.x && x < this.x+w && y>this.y && y < this.y+h){
				if(clicked)
					clicked = false;
				else
					clicked = true;
				return true;
			}
			return false;
		}
}
