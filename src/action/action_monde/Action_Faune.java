package action.action_monde;

import action.Action;
import action.Action_Monde;
import action.Action_Perso;
import action.Action_Perso;
import action.Position;
import monde.GenMonde;
import perso.Personnage;
import perso.Personnage;

public class Action_Faune extends Action_Perso {

	private Personnage perso;

	public Action_Faune(Personnage perso) {
		this.perso = perso;
	}

	public String action(String in, GenMonde monde) {
		if (Action.chasser.test(in)) {
			return this.Chasser(monde);
		} else if (Action.base.test(in))
			return this.base();
		else if (Action.arr�ter_chasse.test(in))
			return this.arretChasse();
		else if (this.actionPersoTest(in))
			return this.actionPerso(this.perso, in);
		else
			return this.help();

	}

	public String arretChasse() {
		this.perso.position = Position.monde;
		return "Vous revenez de votre chasse.\n";
	}

	public String base() {
		this.perso.position = Position.base;
		return "Vous rentrez a votre base.\n";
	}

	public String Chasser(GenMonde monde) {
		if (monde.faune.nb_type_animal > 0) {
			this.perso.inv.putItem(monde.faune.steak);
			return "Vous r�cuperez " + monde.faune.steak.getNombre() + " Steaks " + ".\n";
		} else
			return ("Vous perdez une heure a chasser le vent qui vous siffle aux oreilles");

	}

	public String help() {
		String out = "";
		out += Action.chasser.getName() + "\n";
		out += Action.arr�ter_chasse.getName() + "\n";
		out += Action.base.getName() + "\n";
		out += this.help_perso();
		return out;
	}
}
