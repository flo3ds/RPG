package recipe;

import core.Stack;

public class RecipeMachine {

	private String name;
	private Stack obj;
	private Stack item;

	public RecipeMachine(String name, Stack item, Stack obj) {
		this.setName(name);
		this.obj = obj;
		this.item = item;
	}


	public Stack getObjectForRecipe() {
		return this.obj;
	}

	public Stack getResult() {
		return this.item;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
}
