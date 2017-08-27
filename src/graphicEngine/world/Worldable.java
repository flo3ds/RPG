package graphicEngine.world;

import graphicEngine.Vector2D;
import objects.Object;
import tileEntity.TileEntity;
import tileEntity.TileEntityChest;
import world.World;

public interface Worldable {

	
	public Object getObject(int x, int y);
	public Object getObjectCol(int x, int y);
	public boolean waiting();
	public Vector2D getChunkPos (Vector2D pos);
	public void update (Vector2D pos, world.World world);
	public void updateTileEntity ();
	public void placeObject(int x, int y, Object obj);
	public void save();
	public Chunk getChunk(Vector2D vector2d);
	public Vector2D[] getChunkLoader();
	public TileEntity getTileEntity(Vector2D pos_click);
	public void destroyAt(Vector2D click);
	public void removeTileEntity(Vector2D pos_click);
	public void postInit(Vector2D vector2d, World world);
	
	
}
