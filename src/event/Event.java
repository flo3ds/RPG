package event;

public enum Event {

	attaque(new EventAttaque()),
	vendeur(new EventVendeur()),
	rien(new EventRien());

	private Object obj;

	Event(Object obj) {
		this.obj = obj;
	}

	public Object getEvent() {
		return this.obj;
	}
}
