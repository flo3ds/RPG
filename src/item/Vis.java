package item;


public class Vis extends Item{

	//Ici on met le nom de l'item.
	//Il doit etre unique !
	private String id = "vis";

	//Un constructeur pour créer une vis
	public Vis(){
		this.setId(this.id);
	}
	
	//Un constructeur pour créer plusieur vis
	public Vis(int nb){
		this.setNombre((short) nb);
		this.setId(this.id);
	}
}
