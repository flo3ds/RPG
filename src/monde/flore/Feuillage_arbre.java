package monde.flore;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import core.Rand;
import gui.Pattern;
import gui.map.Layer;
import gui.map.MultiTileset_I;
import gui.map.Tileset;
import monde.GestionId;
import monde.MondeObj;

public class Feuillage_arbre extends MondeObj {

	public Tronc tronc;

	public Feuillage_arbre(GestionId gid) {
		super(gid, new Tileset("test", "test.png", 4, 96, 96, 96 * 2, 96 * 2));
		// tronc = new Tronc(gid);
		this.setTexture(2);
		this.getLayer().setMode(Layer.Mode.none);

		this.setColor(new Color(Rand.entier(0, 255), Rand.entier(0, 255), Rand.entier(0, 255)));

	}

}
