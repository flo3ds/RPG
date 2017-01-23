package items;

public class Steak extends Item{

	
	private String id = "Steak";
 
	
	public Steak(){
		this.setId(this.id);
	}
	
	
	public Steak(int nb){
		this.setNombre((short) nb);
		this.setId(this.id);
	}
}