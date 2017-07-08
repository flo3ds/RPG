package graphicEngine;

import java.util.HashMap;
import java.util.Map;

public class TextureManager {

	private static TextureManager instance = new TextureManager();
	
	private Map<String, ITexture> map = new HashMap<String, ITexture>();
	
	private TextureManager() {
		
	}
	
	public static TextureManager getInstance() {
		return instance;
	}
	
	public void register(String str, ITexture tex) {
		getInstance().map.put(str, tex);
	}
	
	public ITexture getTexture(String str) {
		return getInstance().map.get(str);
	}
	
	
	
}
