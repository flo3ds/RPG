package perso;

import core.Container;
import core.Equipable;
import gui.layout.StructRet;

public class Inventaire extends Container {

	public Inventaire() {
		this.setCases(10);
	}

	public StructRet liteEquipable() {
		StructRet out = new StructRet();
		for (int i = 0; i < this.cases; i++) {

			if (!this.inventaire.isEmpty())
				if (i < this.inventaire.size())
					if (this.inventaire.get(i) instanceof Equipable)
						out.add(this.inventaire.get(i).toString(), i);
		}
		return out;
	}

}