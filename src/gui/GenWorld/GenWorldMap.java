package gui.GenWorld;

import java.util.ArrayList;
import java.util.Iterator;

import gui.map.Layer;
import gui.map.MultiTileset_I;
import gui.map.Tileset;
import monde.Monde;
import monde.MondeObj;

public class GenWorldMap {

	public static void genWorldMap(Monde monde){
		XMLMap xml = new XMLMap("sprites/mapTest2.map");
		
		int w = 20;
		int h = 20;
		
		process(xml, monde.sol, w, h);
		process(xml, monde.pierre, w, h);
		process(xml, monde.arbreGui, w, h);
		monde.arbreGui.fa.getLayer().setDataFeuillage(monde.arbreGui.getLayer().getData());
		process(xml, monde.arbreGui.fa, w, h);
		
		xml.write();
	}
	
	private static void process(XMLMap xml, MondeObj obj, int w, int h){
		
		if(obj instanceof MultiTileset_I){
			Layer layer = obj.getLayer();
			layer.setHW(w, h);
			xml.setLayer(layer.getXml(xml.getDoc()));
			Iterator<Tileset> it = ((ArrayList<Tileset>)layer.getArrayTileset()).iterator();
			while(it.hasNext())
				xml.setTileset(it.next().getXml(xml.getDoc()));
		}else{
		
		Layer layer = obj.getLayer();
		layer.setHW(w, h);
		xml.setLayer(layer.getXml(xml.getDoc()));
		xml.setTileset(((Tileset)obj.getTileset()).getXml(xml.getDoc()));
	}}
	
}
