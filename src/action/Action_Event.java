package action;

import perso.Personnage;

// 
/**
 * Cette classe gere les actions du personnage C'est a dire les actions qui ne
 * dependent pas d'un lieux et qu'il peut faire en permanance Exemple : Regarder
 * son inventaire...
 */
public class Action_Event {

	/**
	 * retourne les actions disponible liée au personnage?
	 *
	 * @return the string
	 */
	public String help() {
		return Action.inventaire.getName() + "\n";
	}

	/**
	 * Liste l'inventaire du personnage
	 *
	 * @param perso
	 *            the perso
	 * @param in
	 *            la commande clavier
	 * @return the string
	 */
	public String listInventaire(Personnage perso, String in) {
		return "";
	}

	/**
	 * Execute l'action du personnage en fonction de la commande
	 *
	 * @param perso
	 *            the perso
	 * @param in
	 *            la commande clavier
	 * @return the string
	 */
	public String actionPerso(Personnage perso, String in) {
		if (Action.inventaire.test(in))
			return this.listInventaire(perso, in);
		else
			return "";
	}

	/**
	 * Test si un action liées au personnage va etre exécuté
	 *
	 * @param in
	 *            la commande clavier
	 * @return the boolean
	 */
	public Boolean actionPersoTest(String in) {
		if (Action.inventaire.test(in))
			return true;
		else
			return false;
	}
}
