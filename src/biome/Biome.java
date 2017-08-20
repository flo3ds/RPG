package biome;

import gameData.GameData;
import graphicEngine.world.Chunk;

public class Biome {

	public static final GameData REGISTRY = GameData.getMain();
	private Sol_extends sol;
	
	public Biome(Sol_extends herbe) {
		this.sol = herbe;
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

	public Sol_extends getSol() {
		return sol;
	}
	
	public String getSolText() {
		return sol.getTex();
	}

	public void setSol(Sol sol) {
		this.sol = sol;
	}

}
