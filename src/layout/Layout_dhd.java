package layout;

import org.lwjgl.input.Mouse;

import core.Inventable;
import core.Stack;
import graphicEngine.SpriteBatch;
import init.Items;
import items.Item;
import perso.Inventaire;
import perso.Personnage;
import tileEntity.TileEntityChest;
import tileEntity.TileEntityDhd;
import tileEntity.TileEntityScirie;

public class Layout_dhd extends Layout {

	private BoutonTogle bou[] = new BoutonTogle[9];
	private boolean clicked = false;
	private TileEntityDhd portal;
	
	public Layout_dhd(TileEntityDhd tileEntity) {
		super("portal");
		portal = tileEntity;
		for(int i=0; i <9; i++)
			bou[i] = new BoutonTogle("res/layout/portal/bouton1");
		
	}
	



	public void draw(SpriteBatch batch, int x, int y, Inventaire inv) {
		x -= 320;
		y -= 240;
		draw_extends(batch, x, y);
		
		int xx = 0;
		
		for(int i=0; i <9; i++) {
			bou[i].draw(batch,  (x+110)+(xx*145), (y+100) + ((i/3)*100), portal.getCode().getCode(i));
			xx++;
			if(xx >= 3)
				xx = 0;
		}
	}
	
	
	public void click (int x, int y, Personnage perso) {
		if (Mouse.isButtonDown(0) && clicked   == false) {
			clicked = true;
			}else{
				
				if (Mouse.isButtonDown(0) == false && clicked == true){
					for(int i=0; i<bou.length; i++) {
						if( bou[i].clicked(x, y)) {
							portal.setReset(i);
						}
					}
					
					clicked = false;
					}
				}
	}

}
