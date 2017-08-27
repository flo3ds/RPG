package world;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gameData.GameData;
import graphicEngine.world.World;
import graphicEngine.world.Worldable;
import objects.Object;

public class WorldProvider {

	private static WorldProvider worldProvider = new WorldProvider();

	private Map<Integer, Worldable> world = new HashMap<Integer, Worldable>();
	
	private WorldProvider(){
		
	}
	
	public static WorldProvider getInstance() {
		return worldProvider;
	}
	
	public Worldable openWorld(int id) {
		if(worldExist(id))
			return getWorld(id);
		else
			return createNewWorld(id);
	}
	
	public void addWorld(int id,Worldable world) {
		getInstance().world.put(id, world);
	}
	
	public Worldable getWorld(int id) {
		return getInstance().world.get(id);
	}
	
	public Worldable createNewWorld(int id) {
		File file = new File("world/"+id);
		file.mkdirs();
		World world = new World(""+id);
		addWorld(id, world);
		return world;
	}
	
	public Boolean worldExist(int id) {
		if(getInstance().world.containsKey(id))
			return true;
		return false;
	}
	
}
