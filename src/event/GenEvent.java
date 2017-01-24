package event;

import core.Rand;
import perso.Personnage;

public class GenEvent {
	
	private Object obj;
	private Personnage perso;
	
	public GenEvent(Personnage perso){
		this.perso = perso;
	}

	public void genEvent(){
		if(perso.getTime().getTime() > 3)
			this.obj = Event.values()[Rand.entier(0, Event.values().length)].getEvent();
		else
			this.obj = null;
	}
	
	public Object getEvent(){
		return this.obj;
	}
	
}
