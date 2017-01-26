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
		this.setIntro("Il y a un rassemblement d'infect� pr�s de la base.\n");// description
																				// rapide
		this.setRapport("Un grand nombre d'infect� se rassemble autours de notre base\nIl va falloir s'en occuper.\n");// description
																														// detaill�
		this.setHelpBase("Attaque"); // Ici c'est une action, ne pas mettre de
										// \n
		this.setContact("Vous avez attir� l'attention des infect�es, il fonce dur vous!");// Contact
																							// avec
																							// l'event
		this.errorId = "EventAttaque";// le nom de la classe
		this.setDuree((short) 3); // dur�e de l'event avant disparition
		this.setRandPourCent((short) 10); // rand en pourcentage
		// On laisse la boucle, on remplace juste l'enum... ici Action_attaque
		// d�clar� en bas de la page
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
