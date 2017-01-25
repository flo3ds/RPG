package event;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import action.Action_Event;
import core.Inventable;
import perso.Personnage;

abstract public class Event_extends {

	private String help;
	private String helpBase;
	private String intro;
	private String rapport;
	private String contact;
	private List<Action_event> list_action = new ArrayList<Action_event>();
	
	private Personnage perso;


	protected abstract String defHelp();
	
	public void setPersonnage(Personnage perso){
		this.perso = perso;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public String getHelp() {
		return this.help;
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
	
	public void setContact(String contact){
		this.contact = contact;
	}
	
	public void addAction(Action_event action_event){
		this.list_action.add(action_event);
	}

	public Boolean test(String in) {
		ListIterator<Action_event> it = this.list_action.listIterator();
		while (it.hasNext()) {
			if ( it.next().getName().equals(in))
				return true;
		}
		return false;
	}
	
		public String action(String in) {
			ListIterator<Action_event> it = this.list_action.listIterator();
			while (it.hasNext()) {
				Action_event action_event_test = it.next();
				if ( action_event_test.getName().equals(in))
					try {
						return action_event_test.getFunc().call(this.perso, in);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			return "non ok";
		}
	
	
}
