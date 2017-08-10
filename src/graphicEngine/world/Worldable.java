package graphicEngine.world;

import graphicEngine.Vector2D;
import objects.Object;

public interface Worldable {

	
	public Object getObject(int x, int y);
	public boolean waiting();
	public Vector2D getChunkPos (Vector2D pos);
	public void update (Vector2D pos, world.World world);
	public void updateTileEntity ();
	public void placeObject(int x, int y, Object obj);
	public void save();
	public Chunk getChunk(Vector2D vector2d);
	public Vector2D[] getChunkLoader();
	
	
}
