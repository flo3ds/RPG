package monde.flore;

import core.Rand;
import gui.Pattern;
import gui.map.MultiTileset_I;
import gui.map.Tileset;
import monde.GestionId;
import monde.MondeObj;

public class Tronc extends MondeObj {

	public Feuillage_arbre fa;
	
	public Tronc(GestionId gid) {		
		super(gid, new Tileset("tronc", "tronc.png", 2, 96, 96, 32, 64));
		fa = new Feuillage_arbre(gid);
		this.setTexture(0);
		this.setParsemé(3);
	}


}
