package objects;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class ObjectState implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2977976442469803904L;
	private int activeState;
	private int nbState = 0;
	// private Map<String, Integer> state = new HashMap<String, Integer>();

	BiMap<String, Integer> bimap = HashBiMap.create();

	public int getActivStateId() {
		return activeState;
	}

	public String getActivStateName() {
		return getStateName(activeState);
	}

	public void setState(int activeState) {
		this.activeState = (short) activeState;
	}

	public void setState(String key) {
		setState(getState(key));
	}

	public int getState(String str) {
		return bimap.get(str);
	}

	public String getStateName(int id) {
		return bimap.inverse().get(id);
	}

	public int getNbState() {
		return nbState;
	}

	public void addStateExtends(String key) {
		nbState++;
		bimap.put(key, nbState);
	}
}
