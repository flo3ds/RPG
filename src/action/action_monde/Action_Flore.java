package action.action_monde;

import action.Action;
import action.Action_Monde;
import action.Action_Perso;
import action.Action_Perso;
import action.Position;
import monde.GenMonde;
import perso.Personnage;
import perso.Personnage;

public class Action_Flore extends Action_Perso {

	private Personnage perso;

	public Action_Flore(Personnage perso) {
		this.perso = perso;
	}

	public String action(String in, GenMonde monde) {
		if (Action.cueillir.test(in)) {
			return this.cueillir(monde);
		} else if (Action.base.test(in))
			return this.base();
		else if (Action.arrêter_cueillir.test(in))
			return this.arretcueillir();
		else if (this.actionPersoTest(in))
			return this.actionPerso(this.perso, in);
		else
			return this.help();

	}

	public String arretcueillir() {
		this.perso.position = Position.monde;
		return "Vous revenez de votre cueillette.\n";
	}

	public String base() {
		this.perso.position = Position.base;
		return "Vous rentrez a votre base.\n";
	}

	public String cueillir(GenMonde monde) {
			this.perso.inv.putItem(monde.flore.plante);
			return "Vous récuperez " + monde.flore.plante.getNombre() + " Plantes " + monde.flore.plante.getPlante()+ ".\n";

	}

	public String help() {
		String out = "";
		out += Action.cueillir.getName() + "\n";
		out += Action.arrêter_cueillir.getName() + "\n";
		out += Action.base.getName() + "\n";
		out += this.help_perso();
		return out;
	}
}
