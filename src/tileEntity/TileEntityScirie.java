package tileEntity;

import core.Stack;
import graphicEngine.world.World_extends;
import recipe.RecipeScirie;

public class TileEntityScirie extends TileEntity implements ITileEntityContainer {

	private int delay = 100;
	private int delta_delay = delay;
	private Stack stack_in;
	private Stack stack_out;
	
	public TileEntityScirie() {
		stack_in = new Stack();
		stack_out = new Stack();
	}

	public void update(World_extends world) {

		if (!stack_in.empty()) {
			for (int i = 0; i < RecipeScirie.values().length; i++) {
				if (stack_in.getId().equals(RecipeScirie.values()[i].recipe.getObjectForRecipe().getId())
						&& stack_in.getNombre() >= RecipeScirie.values()[i].recipe.getObjectForRecipe().getNombre()) {
					if (delta_delay < 0) {
						if (stack_out.empty())
							stack_out.setStack(RecipeScirie.values()[i].recipe.getResult());
						else
							stack_out.addItem(RecipeScirie.values()[i].recipe.getResult().getItem(),
									RecipeScirie.values()[i].recipe.getResult().getNombre());
						stack_in.subNombre(RecipeScirie.values()[i].recipe.getObjectForRecipe().getNombre());
						delta_delay = delay;
					} else {
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
		if (stack instanceof Stack) {
			if (stack_in.empty()) {
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
		stack_out = new Stack();
	}

	public void removeStackIn() {
		stack_in = new Stack();
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
		for (int i = 0; i < RecipeScirie.values().length; i++) {
			if (stack.getId().equals(RecipeScirie.values()[i].recipe.getObjectForRecipe().getId())) {
				if (stack_in.empty())
					return true;
				else if (stack_in.getNombre() + stack.getNombre() <= Stack.MAXSIZE
						&& stack_in.getId().equals(stack.getId()))
					if (stack.getId().equals(stack_in.getId()))
						return true;
			}

		}
		return false;
	}

}
