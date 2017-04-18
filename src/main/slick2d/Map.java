package main.slick2d;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {

	private TiledMap map;

	public void init() throws SlickException {
		this.map = new TiledMap("sprites/map.map");

	}

	public void render(Graphics g) {
		this.map.render(0, 0);
	}

	public TiledMap getTiledMap() {
		return this.map;
	}
}
