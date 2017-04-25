package gui.map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map implements Map_I{

	private TiledMap map;

	public void init(String path) {
		try {
			this.map = new TiledMap(path);
		} catch (SlickException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	public void render(Graphics g) {
		this.map.render(0, 0);
	}

	public TiledMap getTiledMap() {
		return this.map;
	}
}
