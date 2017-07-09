package biome;

import java.util.Random;

import org.lwjgl.Sys;

import objects.Object;
import graphicEngine.world.Chunk;

public class Generator {

	public static void populate(Chunk chunk, Object obj, int nombre) {
		short x, y;
		for (int i = 0; i < nombre; i++) {
			do {
				x = (short) (Math.random() * (Chunk.SIZE - 1 - 0 + 1));
				y = (short) (Math.random() * (Chunk.SIZE - 1 - 0 + 1));
			} while (!chunk.isEmpty(x, y));
			try {
				chunk.placeAt(x, y, obj.getClass().newInstance());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				Sys.alert("Error", "generation biome");
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

}
