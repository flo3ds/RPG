package biome;

import graphicEngine.world.Chunk;
import init.Objects;
import init.Sols;

public class Foret extends Biome {

	public Foret() {

		
		
	}
	
	public void decorate (Chunk chunk) {
		Generator.populate(chunk, Objects.PIERRE, 16);
		Generator.populate(chunk, Objects.ARBRE, 32);
		Generator.populate(chunk, Objects.HERBE, 20);
		Generator.populate(chunk, Objects.FLOWER, 6);
		Generator.populate(chunk, Objects.BRANCHE, 6);
	}
}
