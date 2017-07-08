package biome;

import graphicEngine.world.Chunk;
import init.Objects;

public class Foret extends Biome {

	public Foret() {
		
		
		
	}
	
	public void decorate (Chunk chunk) {
		Generator.populate(chunk, Objects.PIERRE, 32);
		Generator.populate(chunk, Objects.ARBRE, 32);
	}
}