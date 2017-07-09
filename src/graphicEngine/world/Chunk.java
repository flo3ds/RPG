package graphicEngine.world;

import java.io.Serializable;

import biome.Biome;
import graphicEngine.Vector2D;
import init.Objects;
import objects.Object;
import objects.Pierre;
import world.World;

public class Chunk implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9022178210602585020L;

	public static final int SIZE = 32;

	private Boolean define = false;

	private Object[][] grid;

	public Chunk(World world, Vector2D pos) {
		grid = new Object[SIZE][SIZE];
	}

	public Chunk(World world) {
		grid = new Object[SIZE][SIZE];
	}

	public void generate() {
		if (this.define == false) {
			for (int i = 0; i < SIZE; i++)
				for (int j = 0; j < SIZE; j++)
					grid[i][j] = null;
			this.define = true;
		}
	}

	public Object[][] getGrid() {
		return grid;
	}

	public Object getObject(int x, int y) {
		// System.out.println("obj : " + x + ":" + y);
		return grid[x][y];
	}

	public void placeAt(short x, short y, Object obj) {
		grid[x][y] = obj;
	}

	public boolean isEmpty(int x, int y) {
		if (grid[x][y] == null)
			return true;
		return false;
	}

	public Boolean isDefined() {
		return define;
	}

}
