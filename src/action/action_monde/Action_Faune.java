package action.action_monde;

import action.Action;
import action.Action_Monde;
import action.Action_Perso;
import action.Position;
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
			return this.base(perso);
		else if (Action.arrêter_chasse.test(in))
			return this.arretChasse(perso);
		else if (this.actionPersoTest(in))
			return this.actionPerso(perso, in);
		else
			return this.help();

	}
	
	public String arretChasse(Personnage perso) {
		perso.position = Position.monde;
		return "Vous revenez de votre chasse.\n";
	}
	
	public String base(Personnage perso) {
		perso.position = Position.base;
		return "Vous rentrez a votre base.\n";
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
		out += this.help_perso();
		return out;
	}
}
