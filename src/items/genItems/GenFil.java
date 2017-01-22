package items.genItems;

import items.Fil;

public class GenFil {

	static public Fil genFil() {
		
		int nb = (short) (1 + Math.random() * 10);
		
		return new Fil(nb);
}
	
}
