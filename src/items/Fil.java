package items;

public class Fil extends Item{

	
	private String id = "Fil";

	
	public Fil(){
		this.setId(this.id);
	}
	
	
	public Fil(int nb){
		this.setNombre((short) nb);
		this.setId(this.id);
	}
}