package objects;

import perso.Personnage;

public class Chest extends Object {
	
	public Chest() {
		super("chest");
		
		addState("open");
		
	}
	
	public void click(Personnage perso) {
		if( getActivStateId() == 0)
			setState("open");
		else
			setState(0);
	}

	
	
}
