package monde.sol;

import core.Rand;
import gui.map.Tileset;
import items.Minerai;
import items.genItems.GenMinerai;
import monde.GestionId;
import monde.MondeObj;

public class Sol extends MondeObj {

	private String name;
	public Minerai minerais = GenMinerai.genMinerai();

	public Sol(GestionId gid) {

		super(gid, new Tileset("sol", "sol.png", 6, 32, 32, 32, 256));
		int rand = Rand.entier(0, Type.values().length);
		this.name = Type.values()[rand].toString();

		setTexture(Type.values()[rand].getId());
	}

	public String getDescription() {
		return "Composition principale du sol: " + this.name + ".\n";
	}

	public String analyseSol() {
		return this.getDescription() + "L'analyse révelle des traces de " + this.minerais.getMatiere() + ".\n";
	}

	private enum Type {
		herbe(0), terre(1), roche(2), neige(3), glace(4), pousiere(5);

		private int id;

		public int getId() {
			return id;
		}

		Type(int id) {
			this.id = id;
		}
	}

}
