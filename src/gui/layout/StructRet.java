package gui.layout;

import java.util.ArrayList;

public class StructRet {

	private int[] id = new int[100];
	private int nbId = 0;
	private ArrayList<String> text = new ArrayList<String>();
	private String header = "";

	public void add(String text, int id) {
		this.text.add(text);
		this.id[nbId++] = id;
	}

	public ArrayList<String> getText() {
		return text;
	}

	public void setText(ArrayList<String> text) {
		this.text = text;
	}

	public int[] getId() {
		return id;
	}

	public int getnbId() {
		return nbId;
	}

	public void setId(int[] id) {
		this.id = id;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

}
