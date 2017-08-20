package tileEntity;

import core.Container;
import core.Inventable;
import core.Stack;
import graphicEngine.Vector2D;
import graphicEngine.world.World;
import graphicEngine.world.World_extends;
import items.Item;
import objects.Cable;
import objects.CableE;
import objects.ObjectContainer;

public class TileEntityCableE extends TileEntity implements ITileEntityContainer {
	
	Vector2D pos;
	CableE cable;
	public Stack stack = new Stack();
	private int delay = 35;
	private int delta_delay = delay;
	
	public TileEntityCableE(int x, int y, CableE cable) {
		this.pos = new Vector2D(x,y);
		this.cable = cable;
	}

	
	 public void update(World_extends world)
	 {
		String state = "";
		if(world.getTileEntity((int)pos.x, (int)pos.y-1) instanceof ITileEntityContainer) {
			state += "T";
		}
		if(world.getTileEntity((int)pos.x, (int)pos.y+1) instanceof ITileEntityContainer) {
				state += "B";
		}
		if(world.getTileEntity((int)pos.x-1, (int)pos.y) instanceof ITileEntityContainer) {
				state += "L";
		}
		if(world.getTileEntity((int)pos.x+1, (int)pos.y) instanceof ITileEntityContainer) {
				state += "R";
		}
		 
		if(state != "")
		 cable.setState(state); 
	 }

	@Override
	public Stack getStack() {
		// TODO Auto-generated method stub
		return stack;
	}

	@Override
	public void putStack(Stack stack) {
		stack.addNombre(stack.getNombre());
		
	}

	@Override
	public Boolean checkPut(Stack stack) {

		if(this.stack.getId() == null)
			return true;
		else if (stack.getId() == this.stack.getId() && stack.getNombre()+this.stack.getNombre() < Stack.MAXSIZE){
			return true;
		}else
			return false;
		
	}
}
