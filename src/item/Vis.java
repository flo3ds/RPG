package item;


public class Vis extends Item{

	//Ici on met le nom de l'item.
	//Il doit etre unique !
	private String id = "vis";

	//Un constructeur pour cr�er une vis
	public Vis(){
		this.setId(this.id);
	}
	
	//Un constructeur pour cr�er plusieur vis
	public Vis(int nb){
		this.setNombre((short) nb);
		this.setId(this.id);
	}
}
