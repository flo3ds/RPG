package gameData;

import java.util.HashMap;
import java.util.Map;

import biome.Biome;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import objects.Object;

public class GameData {

	private static GameData gameData = new GameData();
	
	private Map<Integer, Object> objects = new HashMap<Integer, Object>();
	private Map<Integer, Biome> biomes = new HashMap<Integer, Biome>();
	
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
	
	
	
}
