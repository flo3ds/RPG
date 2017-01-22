package items;

public class Bois extends Item{

	
	private String id = "Bois";

	
	public Bois(){
		this.setId(this.id);
	}
	
	
	public Bois(int nb){
		this.setNombre((short) nb);
		this.setId(this.id);
	}
}