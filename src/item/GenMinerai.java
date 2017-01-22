package item;

import core.Rand;

public class GenMinerai {	
	
	//Creer un minerai aléatoire plus le retourne
	static public Minerai genMinerai() {
		//Créer un type de minerai aléatoire
		Minerai.matiere type = Minerai.matiere.values()[Rand.entier(0, Minerai.matiere.values().length)];
		//Créer un nombre de minerai aléatoire
		int nb = (short) (1 + Math.random() * 10);
		//Crée le minerai avec les infos précedentes et le retourne
		return new Minerai(type, nb);
	}

}
