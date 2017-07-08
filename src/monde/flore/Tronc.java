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

	public Tronc(GestionId gid, int width, int height) {
		super(gid, new Tileset("tronc", "tronc.png", 2, 96, 96, 96 * 2, 96), width, height);
		this.setTexture(0);
		fa = new Feuillage_arbre(gid, width, height);
		this.setParsemé(3);
		setColisable();

	}

}
