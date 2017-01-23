package action.action_monde;

import action.Action;
import action.Action_Monde;
import action.Action_Perso;
import monde.GenMonde;
import perso.Personnage;

public class Action_Faune extends Action_Perso {

	public String action(Personnage perso, String in, GenMonde monde) {
		if (Action.chasser.test(in)) {
			if (monde.faune.nb_type_animal > 0)
				return this.Chasser(perso, monde);
			else
				return ("Vous perdez une heure a chasser le vent qui vous siffle aux oreilles");
		} else if (Action.base.test(in))
			return Action_Monde.base(perso);
		else if (Action.arrêter_chasse.test(in))
			return Action_Monde.arretChasse(perso);
		else
			return this.help();

	}

	public String Chasser(Personnage perso, GenMonde monde) {
		perso.inv.putItem(monde.faune.steak);
		return "Vous récuperez " + monde.faune.steak.getNombre() + " Steaks " + ".\n";

	}

	public String help() {
		String out = "";
		out += Action.chasser.getName() + "\n";
		out += Action.arrêter_chasse.getName() + "\n";
		out += Action.base.getName() + "\n";
		return out;
	}
}
