package item;

public class GenBois {
	

		static public Bois genBois() {
			
			int nb = (short) (1 + Math.random() * 10);
			
			return new Bois(nb);
}
}
