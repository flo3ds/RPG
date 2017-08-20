package graphicEngine.base;

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
import graphicEngine.world.Chunk;
import graphicEngine.world.World_extends;
import graphicEngine.world.Worldable;
import init.Biomes;
import objects.Object;
import tileEntity.ITileEntityProvider;
import tileEntity.TileEntity;
import world.World;

public class BaseGUI  implements Worldable {

public  short salle_number = 5;
	
	private Vector2D[] mesh;
	public boolean wait = false;
	public String name;

	private Map<Integer, Chunk> chunkMap = new HashMap<Integer, Chunk>();
	private Map<Integer, TileEntity> tileEntity = new HashMap<Integer, TileEntity>();
	
	public BaseGUI(String name, Vector2D pos) {
		mesh = new Vector2D[salle_number];
		this.name = name;
		File f = new File("world/base/"+name + ".ser");
		if(f.exists() && !f.isDirectory()) {
			FileInputStream fin = null;
			FileInputStream fin2 = null;
			try {
				fin = new FileInputStream("world/base/"+name +".ser");
				fin2 = new FileInputStream("world/base/"+name +"_entity.ser");
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
			initChunkBase();
		
	}
	
	public void initChunkBase() {
		int index = 0;
		mesh[index++] = new Vector2D(0, -1);
		mesh[index++] = new Vector2D(0, 0);
		mesh[index++] = new Vector2D(0, 1);
		mesh[index++] = new Vector2D(-1, 0);
		mesh[index++] = new Vector2D(1, 0);
		for (int i=0; i<mesh.length; i++){
			if(!chunkMap.containsKey(mesh[i].hashCode())){
				chunkMap.put(mesh[i].hashCode(), new Chunk());
				chunkMap.get(mesh[i].hashCode()).generate(Biomes.BASE);
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
		int[][] renderArray = new int[Chunk.SIZE * (salle_number*2+1)][Chunk.SIZE * (salle_number*2+1)];
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
			fout = new FileOutputStream("world/base/"+name +".ser");
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
			fout2 = new FileOutputStream("world/base/"+name +"_entity.ser");
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
	
}
