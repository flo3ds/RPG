package action;

import base.Base;
import gui.layout.StructRet;
import perso.Personnage;

public class Action_Coffre implements Actionable {

	private Personnage perso;
	private Base base;
	private Mode mode;

	public Action_Coffre(Personnage perso, Base base) {
		this.perso = perso;
		this.base = base;
	}

	private StructRet error() {
		StructRet out = new StructRet();
		out.add("error", 0);
		return out;
	}

	public StructRet init() {
		mode = null;
		StructRet out = new StructRet();
		out.setHeader("Action :");
		out.add(Action_coffre.coffre_get.action.getName(), Action_coffre.coffre_get.action.getId());
		out.add(Action_coffre.coffre_put.action.getName(), Action_coffre.coffre_put.action.getId());
		return out;
	}

	public StructRet action(int id) {
		if (mode == null) {
			if (Action_coffre.coffre_get.action.test(id)) {
				mode = Mode.get;
				StructRet out = this.base.coffre.liste();
				out.setHeader("get :");
				out.add(Action_coffre.retour.action.getName(), Action_coffre.retour.action.getId());
				return out;
			} else if (Action_coffre.coffre_put.action.test(id)) {
				mode = Mode.put;
				StructRet out = this.perso.inv.liste();
				out.setHeader("put :");
				out.add(Action_coffre.retour.action.getName(), Action_coffre.retour.action.getId());
				return out;
			} else if (Action_coffre.retour.action.test(id))
				return this.init();
		} else if (mode == Mode.get) {
			if (Action_coffre.retour.action.test(id))
				return this.init();
			else if (this.perso.inv.putItem(this.base.coffre.getItem(id))) {
				StructRet out = this.base.coffre.liste();
				out.setHeader("Item non déposé !");
				out.add(Action_coffre.retour.action.getName(), Action_coffre.retour.action.getId());
				return out;
			} else {
				this.base.coffre.removeItem(id);
				StructRet out = this.base.coffre.liste();
				out.setHeader("Item déposé.");
				out.add(Action_coffre.retour.action.getName(), Action_coffre.retour.action.getId());
				return out;
			}
		} else if (mode == Mode.put) {
			if (Action_coffre.retour.action.test(id))
				return this.init();
			else {
				if (this.base.coffre.putItem(this.perso.inv.getItem(id))) {
					StructRet out = perso.inv.liste();
					out.setHeader("Item non déposé !");
					out.add(Action_coffre.retour.action.getName(), Action_coffre.retour.action.getId());
					return out;
				} else {
					this.perso.inv.removeItem(id);
					StructRet out = perso.inv.liste();
					out.setHeader("Item déposé.");
					out.add(Action_coffre.retour.action.getName(), Action_coffre.retour.action.getId());
					return out;
				}
			}
		} else
			return this.error();
		return this.error();

	}

	private enum Mode {
		put, get;
	}

	public enum Action_coffre {

		coffre_get("coffre get", 0), coffre_put("coffre put", 1), retour("retour", 99);

		public core.Action action;

		private int nb = 0;

		Action_coffre(String str, int id) {
			this.action = new core.Action(str, id);
		}
	}

}
