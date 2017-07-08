package graphicEngine.world;

import java.util.HashMap;
import objects.Object;
import java.util.Map;

import graphicEngine.Vector2D;
import init.Biomes;

public class World {
	
	public static final short RANGELOADING = 1;
	
	public Vector2D[] mesh;

	private Map<Integer, Chunk> chunkMap = new HashMap<Integer, Chunk>();
	
	public World(Vector2D pos, world.World world) {
		mesh = new Vector2D[(RANGELOADING*2+1)*(RANGELOADING*2+1)];
	}
	
	public void update (Vector2D pos, world.World world) {
		updateChunk(pos, world);
	}
	
	public Object getObject(int x, int y){
		//TRaitement de la souris (negatif, world, chunk)
		//================================================
		int xc = (int) (x / (Chunk.SIZE*32));
		int yc = (int) (y / (Chunk.SIZE*32));
		int xo = x/32%(Chunk.SIZE);
		int yo = y/32%(Chunk.SIZE);
		if(x < 0){
			xc--;
			x = x * -1;
			xo = 31 - x/32%(Chunk.SIZE);
		}
		if(y > 0){
			
			
			yc *= -1;
		}else{
			yc--;
			yo *= -1;
			yo = 31 - (-1*y)/32%(Chunk.SIZE);
			
		}
		//================================================
		Chunk chunk = getChunk(new Vector2D(xc, yc).hashCode());
		return chunk.getObject(xo, yo);
	}
		
	
	
	private void updateChunk (Vector2D pos, world.World world) {
		Vector2D posChunk = getChunkPos(pos);
		int index = 0;
		for (int i=1; i<RANGELOADING+1; i++) {
			mesh[index++] = new Vector2D(posChunk.x -i, posChunk.y -i);
			mesh[index++] = new Vector2D(posChunk.x, posChunk.y -i);
			mesh[index++] = new Vector2D(posChunk.x +i, posChunk.y -i);
			mesh[index++] = new Vector2D(posChunk.x -i, posChunk.y);
			mesh[index++] = new Vector2D(posChunk.x   , posChunk.y);
			mesh[index++] = new Vector2D(posChunk.x +i, posChunk.y);
			mesh[index++] = new Vector2D(posChunk.x -i, posChunk.y +i);
			mesh[index++] = new Vector2D(posChunk.x , posChunk.y +i);
			mesh[index++] = new Vector2D(posChunk.x +i, posChunk.y +i);	
		}
		for (int i=0; i<mesh.length; i++){
			if(!chunkMap.containsKey(mesh[i].hashCode())){
				chunkMap.put(mesh[i].hashCode(), new Chunk(world));
				Biomes.FORET.decorate(chunkMap.get(mesh[i].hashCode()));
			}
		}
	}
	
	public Vector2D getChunkPos (Vector2D pos){
		
		int x = (int) (pos.x / (Chunk.SIZE*32));
		int y = (int) (pos.y / (Chunk.SIZE*32));
		
		if(pos.x < 0)
			x--;
		if(pos.y < 0)
			y--;
		
		//System.out.println("chunk : "+x+";"+y);
		
		return new Vector2D(x, y);
	}
	
	
	public int[][] getRenderArray() {
		int[][] renderArray = new int[Chunk.SIZE * (RANGELOADING*2+1)][Chunk.SIZE * (RANGELOADING*2+1)];
		return renderArray;
	}
	
	public Chunk getChunk(int vector) {
		return chunkMap.get(vector);
	}
	
}
