package gui.layoutable;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface Layoutable {

	public void render(Graphics g) throws SlickException;

	public void update();

	public Boolean clicked();

}
