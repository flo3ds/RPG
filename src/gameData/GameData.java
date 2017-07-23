package gameData;

import java.util.HashMap;
import java.util.Map;

import biome.Biome;
import items.Item;
import layout.Layout;
import objects.Object;
import tool.Tool;

public class GameData {

	private static GameData gameData = new GameData();
	
	private Map<Integer, Object> objects = new HashMap<Integer, Object>();
	private Map<Integer, Biome> biomes = new HashMap<Integer, Biome>();
	private Map<Integer, Layout> layout = new HashMap<Integer, Layout>();
	private Map<Integer, Item> item = new HashMap<Integer, Item>();
	private Map<Integer, Tool> tool = new HashMap<Integer, Tool>();

	private GameData(){
		
	}
	
	public static GameData getMain() {
		return gameData;
	}
	
	
	
	public void registerObject(int id, Object obj){
		 getMain().objects.put(id, obj);
	}
	
	public Object getObject (int id) {
		return getMain().objects.get(id);
	}
	
	public void registerBiome(int id, Biome biome){
		 getMain().biomes.put(id, biome);
	}
	
	public Biome getBiome (int id) {
		return getMain().biomes.get(id);
	}
	
	public void registerLayout(int id, Layout layout){
		 getMain().layout.put(id, layout);
	}
	
	public Layout getLayout (int id) {
		return getMain().layout.get(id);
	}
	
	public void registerItem(int id, Item layout){
		 getMain().item.put(id, layout);
	}
	
	public Item getItem (int id) {
		return getMain().item.get(id);
	}
	
	public void registerTool(int id, Tool layout){
		 getMain().tool.put(id, layout);
	}
	
	public Tool getTool (int id) {
		return getMain().tool.get(id);
	}

	
}
