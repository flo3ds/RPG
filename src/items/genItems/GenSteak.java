package items.genItems;

import items.Steak;

public class GenSteak {

	static public Steak genSteak() {
		
		int nb = (short) (1 + Math.random() * 5);
		
		return new Steak(nb);
}
	
}
