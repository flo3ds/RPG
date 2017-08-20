package graphicEngine.world;

import java.io.Serializable;

import biome.Biome;
import biome.Sol;
import biome.Sol_extends;
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
	
	private Sol_extends sol;

	public Chunk(World world, Vector2D pos) {
		grid = new Object[SIZE][SIZE];
	}

	public Chunk(World world) {
		grid = new Object[SIZE][SIZE];
	}
	
	public Chunk() {
		grid = new Object[SIZE][SIZE];
	}

	public void generate(Biome biome) {
		
		for(int x=0; x < SIZE; x++) {
			for(int y=0; y < SIZE; y++) {
				grid[x][y] = null;
			}
		}
		
		biome.decorate(this);
		setSol(biome.getSol());
		
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


	
	public String getSolText() {
		return sol.getTex();
	}

	public void setSol(Sol_extends sol) {
		this.sol = sol;
	}

}
