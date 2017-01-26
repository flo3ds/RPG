package perso;

import core.Container;
import core.Equipable;
import core.Tool;

public class Inventaire extends Container {

	public Inventaire() {
		this.setCases(10);
	}

	public String liteEquipable() {
		String out = "";
		for (int i = 0; i < this.cases; i++) {

			if (!this.inventaire.isEmpty())
				if (i < this.inventaire.size())
					if (this.inventaire.get(i) instanceof Equipable)
						out += i + " | " + this.inventaire.get(i).toString();
			out += "\n";
		}
		return out;
	}

}