package biome;

import gameData.GameData;
import graphicEngine.world.Chunk;

public class Biome {

	public static final GameData REGISTRY = GameData.getMain();

	

	public static void registerBiomes() {
		registerBiome(1, new Foret());
	}

	private static void registerBiome(int id, Biome object_) {
		REGISTRY.registerBiome(id, object_);
	}

	public void decorate(Chunk chunk) {
		
	}

}
