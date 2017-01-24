package event;

import core.Time;

public class GestionEvent {

	private Object event;
	
	public GestionEvent(Time time){
		if(time.getTime()>3)
			this.event = GenEvent.genEvent();
	}
	
	public String getHelp(){
		return ((Event_extends)this.event).getHelp();
	}
}
