package monde.sol;

import core.Rand;
import gui.map.Tileset;
import monde.GestionId;
import monde.MondeObj;

public class Pierre extends MondeObj {

	private Type type;

	public Pierre(GestionId gid) {
		super(gid, new Tileset("pierre", "pierre.png", 2, 32, 32, 32, 64));
		int rand = Rand.entier(0, Type.values().length);
		this.type = Type.values()[rand];

		setTexture(type.getId());
		setParsemé(2);
	}

	enum Type {
		bloque(0), caillou(1);

		private int id;

		public int getId() {
			return id;
		}

		Type(int id) {
			this.id = id;
		}
	}
}
