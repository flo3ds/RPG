package core.event;

import base.Base;
import perso.Personnage;

public interface I_Event {
	public String execute(String in, Personnage perso, Base base);
}
