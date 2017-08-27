package items;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.Sys;

import core.Inventable;
import gameData.GameData;
import graphicEngine.ITexture;
import graphicEngine.Texture;
import graphicEngine.TextureManager;
import graphicEngine.Util;
import perso.Personnage;

public class Item implements Inventable {

	public static final GameData REGISTRY = GameData.getMain();

	public String name;
	private short texId;
	
	public Item(String name) {

		this.name = name;

		try {
			if (!TextureManager.getInstance().exist(name)) {
				Texture tex;
				tex = new Texture(Util.getResource("res/item/" + name + ".png"));
				texId = TextureManager.getInstance().register(name, tex);
			}
		} catch (IOException e) {
			Sys.alert("Error", "Texture object not load : " + name);
			e.printStackTrace();
			System.exit(0);
		}
		texId =  TextureManager.getInstance().getIdByName(name);
	}

	

	public String getId() {
		return name;
	}

	public String getTex() {
		return name;
	}

	public short getTexId() {
		return texId;
	}
	
	public static void registerItems() {
		registerItem(1, new Bois());
		registerItem(2, new Fil());
		registerItem(3, new PlancheBois());
		registerItem(4, new Caillou());
		registerItem(5, new Herbe());
		registerItem(6, new Flower());
		registerItem(7, new Branche());
	}

	private static void registerItem(int id, Item item_) {
		REGISTRY.registerItem(id, item_);
	}

	
}
