package gui.map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class MapBase implements Map_I {

	private TiledMap map;

	public void init() throws SlickException {
		this.map = new TiledMap("sprites/base/mapBase.tmx");

	}

	public void render(Graphics g) {
		int nb = this.map.getLayerCount();
		for (int i = 0; i < nb; i++)
			this.map.render(0, 0, i);

	}

	public TiledMap getTiledMap() {
		return this.map;
	}
}
