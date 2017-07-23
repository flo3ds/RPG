package tileEntity;

import core.Inventable;
import core.Stack;
import init.Items;
import items.Burnable;
import items.Item;

public class TileEntityCrafting_station extends TileEntity {
	
	private int delay = 100;
	private int delta_delay = delay;
	private Stack stack_in ;
	private Stack stack_out;

	
	
	public void update() {
		
		if(stack_in != null ){
			if(stack_in.getId().equals(Items.BOIS.getId()) && stack_in.getNombre() >= 2){
				if(delta_delay < 0) {
					if(stack_out == null)
						stack_out = new Stack(Items.PLANCHE, 5);
					else
						stack_out.addItem(Items.PLANCHE, 5);
					stack_in.subNombre(2);
					delta_delay = delay;
				}else{
					delta_delay--;
					
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
	
	public Boolean addStack_in(Inventable stack) {
		if(stack instanceof Stack){
			if(stack_in == null){
				stack_in = (Stack) stack;
				return true;
			}
			this.stack_in.addItem(((Stack)stack).getItem(), ((Stack)stack).getNombre());
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

}
