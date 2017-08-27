package layout;

import core.Inventable;
import core.Stack;
import graphicEngine.SpriteBatch;
import init.Items;
import items.Burnable;
import items.Item;
import perso.Inventaire;
import perso.Personnage;
import tileEntity.TileEntityChest;
import tileEntity.TileEntityFurnace;
import tileEntity.TileEntityScirie;

public class Layout_furnace extends Layout {

	private Container con_in;
	private Container con_out;
	private Container con_fuel;
	private Container[] con_inv;
	private Container[] con_bar;

	private TileEntityFurnace furnace;
	private int inv_size;


	public Layout_furnace(TileEntityFurnace tileEntity, Personnage perso) {
		super("scirie");
		furnace = tileEntity;
		
		inv_size = perso.inv.getSize();
		con_in = addContainer(furnace.getStack_in());
		con_out = addContainer(furnace.getStack_out());
		con_fuel = addContainer(furnace.getStack_fuel());
		con_fuel.setVideTexture("res/item/vide_burnable.png");
		con_inv = new Container[inv_size];
		con_bar = new Container[9];
		
		for(int i=0; i<inv_size; i++)
			con_inv[i] = new Container();for(int i=0; i<inv_size; i++)
				con_inv[i] = addContainer( perso.inv.getItem(i));
			
			for(int i=0; i<9; i++)
				con_bar[i] = addContainer(perso.getGUI().getItemBar()[i]);
	}
	



	public void draw(SpriteBatch batch, int x, int y, Inventaire inv) {
		x -= 320;
		y -= 240;
		draw_extends(batch, x, y);
		
		int xx=0;
		for(int i=0; i<inv_size; i++) {
			con_inv[i].draw(batch, (x+130)+(xx*42), (y+215) + ((i/9)*45));
			xx++;
			if(xx==9)
				xx=0;
		}
		
		for(int i=0; i<9; i++) 
			con_bar[i].draw(batch, (x+130) + (i*42), (y+360));
		
		
		con_in.draw(batch, x+140, y+150);
		con_out.draw(batch, x+500, y+150);
		con_fuel.draw(batch, x+95, y+150);
		
		draw_post_extends(batch);
		
	}

	

}
