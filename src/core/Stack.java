package core;

import java.io.Serializable;

import items.Item;
import objects.Object;
import tool.Tool;

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
	

	public Stack(Stack stack) {
		this.item = stack.getItem();
		this.nb = stack.getNombre();
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
	
	public short getTexId() {
		return item.getTexId();
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

	public void setStack(Stack stack) {
		this.item = stack.getItem();
		this.nb = stack.getNombre();
	}

	public void setNombre(int i) {
		this.nb = i;
	}
	
	public Boolean empty() {
		if(item == null || nb <=0)
			return true;
		return false;
	}

	public boolean compareID(Stack stack) {
		if(stack.getId().equals(getId()))
			return true;
		else
			return false;
	}
	
	public boolean compareID(Inventable stack) {
		if(stack.getId().equals(getId()))
			return true;
		else
			return false;
	}
}
