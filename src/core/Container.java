package core;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import items.Item;

public class Container {

	protected short cases = 10;
	protected List<Stack> inventaire = new ArrayList<Stack>();
	private short libre = 0;

	public Container() {

	}

	// BUG SI IL Y A DES DEJA OBJ
	public void setCases(int i) {
		this.cases = (short) i;
	}

	public boolean putItem(Stack object) {
		if (this.libre == this.cases - 1)
			return false;

		if (object.getItem() instanceof Tool) {
			this.inventaire.add(object);
			return true;
		}

		for (int i = 0; i < this.cases; i++) {

			if (!this.inventaire.isEmpty())
				if (i < this.inventaire.size())
					if (object.getId().equals( this.inventaire.get(i).getId())) {
						((Stack)this.inventaire.get(i)).addNombre(object.getNombre());
						return true;
					}
		}

		try {
			this.inventaire.add(object.getClass().newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public List<Stack> getInventaire() {
		return this.inventaire;
	}



	public Stack getItem(int i) {
		if(i < this.inventaire.size())
			return this.inventaire.get(i);
		else
			return null;
	}

	public void removeItem(int index) {
		ListIterator<Stack> it = inventaire.listIterator();
		int i = 0;
		while (it.hasNext()) {
			it.next();
			if (i++ == index)
				it.remove();
		}
		this.libre--;
	}

	public int getSize() {
		return this.cases;
	}

	public void subItem(int i, int j) {
		this.inventaire.get(i).subNombre(j);
		if (this.inventaire.get(i).getNombre() <= 0)
			this.removeItem(i);
	}

	public Boolean haveItem(Inventable obj) {
		ListIterator<Stack> it = inventaire.listIterator();
		while (it.hasNext()) {
			if (it.next().getId().equals(obj.getId()))
				return true;
		}
		return false;
	}

	public Boolean haveItem(Stack obj, short nb) {
		ListIterator<Stack> it = inventaire.listIterator();
		while (it.hasNext()) {
			Stack objTest = it.next();
			if (objTest.getId().equals(obj.getId()))
				if (objTest.getNombre() >= (obj.getNombre()))
					return true;
		}
		return false;
	}

}
