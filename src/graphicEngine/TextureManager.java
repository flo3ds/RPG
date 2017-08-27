package graphicEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextureManager {

	private static TextureManager instance = new TextureManager();

	//private Map<Short, ITexture> mapId = new HashMap<Short, ITexture>();
	private Map<String, Short> mapName = new HashMap<String, Short>();
	
	private List<ITexture> tex = new ArrayList<ITexture>();
	
	private short id = 0;

	private TextureManager() {
		
	}

	public static TextureManager getInstance() {
		return instance;
	}

	public short register(String str, ITexture tex) {
		if (getInstance().mapName.containsKey(str)) {
			return getInstance().mapName.get(str);
		}else{
			//System.out.println("name : "+str+" | "+id);
			getInstance().tex.add(id, tex);
			getInstance().mapName.put(str, id);
			id++;
			return id;
		}

	}

	public ITexture getTexture(short id) {
		return getInstance().tex.get(id);
	}
	
	
	public short getIdByName(String str) {
		return getInstance().mapName.get(str);
	}

	public boolean exist(String str) {
		if (getInstance().mapName.containsKey(str))
			return true;
		return false;
	}

}
