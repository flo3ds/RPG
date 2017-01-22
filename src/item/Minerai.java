package item;


public class Minerai extends Item{

	private String minerai;
	private matiere type;
	private String id = "minerai";
	
	public Minerai(matiere min, int nb){
		this.minerai = min.toString();
		this.setNombre((short) nb);
		this.setId(this.id+" "+min.toString());
	}

	//retourne la matiere sous forme de texte
	public String getMatiere() {
		return this.minerai;
	}
	
	//retourne la matiere sous forme enum
	public matiere getType(){
		return this.type;
	}

	public enum matiere {
		charbon, fer, cuivre, or, argent, diamond, etain;
	}

}
