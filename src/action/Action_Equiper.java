package action;

import base.Base;
import gui.layout.StructRet;
import perso.Personnage;

public class Action_Equiper implements Actionable {

	private Personnage perso;
	private Base base;
	private Boolean equiper = false;

	public Action_Equiper(Personnage perso, Base base) {
		this.perso = perso;
		this.base = base;
	}

	public String base() {
		this.perso.position = this.perso.lastPosition;
		String out = "Vous etes de retour.\n";
		this.equiper = false;
		return out;
	}

	private StructRet listeEquipable() {
		StructRet out = perso.inv.liteEquipable();
		out.setHeader(this.perso.listeStuff());
		return out;
	}

	public StructRet init(){
		StructRet out = this.listeEquipable();
		//out.add(Action_equiper.retour.name(), Action_equiper.retour.getId());
		return out;
	}
	
	public String help() {
		String out = "";
		out += this.listeEquipable();
		out += Action_equiper.retour.action.getName() + "\n";
		this.equiper = true;
		return out;
	}

	public StructRet action(int id) {
				return this.equiper(id);
	}

	private StructRet equiper(int id) {
			this.perso.setEquipable(this.perso.inv.getItem(id));
			this.equiper = false;
			this.perso.inv.removeItem(id);
			return this.listeEquipable();
	}

	public enum Action_equiper {

		retour("retour", 99);

		public core.Action action;
		private int id;
		
		public int getId(){
			return id;
		}
		
		Action_equiper(String str, int id) {
			this.action = new core.Action(str, id);
		}
	}
}
