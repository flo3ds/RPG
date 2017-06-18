package gui.map;

import java.awt.Color;
import java.awt.image.BufferedImage;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Tileset {

	private int firstgid;
	private String name;
	private int tilecount;
	private int tileheight = 32;
	private int tilewidth = 32;

	private String path;
	private String file;
	private int img_width;
	private int img_height;

	public Tileset(String name, String file, int tilecount, int tilewidth, int tileheight, int img_width,
			int img_height) {
		this.file = file;
		this.name = name;
		this.tilecount = tilecount;
		this.img_width = img_width;
		this.img_height = img_height;
		this.tilewidth = tilewidth;
		this.tileheight = tileheight;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

	public String getFile() {
		return file;
	}

	public void setFirstgid(int gid) {
		this.firstgid = gid;
	}

	public int getFirstgid() {
		return this.firstgid;
	}

	public int getCount() {
		return this.tilecount;
	}

	public Element getXml(Document document) {
		Element tileset = document.createElement("tileset");
		tileset.setAttribute("firstgid", firstgid + "");
		tileset.setAttribute("name", name);
		tileset.setAttribute("tilewidth", tilewidth + "");
		tileset.setAttribute("tileheight", tileheight + "");
		tileset.setAttribute("tilecount", tilecount + "");
		Element image = document.createElement("image");
		image.setAttribute("source", file);
		image.setAttribute("width", "" + img_width);
		image.setAttribute("height", "" + img_height);
		tileset.appendChild(image);
		return tileset;
	}
}
