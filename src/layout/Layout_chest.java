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
import tileEntity.TileEntityChest;

public class Layout_chest extends Layout {
	
	private Container[] con_inv;
	private Container[] con_bar;
	private Container[] con_chest;
	private int inv_size;
	private int chest_size;
	private TileEntityChest chest;


	public Layout_chest(TileEntityChest tileEntityChest, Personnage perso) {
		super("chest");
		chest = tileEntityChest;
		inv_size = perso.inv.getSize();
		chest_size = chest.getSize();
		
		con_inv = new Container[inv_size];
		con_chest = new Container[chest_size];
		con_bar = new Container[9];
		
		for(int i=0; i<chest_size; i++)
			con_chest[i] = addContainer( chest.getItem(i));
		
		for(int i=0; i<inv_size; i++)
			con_inv[i] = addContainer( perso.inv.getItem(i));
		
		for(int i=0; i<9; i++)
			con_bar[i] = addContainer(perso.getGUI().getItemBar()[i]);
		
	}

	public void draw(SpriteBatch batch, int x, int y, Inventaire inv) {
		x -= 320;
		y -= 250;
		draw_extends(batch, x, y);

		int xx = 0;

		for(int i=0; i<chest_size; i++) {
			con_chest[i].draw(batch, (x+130)+(xx*42), (y+100) + ((i/9)*45));
			xx++;
			if(xx==9)
				xx=0;
		}
		
		xx=0;
		for(int i=0; i<inv_size; i++) {
			con_inv[i].draw(batch, (x+130)+(xx*42), (y+205) + ((i/9)*45));
			xx++;
			if(xx==9)
				xx=0;
		}
		
		for(int i=0; i<9; i++) 
			con_bar[i].draw(batch, (x+130) + (i*42), (y+360));
		
		draw_post_extends(batch);
	}
	
	

	
	

}
