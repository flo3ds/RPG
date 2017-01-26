package core;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

abstract public class Container {

	protected short cases = 10;
	protected List<Object> inventaire = new ArrayList<Object>();
	private short libre = 0;

	public Container() {

	}

	// BUG SI IL Y A DES DEJA OBJ
	public void setCases(int i) {
		this.cases = (short) i;
	}

	public boolean putItem(Object object) {
		if (this.libre == this.cases - 1)
			return true;

		if (object instanceof Tool) {
			this.inventaire.add(object);
			return false;
		}

		for (int i = 0; i < this.cases; i++) {

			if (!this.inventaire.isEmpty())
				if (i < this.inventaire.size())
					if (((Inventable) object).getId().equals(((Inventable) this.inventaire.get(i)).getId())) {
						((Item) this.inventaire.get(i)).addNombre(((Item) object).getNombre());
						return false;
					}
		}

		this.inventaire.add(((Inventable) object).clone());
		return false;
	}

	public List<Object> getInventaire() {
		return this.inventaire;
	}

	public String liste() {
		String out = "";
		for (int i = 0; i < this.cases; i++) {
			out += (i + 1) + " | ";
			if (!this.inventaire.isEmpty())
				if (i < this.inventaire.size())
					out += this.inventaire.get(i).toString();
			out += "\n";
		}
		return out;
	}

	public Object getItem(int i) {
		return this.inventaire.get(i);
	}

	public void removeItem(int index) {
		ListIterator<Object> it = inventaire.listIterator();
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
		((Item) this.inventaire.get(i)).subNombre(j);
		if (((Item) this.inventaire.get(i)).getNombre() <= 0)
			this.removeItem(i);
	}

	public Boolean haveItem(Object obj) {
		ListIterator<Object> it = inventaire.listIterator();
		while (it.hasNext()) {
			if (((Inventable) it.next()).getId().equals(((Inventable) obj).getId()))
				return true;
		}
		return false;
	}

	public Boolean haveItem(Object obj, short nb) {
		ListIterator<Object> it = inventaire.listIterator();
		while (it.hasNext()) {
			Object objTest = it.next();
			if (((Inventable) objTest).getId().equals(((Inventable) obj).getId()))
				if (((Item) objTest).getNombre() >= (((Item) obj).getNombre()))
					return true;
		}
		return false;
	}

}
