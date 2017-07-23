package layout;

import core.Inventable;
import core.Stack;
import graphicEngine.SpriteBatch;
import init.Items;
import items.Item;
import perso.Inventaire;
import perso.Personnage;
import tileEntity.TileEntityChest;
import tileEntity.TileEntityScirie;

public class Layout_scirie extends Layout {

	private Container con_in;
	private Container con_out;
	private Container[] con_inv;
	private TileEntityScirie tileEntity;
	private int inv_size;


	public Layout_scirie(TileEntityScirie tileEntity, Inventaire inv) {
		super("scirie");
		this.tileEntity = tileEntity;
		
		inv_size = inv.getSize();
		con_in = new Container();
		con_out = new Container();
		con_inv = new Container[inv_size];
		
		for(int i=0; i<inv_size; i++)
			con_inv[i] = new Container();
	}
	



	public void draw(SpriteBatch batch, int x, int y, Inventaire inv) {
		x -= 320;
		y -= 240;
		draw_extends(batch, x, y);
		
		for(int i=0; i<inv_size; i++) {
			con_inv[i].draw(batch, (x+100)+(i*42), (y+280), inv.getItem(i));
		}
		
		con_in.draw(batch, x+100, y+150, tileEntity.getStack_in());
		con_out.draw(batch, x+500, y+150, tileEntity.getStack_out());
		
	}
	
	public void update () {
		
	}
	
	public void click (int x, int y, Personnage perso) {
		for(int i=0; i<inv_size; i++) {
			if( con_inv[i].clicked(x, y)) {
				Inventable item = perso.inv.getItem(i);
				if (item != null) {
					if(tileEntity.addStack_in(item))
					perso.inv.removeItem(i);
			}}
		}
		if( con_out.clicked(x, y)) {
			perso.inv.putItem(tileEntity.getStack_out());
			tileEntity.removeStackOut();
		}
		else if( con_in.clicked(x, y)) {
			perso.inv.putItem(tileEntity.getStack_in());
			tileEntity.removeStackIn();
		}
		
	}

}
