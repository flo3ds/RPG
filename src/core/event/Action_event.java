package core.event;

public class Action_event {

	private String str;
	private int id;

	public Action_event(String str, int id) {
		this.str = str;
		this.id = id;
	}

	public String getName() {
		return this.str;
	}
	
	public int getId(){
		return id;
	}

	public Boolean test(int id) {
		if (id == this.id)
			return true;
		else
			return false;
	}

}
