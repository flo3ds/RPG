package biome;

import gameData.GameData;
import graphicEngine.world.Chunk;

public class Biome {

	public static final GameData REGISTRY = GameData.getMain();
	private Sol_extends[][] sol = new Sol_extends[Chunk.SIZE][Chunk.SIZE];
	
	public Biome(Sol_extends herbe) {
		for (int x=0; x<Chunk.SIZE; x++)
			for (int y=0; y<Chunk.SIZE; y++)
		this.sol[x][y] = herbe;
	}

	public static void registerBiomes() {
		registerBiome(0, new Foret());
		registerBiome(1, new Base());
	}

	private static void registerBiome(int id, Biome object_) {
		REGISTRY.registerBiome(id, object_);
	}

	public void decorate(Chunk chunk) {

	}

	public Sol_extends[][] getSol() {
		return sol;
	}
	
	public String getSolText(int x, int y) {
		return sol[x][y].getTex();
	}

	public void setSol(int x, int y,Sol sol) {
		this.sol[x][y] = sol;
	}

}
