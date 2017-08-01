package biome;

import graphicEngine.world.Chunk;
import init.Objects;

public class Foret extends Biome {

	public Foret() {
		
		
		
	}
	
	public void decorate (Chunk chunk) {
		Generator.populate(chunk, Objects.PIERRE, 16);
		Generator.populate(chunk, Objects.ARBRE, 24);
		Generator.populate(chunk, Objects.HERBE, 24);
	}
}
