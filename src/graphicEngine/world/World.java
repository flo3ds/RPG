package graphicEngine.world;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import objects.Object;
import tileEntity.ITileEntityProvider;
import tileEntity.TileEntity;

import java.util.Map;

import graphicEngine.Vector2D;
import init.Biomes;

public class World {
	
	public static final short RANGELOADING = 1;
	
	public Vector2D[] mesh;
	public boolean wait = false;

	private Map<Integer, Chunk> chunkMap = new HashMap<Integer, Chunk>();
	private Map<Integer, TileEntity> tileEntity = new HashMap<Integer, TileEntity>();
	
	public World(Vector2D pos, world.World world) {
		mesh = new Vector2D[(RANGELOADING*2+1)*(RANGELOADING*2+1)];
		File f = new File("world.ser");
		if(f.exists() && !f.isDirectory()) {
			FileInputStream fin = null;
			try {
				fin = new FileInputStream("world.ser");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ObjectInputStream ios = null;
			try {
				ios = new ObjectInputStream(fin);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				chunkMap = (HashMap<Integer, Chunk>)ios.readObject();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void addTileEntity(int key, TileEntity tileEntity) {
		//System.out.println("entity = " + key); 

		this.tileEntity.put(key, tileEntity);
	}
	
	public void update (Vector2D pos, world.World world) {
		updateChunk(pos, world);
	}
	
	public void updateTileEntity () {
		for (int key   : tileEntity.keySet()) {
		     tileEntity.get(key).update();  //get() is less efficient 
		}                     
		
	}
	
	public TileEntity getTileEntity(Vector2D pos) {
		//System.out.println("getTile = " + pos.x +":"+pos.y+":"+pos.hashCode());
		return tileEntity.get(pos.hashCode());
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
		

	
	public void placeObject(int x, int y, Object obj){
		//TRaitement de la souris (negatif, world, chunk)
		//================================================
		int xc = (int) (x / (Chunk.SIZE*32));
		int yc = (int) (y / (Chunk.SIZE*32));
		int xo = x/32%(Chunk.SIZE);
		int yo = y/32%(Chunk.SIZE);
		int yp = y / 32;
		int xp = x / 32;
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
		if(obj instanceof ITileEntityProvider){
			addTileEntity(new Vector2D(xp, yp).hashCode(), obj.createNewTileEntity());
		}
		Chunk chunk = getChunk(new Vector2D(xc, yc).hashCode());
		chunk.placeAt((short)xo, (short)yo, obj);
	}
	
	
	private void updateChunk (Vector2D pos, world.World world) {
		Thread t = new Thread(){
			public void run(){
		Vector2D posChunk = getChunkPos(pos);
		wait = true;
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
		wait = false;
		}
		};
		t.start();
	}
	
	public boolean waiting(){
		return wait;
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

	public void save() {
		
		
		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream("world.ser");
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(fout);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			oos.writeObject(this.chunkMap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
		
	}

	
}
