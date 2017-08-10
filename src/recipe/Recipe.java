package recipe;

import core.Stack;

public class Recipe {

	private String name;
	private Stack[] obj;
	private Stack[] tool;
	private Stack item;

	public Recipe(String name, Stack item, Stack[] obj, Stack[] tool) {
		this.setName(name);
		this.obj = obj;
		this.item = item;
		this.tool = tool;
	}


	public Stack[] getObjectForRecipe() {
		return this.obj;
	}

	public Stack[] getToolForRecipe() {
		return this.tool;
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
