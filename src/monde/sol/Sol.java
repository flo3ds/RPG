package monde.sol;

import core.Rand;
import items.Minerai;
import items.genItems.GenMinerai;

public class Sol {

	private String type;
	public Minerai minerais = GenMinerai.genMinerai();
	

	public Sol() {
		this.type = Type.values()[Rand.entier(0, Type.values().length)].toString();
	}

	public String getDescription() {
		return "Le sol est composé de " + this.type + ".\n";
	}

	public String analyseSol() {
		return "L'analyse du sol révelle des traces de " + this.minerais.getMatiere() + ".\n";
	}

	private enum Type {
		sable, roche, poussiere, glaise, terre;
	}

}
