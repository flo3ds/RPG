package event;

import core.Rand;
import core.event.Event_extends;
import perso.Personnage;

public class GenEvent {

	private Object obj = null;
	private Personnage perso;
	private long begin = 0;
	private long end = 0;
	private long interval = 3;

	public GenEvent(Personnage perso) {
		this.perso = perso;
	}

	public void genEvent() {
		if (this.obj == null) {
			if (perso.getTime().getTime() > end + interval) {
				this.obj = Event.values()[Rand.entier(0, Event.values().length)].getEvent();
				this.begin = perso.getTime().getTime();
			}
		} else {
			if (perso.getTime().getTime() > this.begin + ((Event_extends) this.obj).getDuree()) {
				this.obj = null;
				this.end = perso.getTime().getTime();
			}
		}
	}

	public Object getEvent() {
		return this.obj;
	}

}
