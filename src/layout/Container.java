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

public class Container {

	private Texture vide;
	private BitmapFont 	font;
	
	private int x, y, w, h = 0;
	
	public Container () {
		try {
			vide =  new Texture(Util.getResource("res/item/vide.png"));
			font = new BitmapFont(Util.getResource("res/ptsans.fnt"), Util.getResource("res/ptsans_00.png"));
			w = h = vide.getWidth();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		public void draw(SpriteBatch batch, int x, int y, Stack stack) {
			this.x = x;
			this.y = y;
			if(stack != null && stack.getNombre() > 0){
				batch.draw(vide, x, y);
				batch.draw(TextureManager.getInstance().getTexture(stack.getTex()), x, y);
				font.drawText(batch, ""+stack.getNombre(), x, y);
			}else
				batch.draw(vide, x, y);
	
		}

		public boolean clicked(int x, int y) {
			//System.out.println(x+":"+y);
			if(x > this.x && x < this.x+w && y>this.y && y < this.y+h)
				return true;
			return false;
		}
}
