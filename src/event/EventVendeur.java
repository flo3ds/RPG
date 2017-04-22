package event;

import base.Base;
import core.event.Action_event;
import core.event.Event_extends;
import core.event.I_Event;
import gui.layout.StructRet;
import perso.Personnage;

public class EventVendeur extends Event_extends implements I_Event {

	private String errorId;

	public EventVendeur() {
		this.setIntro("Un vendeur est a la porte.\n");// description rapide
		this.setRapport("Un vendeur bizare est a la porte.\nIl souhaite commercer avec nous.\n");// description
																									// detaillé
		this.setHelpBase("Parler"); // Ici c'est une action, ne pas mettre de \n
		this.setContact("Hé ta des sous? j'ai de la bonne cam!");// Contact avec
																	// l'event
		this.setDuree((short) 1); // durée de l'event avant disparition
		this.setRandPourCent((short) 20); // rand en pourcentage
		this.errorId = "EventVendeur";// le nom de la classe

		// On laisse la boucle, on remplace juste l'enum... ici Action_vendeur
		// déclaré en bas de la page
		for (int i = 0; i < Action_vendeur.values().length; i++)
			this.addAction(Action_vendeur.values()[i].getAction());
	}

	public StructRet execute(int id, Personnage perso, Base base) {
		if (Action_vendeur.commerce.action_event.test(id))
			return this.commerce();
		else if (Action_vendeur.retour.action_event.test(id))
			return this.getHelp();
		else
			return new StructRet();
	}

	private StructRet commerce() {
		StructRet out = new StructRet();
		out.setHeader("C'est partit pour le commerce jai des pommes d'happy!");
		return out;
	}

	private enum Action_vendeur {

		commerce("commerce", 0), retour("retour", 99);

		public Action_event action_event;

		Action_vendeur(String str, int id) {
			this.action_event = new Action_event(str, id);
		}

		public Action_event getAction() {
			return this.action_event;
		}

	}

}
