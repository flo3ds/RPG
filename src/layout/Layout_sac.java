package layout;

import java.io.IOException;

import graphicEngine.BitmapFont;
import graphicEngine.SpriteBatch;
import graphicEngine.Texture;
import graphicEngine.TextureManager;
import graphicEngine.Util;
import items.Item;
import perso.Inventaire;

public class Layout_sac extends Layout {
	
	private Texture vide = new Texture(Util.getResource("res/item/vide.png"));
	private BitmapFont 	font = new BitmapFont(Util.getResource("res/ptsans.fnt"), Util.getResource("res/ptsans_00.png"));


	public Layout_sac() throws IOException {
		super("sac");
	}

	public void draw(SpriteBatch batch, int x, int y, Inventaire inv) {
		x -= 320;
		y -= 240;
		draw_extends(batch, x, y);
		font.drawText(batch, "Inventaire", (x+300), (y+50));


		for(int i=0; i<inv.getSize(); i++) {
			
			batch.draw(vide, (x+100)+(i*42), (y+200));
			if (i < inv.getInventaire().size()){
				batch.draw(TextureManager.getInstance().getTexture(inv.getItem(i).getTex()), (x+100)+(i*42), (y+200));
				font.drawText(batch, ""+inv.getItem(i).getNombre(), (x+100)+(i*26), (y+240));
			}
			
			
		}
		
	}

}
