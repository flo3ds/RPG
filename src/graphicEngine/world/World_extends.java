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

import biome.Sol;
import biome.Sol_extends;
import graphicEngine.Vector2D;
import init.Biomes;
import init.Objects;
import init.Sols;
import objects.Object;
import tileEntity.ITileEntityProvider;
import tileEntity.TileEntity;

public class World_extends {

public static final short RANGELOADING = 1;
	
	private Vector2D[] mesh;
	public boolean wait = true;
	public String name;
	private boolean gen = true;


	private Map<Integer, Chunk> chunkMap = new HashMap<Integer, Chunk>();
	private Map<Integer, TileEntity> tileEntity = new HashMap<Integer, TileEntity>();
	
	public World_extends(String name) {
		mesh = new Vector2D[(RANGELOADING*2+1)*(RANGELOADING*2+1)];
		this.name = name;
		File f = new File("world/world/"+name + ".ser");
		if(f.exists() && !f.isDirectory()) {
			FileInputStream fin = null;
			FileInputStream fin2 = null;
			try {
				fin = new FileInputStream("world/world/"+name +".ser");
				fin2 = new FileInputStream("world/world/"+name +"_entity.ser");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ObjectInputStream ios = null;
			ObjectInputStream ios2 = null;
			try {
				ios = new ObjectInputStream(fin);
				ios2 = new ObjectInputStream(fin2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				chunkMap = (HashMap<Integer, Chunk>)ios.readObject();
				tileEntity = (HashMap<Integer, TileEntity>)ios2.readObject();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		wait = false;
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
	
	public void postInit(Vector2D pos, world.World world) {
		updateChunkWithoutThread(pos, world);
		placePortal();
	}
	
	public void updateTileEntity () {
		for (int key   : tileEntity.keySet()) {
		     tileEntity.get(key).update(this);  //get() is less efficient 
		}                     
		
	}
	
	public TileEntity getTileEntity(Vector2D pos) {
		//System.out.println("getTile = " + pos.x +":"+pos.y+":"+pos.hashCode());
		return tileEntity.get(pos.hashCode());
	}
	
	public void removeTileEntity(Vector2D pos) {
		//System.out.println("getTile = " + pos.x +":"+pos.y+":"+pos.hashCode());
		tileEntity.remove(pos.hashCode());
	}
	
	public TileEntity getTileEntity(int x, int y) {
		//System.out.println("getTile = " + pos.x +":"+pos.y+":"+pos.hashCode());
		return tileEntity.get(new Vector2D(x, y).hashCode());
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
		//System.out.print("chunk = "+ch_x+" : "+ch_y+"\n");
		//System.out.print("obj = "+obj_x+" : "+obj_y+"\n");
		return chunk.getObject(obj_x, obj_y);
	}
	
	
	public Object getObjectCol(int x, int y){
		//TRaitement de la souris (negatif, world, chunk)
		//================================================
		if(y < 0)
			y -= 32;
		if(x < 0)
			x -= 32;
		x /= 32;
		y /= 32;
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
		//System.out.print("chunk = "+ch_x+" : "+ch_y+"\n");
		//System.out.print("obj = "+obj_x+" : "+obj_y+"\n");
		return chunk.getObject(obj_x, obj_y);
	}
	
	


	
	public void placeObject(int x, int y, Object obj){
		//TRaitement de la souris (negatif, world, chunk)
		//================================================
		//System.out.print("setclick = "+x+" : "+y+"\n");

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
			addTileEntity(new Vector2D(x, y).hashCode(), obj.createNewTileEntity(x, y));
		}
		Chunk chunk = getChunk(new Vector2D(ch_x, ch_y));
		chunk.placeAt((short)obj_x, (short)obj_y, obj);
	}
	
	public void placeSol(int x, int y, Sol_extends pierre){
		//TRaitement de la souris (negatif, world, chunk)
		//================================================
		//System.out.print("setclick = "+x+" : "+y+"\n");

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
		chunk.placeSol((short)obj_x, (short)obj_y, pierre);
	}
	
	
	
	private void updateChunk (Vector2D pos, world.World world) {
		Thread t = new Thread(){

			public void run(){
				
				wait = true;
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
		//if (gen){
		for (int i=0; i<mesh.length; i++){
			if(!chunkMap.containsKey(mesh[i].hashCode())){
				System.out.println("gen " + mesh[i].x+":"+mesh[i].y);
				chunkMap.put(mesh[i].hashCode(), new Chunk(world));
				chunkMap.get(mesh[i].hashCode()).generate(Biomes.FORET);
			}
		}
		//}
		wait = false;
		gen = false;
		}
		};
		t.start();
	}
	
	private void updateChunkWithoutThread (Vector2D pos, world.World world) {
		
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
				chunkMap.get(mesh[i].hashCode()).generate(Biomes.FORET);
			}
		}
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
	
		//System.out.print("chunk = "+ch_x+" : "+ch_y+"\n");
		//System.out.print("obj = "+obj_x+" : "+obj_y+"\n\n");

		
		getChunk(new Vector2D(ch_x, ch_y)).placeAt(obj_x, obj_y, null);
		tileEntity.remove(pos);
	}

	public void save() {
		
		
		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream("world/world/"+name +".ser");
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
		
		
		FileOutputStream fout2 = null;
		try {
			fout2 = new FileOutputStream("world/world/"+name +"_entity.ser");
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		ObjectOutputStream oos2 = null;
		try {
			oos2 = new ObjectOutputStream(fout2);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			oos2.writeObject(this.tileEntity);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.exit(0);
		
	}
	
	public void placePortal(){
		
		for (int x=-1; x<=1; x++)
			for (int y=-2; y<=1; y++) {
				destroyAt(new Vector2D(x, y));
				placeSol(x, y, Sols.PIERRE);
			}
		
		placeObject(0, 0, Objects.PORTAL);
		placeObject(0, -1, Objects.DHD);
	}
}
