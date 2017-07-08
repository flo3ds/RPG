package objects;

public class ObjectState {
	
	public short activeState;
	
	public short getState() {
		return activeState;
	}

	public void setState(int activeState) {
		this.activeState = (short) activeState;
	}
}
