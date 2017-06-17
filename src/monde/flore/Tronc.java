package monde.flore;

import core.Rand;
import gui.Pattern;
import gui.map.Layer;
import gui.map.MultiTileset_I;
import gui.map.Tileset;
import monde.GestionId;
import monde.MondeObj;

public class Tronc extends MondeObj {

	public Feuillage_arbre fa;

	public Tronc(GestionId gid) {
		super(gid, new Tileset("tronc", "tronc.png", 2, 96, 96, 96 * 2, 96));
		this.setTexture(0);
		fa = new Feuillage_arbre(gid);
		this.setParsemé(3);

	}

}
