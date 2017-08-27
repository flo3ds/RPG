package layout;

import java.io.IOException;

import org.lwjgl.input.Mouse;

import core.Stack;
import graphicEngine.BitmapFont;
import graphicEngine.SpriteBatch;
import graphicEngine.Texture;
import graphicEngine.TextureManager;
import graphicEngine.Util;
import items.Item;
import perso.Inventaire;
import perso.Personnage;

public class Layout_sac extends Layout {
	
	private Container[] con_inv;
	private Container[] con_bar;
	private int inv_size;
	


	public Layout_sac(Personnage perso) throws IOException {
		super("sac");
		inv_size = perso.inv.getSize();
		con_inv = new Container[inv_size];
		con_bar = new Container[9];
		
		for(int i=0; i<inv_size; i++)
			con_inv[i] = addContainer( perso.inv.getItem(i));
		
		for(int i=0; i<9; i++)
			con_bar[i] = addContainer( perso.getGUI().getItemBar()[i]);
		
	}

	public void draw(SpriteBatch batch, int x, int y, Inventaire inv) {
		x -= 320;
		y -= 250;
		draw_extends(batch, x, y);

		int xx = 0;

		for(int i=0; i<inv.getSize(); i++) {
			con_inv[i].draw(batch, (x+130)+(xx*42), (y+200) + ((i/9)*45));
			xx++;
			if(xx==9)
				xx=0;
		}
		
		for(int i=0; i<9; i++) 
			con_bar[i].draw(batch, (x+130) + (i*42), (y+350));
		
		draw_post_extends(batch);
	}
	
	

	
	

}
