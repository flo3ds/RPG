package action;

import perso.Personnage;

// 
/**
 * Cette classe gere les actions du personnage C'est a dire les actions qui ne
 * dependent pas d'un lieux et qu'il peut faire en permanance Exemple : Regarder
 * son inventaire...
 */
public class Action_Perso {

	/**
	 * retourne les actions disponible liée au personnage?
	 *
	 * @return the string
	 */
	public String help_perso() {
		String out = "";
		out += Action_perso.inventaire.action.getName() + "\n";
		out += Action_perso.equiper.action.getName() + "\n";
		return out;
	}

	public String equiper(Personnage perso) {
		perso.lastPosition = perso.position;
		perso.position = Position.equiper;
		return perso.listeStuff();
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
		if (Action_perso.inventaire.action.test(in))
			return perso.getGold() + "PO\n" + perso.inv.liste();
		else if (Action_perso.equiper.action.test(in))
			return this.equiper(perso);
		else
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
		if (Action_perso.inventaire.action.test(in))
			return this.listInventaire(perso, in);
		else if (Action_perso.equiper.action.test(in))
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
		if (Action_perso.inventaire.action.test(in))
			return true;
		else if (Action_perso.equiper.action.test(in))
			return true;
		else
			return false;
	}

	public enum Action_perso {

		inventaire("inventaire"), equiper("equiper");

		public core.Action action;

		Action_perso(String str) {
			this.action = new core.Action(str);
		}
	}
}
