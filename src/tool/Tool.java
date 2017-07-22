package tool;

import gameData.GameData;
import items.Bois;
import items.Fil;
import items.Item;
import items.PlancheBois;

public class Tool {

	public static final GameData REGISTRY = GameData.getMain();

	public String name;
	
	public Tool(String name) {

		this.name = name;

	}

	

	public String getId() {
		return name;
	}

	public String getTex() {
		return name;
	}



	
	

	public static void registerTool() {
		registerTool(1, new Axe());

	}

	private static void registerTool(int id, Tool item_) {
		REGISTRY.registerTool(id, item_);
	}

	
}
