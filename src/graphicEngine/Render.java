package graphicEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphicEngine.world.Chunk;
import graphicEngine.world.World;
import graphicEngine.world.Worldable;
import objects.Object;
import perso.Personnage;

public class Render {
	
	public static void render(SpriteBatch batch, Player player, Worldable world, Personnage perso) {
		if (!world.waiting()){
			Vector2D[] mesh = world.getChunkLoader();
			final int mesh_lenght = mesh.length;
			Chunk[] chunks = new Chunk[mesh_lenght];
			for (int i = 0; i < mesh_lenght; i++) {
				chunks[i] = world.getChunk(world.getChunkLoader()[i]);
			}
			
			for (int i = 0; i < mesh_lenght; i++) {
				renderSol(chunks[i], world.getChunkLoader()[i], batch);
			}
			
			for (int i = 0; i < mesh_lenght; i++) {
				renderChunk(chunks[i], world.getChunkLoader()[i], batch, player, world, perso);
			}
		}
	}
	
	public static void renderSol(Chunk chunk, Vector2D pos, SpriteBatch batch) {
		int x = (int) (32 * Chunk.SIZE * pos.x);
		int y = (int) (32 * Chunk.SIZE * pos.y);
		// System.out.println("ch "+x+" | cy"+pos.y);
		
		for (int j = 0; j < Chunk.SIZE; j++) {
			for (int i = 0; i < Chunk.SIZE; i++) {
				batch.draw(TextureManager.getInstance().getTexture(chunk.getSolTextId(i, j)), 32 * i + x, 32 * j + y);
			}
		}
	}

	public static void renderChunk(Chunk chunk, Vector2D pos, SpriteBatch batch, Player player, Worldable world, Personnage perso) {
		int x = (int) (32 * Chunk.SIZE * pos.x);
		int y = (int) (32 * Chunk.SIZE * pos.y);
		// System.out.println("ch "+x+" | cy"+pos.y);
		
		int py = (int) (player.getPos().y / 32 % (Chunk.SIZE));
		int px = (int) (player.getPos().x / 32 % (Chunk.SIZE));
		if (player.getPos().y > 0) {

		} else {
			py *= -1;
			py = 31 - py;
		}
		
		// System.out.println("pos : " + world.getChunkPos(new
		// Vector2D(player.getPos().x, player.getPos().y)).y);
		// System.out.println("posp : " + py);
		Object[][] data = chunk.getGrid();
		Map<Short, List<Integer[]>> preObjs = new HashMap<Short, List<Integer[]>>();
		for (int j = 0; j < Chunk.SIZE; j++) {
			
			for (int i = Chunk.SIZE - 1; i >= 0; i--) {
				if (data[i][j] != null){
					Integer[] intt = new Integer[2];
					intt[0] = (32* i) + x + data[i][j].getDx();
					intt[1] = (32* j) + y + data[i][j].getDy();
					if(!preObjs.containsKey(data[i][j].getTexId()))
						preObjs.put(data[i][j].getTexId(), new ArrayList<Integer[]>());
					preObjs.get(data[i][j].getTexId()).add(intt);
				}
			}
		}
		
		
		for (short key   : preObjs.keySet()) {
			ITexture texture = TextureManager.getInstance().getTexture(key);
			int z = preObjs.get(key).size();
			for (int i=0; i<z; i++) 
			drawObject(texture, preObjs.get(key).get(i)[0] , preObjs.get(key).get(i)[1], batch);
		}   
		
		player.render(batch, perso);

		
		if (world.getChunkPos(new Vector2D(player.getPos().x, player.getPos().y)).compare(pos)){
			Object obj = chunk.getObject(px, py+1);
			if(obj != null)
				drawObject(TextureManager.getInstance().getTexture(obj.getTexId()), 32* px + x + obj.getDx() ,32* py + y + obj.getDy() , batch);
		}
	
					


	}
	

	private static void drawObject(ITexture obj, int x, int y, SpriteBatch batch) {
		//System.out.println(obj.getName()+" "+obj.getTexId());
		batch.draw(obj, x , y );	
	}
	
}
