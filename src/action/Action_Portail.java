package action;

import base.Base;
import gui.GenWorld.GenWorldMap;
import gui.layout.StructRet;
import gui.map.Map;
import perso.Personnage;

public class Action_Portail implements Actionable{

	private Personnage perso;
	private Base base;
	public Boolean sonder = false;
	private Monde_Actif monde;
	
	private StructRet error() {
		StructRet out = new StructRet();
		out.add("error", 0);
		return out;
	}

	public Action_Portail(Personnage perso, Base base, Monde_Actif monde) {
		this.perso = perso;
		this.base = base;
		this.monde = monde;
	}

	public StructRet explorer() {
		this.perso.position = Position.monde;
		this.perso.addTime(1);
		monde.genMonde();
		GenWorldMap.genWorldMap(monde.getMonde());
		Map map = new Map();
		map.init("world/test/test.map");
		this.perso.teleport(map);
		StructRet out = new StructRet();
		out.add("", 999);
		return out;
	}

	public StructRet sonder() {
		this.perso.addTime(1);
		return new StructRet();// L'action sonder et gerer dans GUIMain
	}

	public StructRet action(int id) {
		if (Action_portail.explorer.action.test(id))
			return this.explorer();
		else if (Action_portail.sonder.action.test(id))
			return this.sonder();
		else if (Action_portail.retour.action.test(id))
			return this.init();
		else
			return this.error();
	}

	public enum Action_portail {

		retour("retour", 99), explorer("explorer", 0), sonder("sonder", 1);

		public core.Action action;

		Action_portail(String str, int id) {
			this.action = new core.Action(str, id);
		}
	}

	@Override
	public StructRet init() {
		StructRet out = new StructRet();
		out.add(Action_portail.explorer.action.getName(), Action_portail.explorer.action.getId());
		out.add(Action_portail.sonder.action.getName(), Action_portail.sonder.action.getId());
		return out;
	}
}
