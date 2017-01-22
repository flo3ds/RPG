package items.genItems;

import core.Rand;
import items.Minerai;
import items.Minerai.matiere;

public class GenMinerai {	
	
	//Creer un minerai al�atoire plus le retourne
	static public Minerai genMinerai() {
		//Cr�er un type de minerai al�atoire
		Minerai.matiere type = Minerai.matiere.values()[Rand.entier(0, Minerai.matiere.values().length)];
		//Cr�er un nombre de minerai al�atoire
		int nb = (short) (1 + Math.random() * 10);
		//Cr�e le minerai avec les infos pr�cedentes et le retourne
		return new Minerai(type, nb);
	}
}