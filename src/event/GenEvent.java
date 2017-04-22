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
		this.obj = Event.vendeur.getEvent();
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
		/*if (this.obj != null)
			System.out.println("Event : " + this.obj.getClass());*/
	}

	public short randEventPourCent() {
		int[] begin = new int[Event.values().length];
		int[] end = new int[Event.values().length];
		int total = 0;
		for (short i = 0; i < Event.values().length; i++) {
			begin[i] = total;
			end[i] = total + ((Event_extends) Event.values()[i].getEvent()).getRandPourCent();
			total = +((Event_extends) Event.values()[i].getEvent()).getRandPourCent();
		}

		int rand = Rand.entier(0, total);

		for (short i = 0; i < Event.values().length; i++) {
			if (rand > begin[i] && rand < end[i])
				return i;
		}

		return 0;
	}

	public Object getEvent() {
		return this.obj;
	}

}
