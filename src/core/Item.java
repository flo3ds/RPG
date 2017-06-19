package core;

abstract public class Item extends Inventable {

	private short nombre = 1;

	public int getNombre() {
		return this.nombre;
	}

	public void setNombre(short nombre) {
		this.nombre = nombre;
	}

	public void subNombre(int i) {
		this.nombre -= i;
	}

	public String toString() {
		return this.getId() + " x" + this.nombre;
	}

	public void addNombre(int i) {
		this.nombre += i;
	}
}
