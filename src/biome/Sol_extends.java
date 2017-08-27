package biome;

import java.io.IOException;
import java.io.Serializable;

import org.lwjgl.Sys;

import gameData.GameData;
import graphicEngine.Texture;
import graphicEngine.TextureManager;
import graphicEngine.Util;
import graphicEngine.world.Chunk;

public class Sol_extends implements Serializable{
	
	private String name;
	private short texId;
	
	public Sol_extends(String name) {
		this.setName(name);
		try {
			if (!TextureManager.getInstance().exist("sol_"+name)) {
				Texture tex;
				tex = new Texture(Util.getResource("res/sol/" + name + ".png"));
				texId = TextureManager.getInstance().register("sol_"+name, tex);
			}
			else
				System.out.println(" error doublon add text : " + "sol_"+name);
		} catch (IOException e) {
			Sys.alert("Error", "Texture object not load : " + name);
			e.printStackTrace();
			System.exit(0);
		}
		
		texId = TextureManager.getInstance().getIdByName("sol_"+name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTex() {
		return "sol_"+name;
	}

	public static final GameData REGISTRY = GameData.getMain();

	public static void registerSols() {
		registerSol(0, new Sol("herbe"));
		registerSol(1, new Sol("base"));
		registerSol(2, new Sol("pierre"));
	}

	private static void registerSol(int id, Sol_extends object_) {
		REGISTRY.registerSol(id, object_);
	}

	public short getTextId() {
		return texId;
	}
	
	

	
}
