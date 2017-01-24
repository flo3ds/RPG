package perso;

import action.Position;
import core.Time;

public class Personnage {

	private Time time;

	public Personnage(Time time) {
		this.time = time;
	}

	public Inventaire inv = new Inventaire();
	public Position position = Position.base;

	public void addTime(long jours) {
		this.time.addTime(jours);
	}

	public Time getTime() {
		return this.time;
	}

}
