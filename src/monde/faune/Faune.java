package monde.faune;


public class Faune {
	
	private short nb_type_animal;
	private Animal[] animal;
	
	
	public Faune(){
		
		short random = (short)(Math.random() * (4)); // Nombre d'animaux entre 0 et 3
		this.nb_type_animal =random;
		animal = new Animal[this.nb_type_animal + 1];
		
		for(int i = 0; i < this.nb_type_animal; i++)
			animal[i] = new Animal();
	}
	
	
	public short getNbTypeAnimal(){
		return this.nb_type_animal;
	}
	
	public String getDescriptionType(){
		
		if(this.nb_type_animal>1)
			return "Il y a "+this.nb_type_animal+" type d'animaux différents :\n";
		
		else if(this.nb_type_animal==1)
			return "Il y a un seule animal :\n";
		else
			return "Il n'y a pas d'animaux dans les environs\n";
	}
	
	public String getDescriptionAnimal(int i){
		return "Description de l'animal "+(i+1)+" :\n"+animal[i].getDescriptionAnl();
	}
	
	
	public String getAllDescription(){
		
		String out = this.getDescriptionType()+"\n";
		
		for(int i = 0; i < this.nb_type_animal; i++)
			out += this.getDescriptionAnimal(i)+"\n";
		
		return out;
	}
	
}
