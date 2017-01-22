package core;

import base.Craft_Category;

public class Recipe {

	private String name;
	private Object[] obj;
	private Object[] tool;
	private Object item;
	
	public Recipe(String name, Object item, Object[] obj, Object[] tool){
		this.name = name;
		this.obj = obj;
		this.item = item;
		this.tool = tool;
	}
	
	public String recipe(){
		String out = "";
		out += this.name + " => ";
		for(int i=0; i < this.obj.length; i++){
				out += this.obj[i].toString() +" | ";
		}
		
		if(this.tool != null){
			out += " <= ";
		for(int i=0; i < this.tool.length; i++){
			out += this.tool[i].toString() +" | ";
	}}
			
		return out + "\n";
	}
	
	public Object[] getObjectForRecipe(){
		return this.obj;
	}
	
	public Object[] getToolForRecipe(){
		return this.tool;
	}
	
	public Object getItem(){
		return this.item;
	}
}
