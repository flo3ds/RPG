package action;

import gui.layout.StructRet;

public interface Actionable {

	public StructRet action(int id);

	public StructRet init();
}
