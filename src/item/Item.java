package item;

abstract public class Item {

	private short nombre = 1;
	private String id;
	
	public int getNombre(){
		return this.nombre;
	}
	
	public void setNombre(short nombre){
		this.nombre = nombre;
	}
	
	public String getId()
	{
		return this.id;
	}
	
	public String setId(String id)
	{
		return this.id = id;
	}

	public void subNombre(int i) {
		this.nombre -= i;
	}
	
	public String toString() {
		return this.id + " x" +this.nombre;
	}

	public void addNombre(int i) {
		this.nombre += i;
	}
}
