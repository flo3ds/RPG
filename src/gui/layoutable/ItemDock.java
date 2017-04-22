package gui.layoutable;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class ItemDock implements Layoutable {

	private String texte = "";
	private int x, y, width, height = 0;
	private Input input;
	private Boolean leftDown = false;
	private Color color_bg_normal = new Color(120, 120, 120, 255);
	private Color color_bg_down = new Color(170, 170, 170, 255);
	private Color color_text_normal = new Color(250, 250, 250, 255);
	private Color color_text_over = new Color(255, 106, 0, 255);

	private Color color_bg;
	private Color color_text;

	public ItemDock(Input input, int x, int y, int width, int height, String str) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.texte = parseText(str);
		this.input = input;
		color_bg = color_bg_normal;
		color_text = color_text_normal;
	}

	public String getTexte() {
		return this.texte;
	}

	public void setTexte(String texte) {
		this.texte = parseText(texte);
	}

	public String parseText(String str) {
		String out = "";
		String buffer[] = str.split(" ");
		for (int i = 0; i < buffer.length; i++)
			out += buffer[i] + "\n";
		return out;
	}

	@Override
	public Boolean clicked() {
		return false;

	}

	public void render(Graphics g) throws SlickException {
		g.setColor(color_bg);
		g.fillRect(x, y, width, height);
		g.setColor(color_text);
		g.drawString(texte, x, y);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
