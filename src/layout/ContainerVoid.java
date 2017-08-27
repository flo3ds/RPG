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

public class ContainerVoid {

	
	private int x, y;
	private int w, h;
	
	public ContainerVoid(){
		w = h = 32;
	}
	
		public void draw(SpriteBatch batch, int x, int y, Stack stack) {
			this.x = x;
			this.y = y;
			batch.draw(TextureManager.getInstance().getTexture(stack.getTexId()), x, y);
		}


		public boolean clicked(int x, int y) {
			//System.out.println(x+":"+y+" || "+this.x+":"+this.y+" -- "+w+":"+h);
			if(x > this.x && x < this.x+w && y>this.y && y < this.y+h)
				return true;
			return false;
		}
}
