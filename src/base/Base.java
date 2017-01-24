package base;

import event.GenEvent;

public class Base {

	public Coffre coffre = new Coffre();
	public CraftTable craftTable = new CraftTable();

	public GenEvent event;

	public Base(GenEvent event) {
		this.event = event;
	}
}
