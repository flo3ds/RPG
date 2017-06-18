package event;

import base.Base;
import core.event.Action_event;
import core.event.Event_extends;
import core.event.I_Event;
import gui.layout.StructRet;
import perso.Personnage;

public class EventRien extends Event_extends implements I_Event {

	private String errorId;

	public EventRien() {

		this.setEventReady(false);// Event jamais appelé si false
		this.setIntro("RAS");// description rapide
		this.setRapport("");// description
							// detaillé
		this.setHelpBase(""); // Ici c'est une action, ne pas mettre de \n
		this.setContact("");// Contact avec
		this.setDuree((short) 2); // durée de l'event avant disparition
		this.setRandPourCent((short) 100); // rand en pourcentage
		this.errorId = "EventRien";// le nom de la classe

		// On laisse la boucle, on remplace juste l'enum... ici Action_vendeur
		// déclaré en bas de la page
		for (int i = 0; i < Action_rien.values().length; i++)
			this.addAction(Action_rien.values()[i].getAction());
	}

	public StructRet execute(int id, Personnage perso, Base base) {
		StructRet out = new StructRet();
		out.setHeader("RAS.");
		return out;
	}

	private enum Action_rien {

		;

		public Action_event action_event;

		Action_rien(String str, int id) {
			this.action_event = new Action_event(str, id);
		}

		public Action_event getAction() {
			return this.action_event;
		}

	}
}
