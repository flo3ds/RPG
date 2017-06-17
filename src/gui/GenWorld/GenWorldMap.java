package gui.GenWorld;

import java.util.ArrayList;
import java.util.Iterator;

import gui.map.Layer;
import gui.map.MultiTileset_I;
import gui.map.Tileset;
import monde.Monde;
import monde.MondeObj;

public class GenWorldMap {

	public static void genWorldMap(Monde monde) {
		String name = "test";
		// créér le dossier
		XMLMap xml = new XMLMap("world/" + name + "/test.map");

		int w = 20;
		int h = 20;

		process(xml, name, monde.sol, w, h);
		process(xml, name, monde.pierre, w, h);
		monde.tronc.getLayer().getXml(xml.getDoc());
		monde.tronc.fa.getLayer().setDataUp(monde.tronc.getLayer().getData());
		monde.tronc.getLayer().setMode(Layer.Mode.none);
		process(xml, name, monde.tronc, w, h);
		process(xml, name, monde.tronc.fa, w, h);

		xml.write();
	}

	private static void process(XMLMap xml, String name, MondeObj obj, int w, int h) {

		if (obj instanceof MultiTileset_I) {
			Layer layer = obj.getLayer();
			layer.setHW(w, h);
			xml.setLayer(layer.getXml(xml.getDoc()));
			Iterator<Tileset> it = ((ArrayList<Tileset>) layer.getArrayTileset()).iterator();
			while (it.hasNext())
				xml.setTileset(it.next().getXml(xml.getDoc()));
		} else {

			Layer layer = obj.getLayer();
			layer.setHW(w, h);
			xml.setLayer(layer.getXml(xml.getDoc()));
			obj.pngProcess("world/" + name + "/");
			xml.setTileset(((Tileset) obj.getTileset()).getXml(xml.getDoc()));
		}
	}

}
