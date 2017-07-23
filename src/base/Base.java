package base;

import java.util.ArrayList;
import java.util.List;


public class Base {

	protected List<Object> amelio = new ArrayList<Object>();

	public Base() {
	}

	public void addAmelio(Object obj) {
		this.amelio.add(obj);
	}

	public Object[] getAmelio() {
		return this.amelio.toArray();
	}

}
