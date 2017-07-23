package layout;

import core.Stack;
import graphicEngine.SpriteBatch;
import perso.Inventaire;
import perso.Personnage;
import tileEntity.TileEntityChest;
import tileEntity.TileEntityCrafting_station;

public class Layout_crafting extends Layout {
	
	private Container[] con_chest;
	private Container[] con_inv;
	private TileEntityCrafting_station tileEntity;
	private int chest_size;
	private int inv_size;
	
	public Layout_crafting(TileEntityCrafting_station tileEntity, Inventaire inv) {
		super("crafting");
		this.tileEntity = tileEntity;
		chest_size = this.tileEntity.getSize();
		inv_size = inv.getSize();
		con_chest = new Container[chest_size];
		con_inv = new Container[inv_size];
		
		for(int i=0; i<inv_size; i++)
			con_inv[i] = new Container();
		
		for(int i=0; i<chest_size; i++)
			con_chest[i] = new Container();
	}

	public void draw(SpriteBatch batch, int x, int y, Inventaire inv) {
		x -= 320;
		y -= 240;
		draw_extends(batch, x, y);
		
		for(int i=0; i<inv_size; i++) {
			con_inv[i].draw(batch, (x+100)+(i*42), (y+150), inv.getItem(i));
		}
		for(int i=0; i<chest_size; i++) {
			con_chest[i].draw(batch, (x+100)+(i*42), (y+280), tileEntity.getItem(i));
		}
	}
	
	public void update () {
		
	}
	
	public void click (int x, int y, Personnage perso) {
		for(int i=0; i<inv_size; i++) {
			if( con_inv[i].clicked(x, y)) {
				Stack item = perso.inv.getItem(i);
				if (item != null) {
					tileEntity.putItem(item);
					perso.inv.removeItem(i);
			}}
		}
		for(int i=0; i<chest_size; i++) {
			if( con_chest[i].clicked(x, y)) {
				Stack item = tileEntity.getItem(i);
				if (item != null) {
					perso.inv.putItem(item);
					tileEntity.removeItem(i);
			}}
		}
	}

}
