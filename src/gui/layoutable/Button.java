package gui.layoutable;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Button implements Layoutable {

	private String texte = "";
	private int id;
	private int x, y, width, height = 0;
	private Input input;
	private Boolean leftDown = false;
	private Color color_bg_normal = new Color(120, 120, 120, 255);
	private Color color_bg_down = new Color(170, 170, 170, 255);
	private Color color_text_normal = new Color(250, 250, 250, 255);
	private Color color_text_over = new Color(255, 106, 0, 255);

	private Color color_bg;
	private Color color_text;

	public Button(int id, Input input, int x, int y, int width, int height, String str) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.texte = str;
		this.input = input;
		color_bg = color_bg_normal;
		color_text = color_text_normal;
	}

	public int getId() {
		return id;
	}

	public void update(int xd, int yd) {
		int x = input.getMouseX() + xd;
		int y = input.getMouseY() + yd;
		color_bg = color_bg_normal;
		if (x < this.x + width && x > this.x && y < this.y + height && y > this.y) {
			color_text = color_text_over;
			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
				color_bg = color_bg_down;
		} else
			color_text = color_text_normal;

	}

	public String getTexte() {
		return this.texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public Boolean clicked(int xd, int yd) {
		if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
			this.leftDown = true;
		} else {
			if (this.leftDown) {
				int x = input.getMouseX() + xd;
				int y = input.getMouseY() + yd;
				if (x < this.x + width && x > this.x && y < this.y + height && y > this.y)
					return true;
				this.leftDown = false;
			}
		}
		return false;
	}

	public void render(Graphics g) throws SlickException {
		g.setColor(color_bg);
		g.fillRect(x, y, width, height);
		g.setColor(color_text);
		g.drawString(texte, x, y);
	}

}
