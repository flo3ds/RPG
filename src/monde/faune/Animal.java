package monde.faune;


public class Animal {

	private String especeP;
	private String especeA;
	private int albinos;

public Animal(){

	this.especeP = EspeceP.values()[(int) (Math.random() * EspeceP.values().length)].toString();
	this.especeA = EspeceA.values()[(int) (Math.random() * EspeceA.values().length)].toString();
	
	
}

public String getDescriptionAnl(){
	return "L'animal est un "+this.especeP + ".\n";
	
}


private enum EspeceP {	// Esp�ce passive
	cochon,
	cerf,
	singe,
	aigle,
	renard,
	lapin,
	
}

private enum EspeceA {	//Esp�ce active
	loup,
	sanglier,
	ours,
	serpent,
	tyranosaure,
	tigre,
	
}

}