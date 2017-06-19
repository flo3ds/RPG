package event;

import base.Base;
import core.event.Action_event;
import core.event.Event_extends;
import core.event.I_Event;
import gui.layout.StructRet;
import perso.Personnage;

public class EventAttaque extends Event_extends implements I_Event {

	private String errorId;

	public EventAttaque() {
		this.setIntro("Il y a un rassemblement d'infecté prés de la base.\n");// description
																				// rapide
		this.setRapport("Un grand nombre d'infecté se rassemble autours de notre base\nIl va falloir s'en occuper.\n");// description
																														// detaillé
		this.setHelpBase("Attaque"); // Ici c'est une action, ne pas mettre de
										// \n
		this.setContact("Vous avez attiré l'attention des infectées, il fonce sur vous!");// Contact
																							// avec
																							// l'event
		this.errorId = "EventAttaque";// le nom de la classe
		this.setDuree((short) 3); // durée de l'event avant disparition
		this.setRandPourCent((short) 10); // rand en pourcentage
		// On laisse la boucle, on remplace juste l'enum... ici Action_attaque
		// déclaré en bas de la page
		for (int i = 0; i < Action_attaque.values().length; i++)
			this.addAction(Action_attaque.values()[i].getAction());
	}

	public StructRet execute(int id, Personnage perso, Base base) {
		if (Action_attaque.attaque.action_event.test(id))
			return this.attaque();
		else if (Action_attaque.retour.action_event.test(id))
			return this.getHelp();
		else
			return new StructRet();
	}

	private StructRet attaque() {
		StructRet out = new StructRet();
		out.setHeader("Ataque");
		return out;
	}

	private enum Action_attaque {

		attaque("attaque", 0), retour("retour", 99);

		public Action_event action_event;

		Action_attaque(String str, int id) {
			this.action_event = new Action_event(str, id);
		}

		public Action_event getAction() {
			return this.action_event;
		}

	}

}
