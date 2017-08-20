package tileEntity;

import core.Inventable;

import core.Stack;
import graphicEngine.world.World_extends;
import recipe.RecipeScirie;
import init.Items;
import items.Item;

public class TileEntityScirie extends TileEntity implements ITileEntityContainer{
	
	private int delay = 100;
	private int delta_delay = delay;
	private Stack stack_in ;
	private Stack stack_out;

	
	public void update(World_extends world) {
		
		if(stack_in != null ){
			for (int i=0; i< RecipeScirie.values().length; i++) {
				if(stack_in.getId().equals(RecipeScirie.values()[i].recipe.getObjectForRecipe().getId()) && stack_in.getNombre() >= RecipeScirie.values()[i].recipe.getObjectForRecipe().getNombre()){
					if(delta_delay < 0) {
						if(stack_out == null)
							stack_out = RecipeScirie.values()[i].recipe.getResult();
						else
							stack_out.addItem(RecipeScirie.values()[i].recipe.getResult().getItem(), RecipeScirie.values()[i].recipe.getResult().getNombre());
						stack_in.subNombre(RecipeScirie.values()[i].recipe.getObjectForRecipe().getNombre());
						delta_delay = delay;
					}else{
						delta_delay--;
						
					}
				}
			}
		}
	}

	public Stack getStack_in() {
		return stack_in;
	}

	public void setStack_in(Stack stack_in) {
		this.stack_in = stack_in;
	}
	
	public Boolean addStack_in(Stack stack) {
		if(stack instanceof Stack){
			if(stack_in == null){
				stack_in = new Stack(((Stack) stack).getItem(), ((Stack) stack).getNombre());
				return true;
			}
			this.stack_in.addItem((stack).getItem(), (stack).getNombre());
			return true;
		}
		return false;
	}
	
	public Stack getStack_out() {
		return stack_out;
	}

	public void setStack_out(Stack stack_out) {
		this.stack_out = stack_out;
	}

	public void removeStackOut() {
		stack_out = null;	
	}
	
	public void removeStackIn() {
		stack_in = null;
		delta_delay = delay;
	}

	@Override
	public Stack getStack() {
		return stack_out;
	}

	@Override
	public void putStack(Stack stack) {
		this.addStack_in(stack);
	}

	@Override
	public Boolean checkPut(Stack stack) {
		for (int i=0; i< RecipeScirie.values().length; i++) {
			System.out.println("test recette "+i +" if "+stack.getId()+" == "+ RecipeScirie.values()[i].recipe.getObjectForRecipe().getId());
			if(stack.getId().equals(RecipeScirie.values()[i].recipe.getObjectForRecipe().getId())){
				System.out.println("test");
				if(stack_in == null)
					return true;
				else if(stack_in.getId().equals(""))
					return true;
				else if(stack_in.getNombre() + stack.getNombre() <= Stack.MAXSIZE)
					if(stack.getId().equals(stack_in.getId()))
						return true; 
			}
			
		}
		return false;
	}

}
