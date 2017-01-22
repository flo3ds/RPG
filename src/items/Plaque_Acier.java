package items;


public class Plaque_Acier extends Item{

	//Ici on met le nom de l'item.
	//Il doit etre unique !
	private String id = "Plaque d'acier";

	//Un constructeur pour cr�er 1 seul
	public Plaque_Acier(){
		this.setId(this.id);
	}
	
	//Un constructeur pour cr�er plusieur
	public Plaque_Acier(int nb){
		this.setNombre((short) nb);
		this.setId(this.id);
	}
}
