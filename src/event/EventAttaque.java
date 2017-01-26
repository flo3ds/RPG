package event;

import action.Position;
import base.Base;
import core.event.Action_event;
import core.event.Event_extends;
import core.event.I_Event;
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
		this.setContact("Vous avez attiré l'attention des infectées, il fonce dur vous!");// Contact
																							// avec
																							// l'event
		this.errorId = "EventAttaque";// le nom de la classe
		this.setDuree((short) 3);
		// On laisse la boucle, on remplace juste l'enum... ici Action_vendeur
		for (int i = 0; i < Action_attaque.values().length; i++)
			this.addAction(Action_attaque.values()[i].getAction());
	}

	public String execute(String in, Personnage perso, Base base) {
		if (Action_attaque.attaque.action_event.test(in))
			return this.attaque();
		else if (Action_attaque.base.action_event.test(in))
			return this.base(perso, base);
		else
			return this.errorId;
	}

	private String attaque() {
		return "C'est partit pour l'attaque!";
	}

	public String base(Personnage perso, Base base) {
		perso.position = Position.base;
		String out = "Vous etes de retour a la base.\n";
		if (base.event.getEvent() != null)
			out += ((Event_extends) base.event.getEvent()).getIntro();
		return out;
	}

	private enum Action_attaque {

		attaque("attaque"), base("base");

		public Action_event action_event;

		Action_attaque(String str) {
			this.action_event = new Action_event(str);
		}

		public Action_event getAction() {
			return this.action_event;
		}

	}

}
