package gui.GenWorld;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

import gui.map.Layer;
import gui.map.MultiTileset_I;
import gui.map.Tileset;
import monde.Monde;
import monde.MondeObj;

public class GenWorldMap {

	public static void genWorldMap(Monde monde, int width, int height) {
		String name = "test";
		
		File dir = new File ("world/" + name);
		dir.mkdirs();
		
		// créér le dossier
		XMLMap xml = new XMLMap("world/" + name + "/test.tmx", width, height);
		
		//=================================
		// COLLISION
		Layer col = monde.col.getLayer();
		processCol(monde.pierre, col);
		//processCol(monde.tronc, col);
		initCol(col, xml, monde, name);
		//===================================================================
		
		process(xml, name, monde.sol, col);
		process(xml, name, monde.pierre, col);
		monde.tronc.getLayer().getXml(xml.getDoc());
		monde.tronc.fa.getLayer().setDataUp(monde.tronc.getLayer().getData());
		monde.tronc.getLayer().setMode(Layer.Mode.none);
		process(xml, name, monde.tronc, col);
		monde.tronc.fa.getLayer().defData();
		process(xml, name, monde.tronc.fa, col);
		
		
		xml.write();
	}

	private static void process(XMLMap xml, String name, MondeObj obj, Layer col) {

		if (obj instanceof MultiTileset_I) {
			Layer layer = obj.getLayer();
			xml.setLayer(layer.getXml(xml.getDoc()));
			Iterator<Tileset> it = ((ArrayList<Tileset>) layer.getArrayTileset()).iterator();
			while (it.hasNext())
				xml.setTileset(it.next().getXml(xml.getDoc()));
		} else {

			Layer layer = obj.getLayer();
			xml.setLayer(layer.getXml(xml.getDoc()));
			obj.pngProcess("world/" + name + "/");
			xml.setTileset(((Tileset) obj.getTileset()).getXml(xml.getDoc()));
		}
	}
	
	private static void processCol(MondeObj obj, Layer col) {
		if (obj.colisable())
			col.mergerLayer(obj.getCol());
		}

	private static void initCol(Layer col, XMLMap xml, Monde monde, String name) {
		col.reabiliteLayer();
		col.setMode(Layer.Mode.none);
		col.defData();
		xml.setLayer(col.getXml(xml.getDoc()));
		monde.col.pngProcess("world/" + name + "/");
		xml.setTileset(((Tileset) col.getTileset()).getXml(xml.getDoc()));
	}
	
}
