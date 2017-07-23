package tool;

import java.io.IOException;

import org.lwjgl.Sys;

import core.Inventable;
import gameData.GameData;
import graphicEngine.Texture;
import graphicEngine.TextureManager;
import graphicEngine.Util;
import items.Bois;
import items.Fil;
import items.Item;
import items.PlancheBois;

public class Tool implements Inventable {

	public static final GameData REGISTRY = GameData.getMain();

	public String name;
	
	public Tool(String name) {

		this.name = name;
		try {
			if (!TextureManager.getInstance().exist(name)) {
				Texture tex;
				tex = new Texture(Util.getResource("res/tool/" + name + ".png"));
				TextureManager.getInstance().register(name, tex);
			}
		} catch (IOException e) {
			Sys.alert("Error", "Texture object not load : " + name);
			e.printStackTrace();
			System.exit(0);
		}
	}

	

	public String getId() {
		return name;
	}

	public String getTex() {
		return name;
	}



	
	

	public static void registerTools() {
		registerTool(1, new Axe());

	}

	private static void registerTool(int id, Tool item_) {
		REGISTRY.registerTool(id, item_);
	}

	
}
