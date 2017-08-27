package core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import items.Item;
import tool.Tool;

public class Container {

	protected short cases = 10;
	protected Stack inventaire[] = new Stack[cases];

	public Container() {
		init();
	}
	
	public Container(int size) {
		this.cases = (short) size;
		inventaire = new Stack[size];
		init();
	}

	public void init() {
		for(int i=0; i < cases; i++)
			inventaire[i] = new Stack();
	}
	
	// BUG SI IL Y A DES DEJA OBJ
	public void setCases(int i) {
		this.cases = (short) i;
		inventaire = new Stack[i];
		init();
	}
	
	public int getFreeCase() {
		for (int i=0; i <cases; i++)
			if(inventaire[i] == null)
				return i;
			else if(inventaire[i].getId() == "")
				return i;
		return -1;
	}

	public boolean putItem(Stack object) {
		int free = getFreeCase();
		if(free == -1)
			return false;
		
		if (object.getItem() instanceof Tool) {
			inventaire[free].setStack(object);
			return true;
		}

		for (int i = 0; i < this.cases; i++) {
			if(inventaire[i] != null)
					if (object.getId().equals(inventaire[i].getId())) {
						inventaire[i].addNombre(object.getNombre());
						return true;
					}
		}

		inventaire[free].setStack(new Stack(object.getItem(), object.getNombre()));
		return true;
	}


	public void putItem(Stack stack, int i) {
		inventaire[i] = stack;
	}

	public Stack[] getItemTab() {
		return inventaire;
	}

	public Stack getItem(int i) {
		return inventaire[i];
	}
	
	public void removeItem(int index) {
		inventaire[index] = null;
	}

	public int getSize() {
		return cases;
	}

	public void subItem(int i, int j) {
		inventaire[i].subNombre(j);
		if (inventaire[i].getNombre() <= 0)
			this.removeItem(i);
	}

	public Boolean haveItem(Stack obj) {
		for (int i=0; i <cases; i++)
			if(inventaire[i].getId().equals(obj.getId()))
				if(inventaire[i].getNombre() >= obj.getNombre())
					return true;
		return false;
	}
	
	public int findItem(Stack obj) {
		for (int i=0; i <cases; i++)
			if(inventaire[i].getId().equals(obj.getId()))
				if(inventaire[i].getNombre() >= obj.getNombre())
					return i;
		return -1;
	}

/*
	public Stack getFirstStack() {
		if(inventaire[i])
			return null;
		if(this.inventaire.get(0) != null && this.inventaire.get(0).getNombre() > 0)
			return this.inventaire.get(0);
		else
			this.removeItem(0);
		return getFirstStack();	
	}
*/
}
