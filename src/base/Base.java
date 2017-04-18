package base;

import java.util.ArrayList;
import java.util.List;

import craft.CraftTable;
import event.GenEvent;

public class Base {

	public Coffre coffre = new Coffre();
	public CraftTable craftTable = new CraftTable();
	public GenEvent event;
	protected List<Object> amelio = new ArrayList<Object>();

	public Base(GenEvent event) {
		this.event = event;
	}

	public void addAmelio(Object obj) {
		this.amelio.add(obj);
	}

	public Object[] getAmelio() {
		return this.amelio.toArray();
	}

}
