package monde.flore;

import core.Rand;
import gui.Pattern;
import gui.map.Layer;
import gui.map.MultiTileset_I;
import gui.map.Tileset;
import monde.GestionId;
import monde.MondeObj;

public class Feuillage_arbre extends MondeObj {

	public Feuillage_arbre(GestionId gid) {		
		super(gid, new Tileset("feuillage", "arbre_fueillage.png", 2, 96, 96, 32, 64));
		this.getLayer().setMode(Layer.Mode.none);
		this.setTexture(0);
	}


}
