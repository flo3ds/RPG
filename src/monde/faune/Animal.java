package monde.faune;


public class Animal {

	private String especeP;
	private String especeA;
	private String ressemble;
	private String adjectif;
	private String endroit;
	private int albinos;

public Animal(){

	this.especeP = EspeceP.values()[(int) (Math.random() * EspeceP.values().length)].toString();
	this.especeA = EspeceA.values()[(int) (Math.random() * EspeceA.values().length)].toString();
	this.ressemble = Ressemble.values()[(int) (Math.random() * Ressemble.values().length)].toString();
	this.endroit = Endroit.values()[(int) (Math.random() * Endroit.values().length)].toString();
	this.adjectif = Adjectif.values()[(int) (Math.random() * Adjectif.values().length)].toString();
	
}

public String getDescriptionAnl(){
	return "L'animal "+this.ressemble+" à un "+this.adjectif + " "+this.especeP+ " des "+this.endroit+"\n";
	
}


private enum EspeceP {	// Espéce passive
	cochon,
	cerf,
	singe,
	aigle,
	renard,
	lapin,
	liévre,
	
}


private enum Ressemble {
	ressemble, semblable, s_apparente;
}

private enum Adjectif {
	monstrueu, immense, petit, grand, large, ridicule ;
}

private enum Endroit {
	montagnes, forêts, champs, neiges, sables, prairies;
}

private enum EspeceA {	//Espéce active
	loup,
	sanglier,
	ours,
	serpent,
	tyranosaure,
	tigre,
	
}

}