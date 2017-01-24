package core;

public class Action {

	private final String str;

	public Action(String str) {
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
