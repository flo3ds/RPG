package core;

public class Action {

	private final String str;
	private int id;

	public Action(String str, int id) {
		this.str = str;
		this.id = id;
	}

	public String getName() {
		return this.str;
	}

	public Boolean test(int id) {

		if (id == this.id)
			return true;
		else
			return false;
	}

	public int getId() {
		return id;
	}

}
