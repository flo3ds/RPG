package gui.layout;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;

import action.Actionable;
import gui.layoutable.Button;
import gui.layoutable.ItemDock;
import gui.layoutable.Layoutable;

public class Layout {

	private String texte = "";
	private String header = "";
	private int x, y, width, height = 0;
	private ArrayList<Object> obj = new ArrayList<Object>();
	private Input input;
	private int nbLine, nbCol = 0;
	private Boolean show = false;
	private Object action;
	private int headerHeight = 80;

	public Layout(Object action, Input input, int x, int y, int width, int height, String str) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.texte = str;
		this.input = input;
		this.action = action;
	}

	public void update() {
		for (int i = 0; i < obj.size(); i++)
			((Layoutable) obj.get(i)).update();

		for (int i = 0; i < obj.size(); i++)
			if (((Layoutable) obj.get(i)).clicked())
				this.redrawLine(((Button) obj.get(i)).getId());
	}

	public void close() {
		show = false;
	}

	public void open(Object action) {
		obj.clear();
		nbLine = 0;
		this.action = action;
		StructRet buffer = ((Actionable) action).init();
		header = buffer.getHeader();
		Iterator<String> text = buffer.getText().iterator();
		int id[] = buffer.getId();
		int i = 0;
		while (text.hasNext())
			addButton(text.next(), id[i++]);
		show = true;

	}

	public void redrawLine(int idAction) {
		obj.clear();
		nbLine = 0;
		StructRet buffer = ((Actionable) action).action(idAction);
		header = buffer.getHeader();
		Iterator<String> text = buffer.getText().iterator();
		int id[] = buffer.getId();
		int i = 0;
		while (text.hasNext())
			addButton(text.next(), id[i++]);
		show = true;
	}

	public void drawLine(StructRet in) {
		obj.clear();
		nbLine = 0;
		Iterator<String> text = in.getText().iterator();
		header = in.getHeader();
		int id[] = in.getId();
		int i = 0;
		while (text.hasNext())
			addButton(text.next(), id[i++]);
		show = true;
	}

	public void drawSquare(StructRet in) {
		obj.clear();
		nbLine = 0;
		Iterator<String> text = in.getText().iterator();
		header = in.getHeader();
		int id[] = in.getId();
		int i = 0;
		while (text.hasNext())
			addButton(text.next(), id[i++]);
		show = true;
	}

	public void addButton(String text, int id) {
		int width = 300;
		int height = 30;
		int padding = 5;

		obj.add((Object) new Button(id, input, x + padding, headerHeight + y + padding + ((height + padding) * nbLine),
				width, height, text));
		nbLine++;

	}

	public void addItemDock(String text) {
		int size = 70;
		int padding = 5;

		if ((size + padding) * nbCol > width - x - size) {
			nbLine++;
			nbCol = 0;
		}
		obj.add((Object) new ItemDock(input, x + padding + ((size + padding) * nbCol),
				headerHeight + y + padding + ((size + padding) * nbLine), size, size, text));
		nbCol++;

	}

	public void render(GUIContext arg0, Graphics g) throws SlickException {
		int headerH = 60;
		int padding = 5;
		int fontSize = 20;
		if (show) {
			g.setColor(new Color(60, 60, 60, 200));
			g.fillRect(x, y, width, height);
			g.setColor(new Color(200, 200, 200, 250));
			g.drawString(header, x + padding, y + padding + (headerH / 2) - fontSize);
			for (int i = 0; i < obj.size(); i++)
				((Layoutable) obj.get(i)).render(g);
		}
	}

	public Boolean isShow() {
		return show;
	}

}
