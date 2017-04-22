package core.event;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import gui.layout.StructRet;
import perso.Personnage;

abstract public class Event_extends {

	private String helpBase;
	private String intro;
	private String rapport;
	private String contact;
	private Boolean ready = true;
	private List<Action_event> list_action = new ArrayList<Action_event>();

	private Personnage perso;

	private short randPourCent = 0;

	private short duree = 1;

	public short getRandPourCent() {
		return this.randPourCent;
	}

	public void setRandPourCent(short randPourCent) {
		this.randPourCent = randPourCent;
	}

	public short getDuree() {
		return this.duree;
	}

	public void setDuree(short duree) {
		this.duree = duree;
	}

	private StructRet defHelp() {
		StructRet out = new StructRet();
		ListIterator<Action_event> it = this.list_action.listIterator();
		while (it.hasNext()) {
			Action_event test = it.next();
			if(test.getId() != 99)
			out.add(test.getName(), test.getId());
		}
		out.setHeader(getIntro());
		return out;
	}

	public StructRet getHelp() {
		return this.defHelp();
	}

	public void setHelpBase(String help) {
		this.helpBase = help;
	}

	public String getHelpBase() {
		return this.helpBase;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getIntro() {
		return this.intro;
	}

	public String getRapport() {
		return this.rapport;
	}

	public void setRapport(String rapport) {
		this.rapport = rapport;
	}

	public String contact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void addAction(Action_event action_event) {
		this.list_action.add(action_event);
	}

	public Boolean test(int id) {
		ListIterator<Action_event> it = this.list_action.listIterator();
		while (it.hasNext()) {
			if (it.next().getId() == id)
				return true;
		}
		return false;
	}

	public boolean getEventReady() {
		return this.ready;
	}

	public boolean setEventReady(Boolean ready) {
		return this.ready = ready;
	}

}
