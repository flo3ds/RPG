package gui.map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

public interface Map_I {

	public void render(Graphics g);
	public TiledMap getTiledMap();
}
