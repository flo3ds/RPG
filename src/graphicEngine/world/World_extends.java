package graphicEngine.world;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import graphicEngine.Vector2D;
import init.Biomes;
import objects.Object;
import tileEntity.ITileEntityProvider;
import tileEntity.TileEntity;

public class World_extends {

public static final short RANGELOADING = 1;
	
	private Vector2D[] mesh;
	public boolean wait = false;
	public String name;

	private Map<Integer, Chunk> chunkMap = new HashMap<Integer, Chunk>();
	private Map<Integer, TileEntity> tileEntity = new HashMap<Integer, TileEntity>();
	
	public World_extends(String name, Vector2D pos, world.World world) {
		mesh = new Vector2D[(RANGELOADING*2+1)*(RANGELOADING*2+1)];
		this.name = name;
		File f = new File(name + "world.ser");
		if(f.exists() && !f.isDirectory()) {
			FileInputStream fin = null;
			try {
				fin = new FileInputStream(name +"world.ser");
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
	
	public Vector2D[] getChunkLoader() {
		return mesh;
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
		int ch_x = (int) ((x) / Chunk.SIZE);
		int ch_y = (int) ((y) / Chunk.SIZE);
		
		short obj_x = (short) ((x) % Chunk.SIZE);
		short obj_y = (short) ((y) % Chunk.SIZE);
		
	
		if(y < 0) {
			obj_y += 32;
			ch_y--;
		}
		if(x < 0) {
			obj_x += 32;
			ch_x--;
		}
		//================================================
		Chunk chunk = getChunk(new Vector2D(ch_x, ch_y));
		System.out.print("chunk = "+ch_x+" : "+ch_y+"\n");
		System.out.print("obj = "+obj_x+" : "+obj_y+"\n");
		return chunk.getObject(obj_x, obj_y);
	}
		

	
	public void placeObject(int x, int y, Object obj){
		//TRaitement de la souris (negatif, world, chunk)
		//================================================
		System.out.print("setclick = "+x+" : "+y+"\n");

		int ch_x = (int) ((x) / Chunk.SIZE);
		int ch_y = (int) ((y) / Chunk.SIZE);
		
		short obj_x = (short) ((x) % Chunk.SIZE);
		short obj_y = (short) ((y) % Chunk.SIZE);
		
	
		if(y < 0) {
			obj_y += 32;
			ch_y--;
		}
		if(x < 0) {
			obj_x += 32;
			ch_x--;
		}
	
		//================================================
		if(obj instanceof ITileEntityProvider){
			addTileEntity(new Vector2D(x, y).hashCode(), obj.createNewTileEntity());
		}
		Chunk chunk = getChunk(new Vector2D(ch_x, ch_y));
		chunk.placeAt((short)obj_x, (short)obj_y, obj);
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
	
	public Chunk getChunk(Vector2D vector) {
		return chunkMap.get(vector.hashCode());
	}
	
	public void destroyAt(Vector2D pos) {
		int ch_x = (int) ((pos.x) / Chunk.SIZE);
		int ch_y = (int) ((pos.y) / Chunk.SIZE);
		
		short obj_x = (short) ((pos.x) % Chunk.SIZE);
		short obj_y = (short) ((pos.y) % Chunk.SIZE);
		
	
		if(pos.y < 0) {
			obj_y += 32;
			ch_y--;
		}
		if(pos.x < 0) {
			obj_x += 32;
			ch_x--;
		}
	
		System.out.print("chunk = "+ch_x+" : "+ch_y+"\n");
		System.out.print("obj = "+obj_x+" : "+obj_y+"\n\n");

		
		getChunk(new Vector2D(ch_x, ch_y)).placeAt(obj_x, obj_y, null);
	}

	public void save() {
		
		
		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream(name +"world.ser");
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
