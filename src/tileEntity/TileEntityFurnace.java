package tileEntity;

import core.Stack;
import graphicEngine.world.World_extends;
import items.Burnable;
import objects.Furnace;
import recipe.RecipeFurnace;
import recipe.RecipeScirie;

public class TileEntityFurnace extends TileEntity implements ITileEntityContainer {

	private int delay = 100;
	private int delta_delay = delay;
	private Stack stack_in;
	private Stack stack_out;
	private Stack stack_fuel;
	private Furnace furnace;
	private int fuel = 0;

	public TileEntityFurnace(Furnace furnace) {
		this.furnace = furnace;
		stack_in = new Stack();
		stack_out = new Stack();
		stack_fuel = new Stack();
	}

	public void update(World_extends world) {

		if (!stack_in.empty()) {
			if (fuel > 0) {
				for (int i = 0; i < RecipeFurnace.values().length; i++) {
					if (stack_in.getId().equals(RecipeFurnace.values()[i].recipe.getObjectForRecipe().getId())
							&& stack_in.getNombre() >= RecipeScirie.values()[i].recipe.getObjectForRecipe()
									.getNombre()) {
						if (delta_delay < 0) {
							if (stack_out.empty())
								stack_out.setStack(RecipeFurnace.values()[i].recipe.getResult());
							else
								stack_out.addItem(RecipeFurnace.values()[i].recipe.getResult().getItem(),
										RecipeScirie.values()[i].recipe.getResult().getNombre());
							stack_in.subNombre(RecipeFurnace.values()[i].recipe.getObjectForRecipe().getNombre());
							delta_delay = delay;
						} else
							delta_delay--;
					}
				}
			}
		}

		if (fuel > 0) {
			fuel--;
			furnace.setState("on");
		} else {
			if (!stack_fuel.empty()) {
				if (stack_fuel.getItem() instanceof Burnable) {
					fuel = ((Burnable) stack_fuel.getItem()).getFuelTime();
					stack_fuel.subNombre(1);
				} else {
					delta_delay = delay;
				}
			}else
				furnace.setState(0);
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

	public Boolean addStack_fuel(Stack stack) {
		if (stack instanceof Stack) {
			if (stack_fuel.empty()) {
				stack_fuel = new Stack(((Stack) stack).getItem(), ((Stack) stack).getNombre());
				return true;
			}
			this.stack_fuel.addItem((stack).getItem(), (stack).getNombre());
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

	public int getFuel() {
		return fuel;
	}

	@Override
	public Stack getStack() {
		return stack_out;
	}

	@Override
	public void putStack(Stack stack) {
		if (stack.getItem() instanceof Burnable)
			addStack_fuel(stack);
		else
			addStack_in(stack);
	}

	@Override
	public Boolean checkPut(Stack stack) {
		if (stack.getItem() instanceof Burnable) {
			if (stack_fuel.empty())
				return true;
			else if (stack_fuel.getNombre() + stack.getNombre() <= Stack.MAXSIZE
					&& stack_fuel.getId().equals(stack.getId()))
				if (stack.getId().equals(stack_fuel.getId()))
					return true;
		}
		for (int i = 0; i < RecipeFurnace.values().length; i++) {
			if (stack.getId().equals(RecipeFurnace.values()[i].recipe.getObjectForRecipe().getId())) {
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

	public Stack getStack_fuel() {
		// TODO Auto-generated method stub
		return stack_fuel;
	}

	public void removeStackFuel() {
		stack_fuel = new Stack();
	}

}
