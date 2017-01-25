package event;

import java.util.concurrent.Callable;

import event.EventVendeur.FuncVendeur;

public class Action_event {

	private String str;
	private FuncVendeur func;

	Action_event(String str, FuncVendeur func) {
		this.str = str;
		this.func = func;
	}

	public String getName() {
		return this.str;
	}
	
	public FuncVendeur getFunc() {
		return this.func;
	}

	public Boolean test(String in) {
		if (in.equals(this.str))
			return true;
		else
			return false;
	}
	
}
