package core.event;

import base.Base;
import gui.layout.StructRet;
import perso.Personnage;

public interface I_Event {
	public StructRet execute(int in, Personnage perso, Base base);
}
