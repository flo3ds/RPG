package monde;

import core.Rand;
import gui.Pattern;
import gui.map.Layer;
import gui.map.MultiTileset_I;
import gui.map.Tileset;
import monde.flore.Feuillage_arbre;

public class Colision extends MondeObj {


	public Colision(GestionId gid, int width, int height) {
		super(gid, new Tileset("col", "col.png", 1, 32, 32, 32, 32), width, height, Layer.Mode.none);
		this.setTexture(0);
		
	}

}
