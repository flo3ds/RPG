package core;

import java.io.Serializable;

import items.Item;
import objects.Object;

public class Stack implements Inventable {
	
	public static final int MAXSIZE = 64;
	
	Inventable item;
	int nb = 1;
	
	public Stack (Inventable obj, int nb) {
		this.item = obj;
		this.nb = nb;
	}
	
	public Stack () {
		
	}
	

	public void reset() {
		item = null;
		nb = 0;
	}
	
	public int getNombre() {
		return nb;
	}

	public void subNombre(int x) {
		nb -= x;
		if(nb < 1)
			item = null;
	}
	
	public String getTex() {
		return item.getTex();
	}
	
	public void addNombre(int nb) {
		this.nb += nb;
	}
	
	public Inventable getItem () {
		return item;
	}
	
	public void setItem (Inventable item, int nb) {
		this.item = item;
		this.nb = nb;
	}
	
	public void addItem (Inventable item, int nb) {
		this.item = item;
		this.nb += nb;
	}
	
	public String getId() {
		if (item != null)
			return item.getId();
		else
			return "";
	}
}
