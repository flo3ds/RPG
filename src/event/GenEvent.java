package event;

import core.Rand;

public class GenEvent {

	public static Object genEvent(){
		return Event.values()[Rand.entier(0, Event.values().length)].getEvent();
	}
	
}
