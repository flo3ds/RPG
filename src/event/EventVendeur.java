package event;

import action.Position;
import base.Base;
import core.event.Action_event;
import core.event.Event_extends;
import core.event.I_Event;
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
		this.setDuree((short) 1);
		this.errorId = "EventVendeur";// le nom de la classe

		// On laisse la boucle, on remplace juste l'enum... ici Action_vendeur
		for (int i = 0; i < Action_vendeur.values().length; i++)
			this.addAction(Action_vendeur.values()[i].getAction());
	}

	public String execute(String in, Personnage perso, Base base) {
		if (Action_vendeur.commerce.action_event.test(in))
			return this.commerce();
		else if (Action_vendeur.base.action_event.test(in))
			return this.base(perso, base);
		else
			return this.errorId;
	}

	private String commerce() {
		return "C'est partit pour le commerce jai des pommes d'happy!";
	}

	public String base(Personnage perso, Base base) {
		perso.position = Position.base;
		String out = "Vous etes de retour a la base.\n";
		if (base.event.getEvent() != null)
			out += ((Event_extends) base.event.getEvent()).getIntro();
		return out;
	}

	private enum Action_vendeur {

		commerce("commerce"), base("base");

		public Action_event action_event;

		Action_vendeur(String str) {
			this.action_event = new Action_event(str);
		}

		public Action_event getAction() {
			return this.action_event;
		}

	}

}
