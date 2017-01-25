package core.event;

import java.util.concurrent.Callable;


public class Action_event {

	private String str;

	public Action_event(String str) {
		this.str = str;
	}

	public String getName() {
		return this.str;
	}
	
	public Boolean test(String in) {
		if (in.equals(this.str))
			return true;
		else
			return false;
	}
	
}
