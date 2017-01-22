package items;

public class PlancheBois extends Item{

	
		private String id = "Planche de bois";

		
		public PlancheBois(){
			this.setId(this.id);
		}
		
		
		public PlancheBois(int nb){
			this.setNombre((short) nb);
			this.setId(this.id);
		}
	}
	
	

