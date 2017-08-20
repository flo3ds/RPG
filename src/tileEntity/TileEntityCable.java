package tileEntity;

import core.Container;
import core.Inventable;
import core.Stack;
import graphicEngine.Vector2D;
import graphicEngine.world.World;
import graphicEngine.world.World_extends;
import items.Item;
import objects.Cable;
import objects.ObjectContainer;

public class TileEntityCable extends TileEntity implements ITileEntityContainer {
	
	Vector2D pos;
	Cable cable;
	public Stack stack = new Stack();
	private int delay = 35;
	private int delta_delay = delay;
	
	public TileEntityCable(int x, int y, Cable cable) {
		this.pos = new Vector2D(x,y);
		this.cable = cable;
	}

	
	 public void update(World_extends world)
	 {
		 if(world.getTileEntity((int)pos.x-1, (int)pos.y) instanceof ITileEntityContainer
				 && world.getTileEntity((int)pos.x+1, (int)pos.y) instanceof ITileEntityContainer) {
			 ITileEntityContainer in = (ITileEntityContainer) world.getTileEntity((int)pos.x-1, (int)pos.y);
			 ITileEntityContainer out = (ITileEntityContainer) world.getTileEntity((int)pos.x+1, (int)pos.y);
			cable.setState("LR");
			if(delta_delay < 0) {
				if(in.getStack() == null)
					return;
				else if(in.getStack().getItem() == null || in.getStack().getNombre() < 1)
					return;
				else if(out.checkPut(new Stack(in.getStack().getItem(), 1))){
					out.putStack(new Stack(in.getStack().getItem(), 1));
					in.getStack().subNombre(1);;
				}
				delta_delay = delay;
			}else{
				delta_delay--;
				
			}
			
		 }else if(world.getTileEntity((int)pos.x-1, (int)pos.y) instanceof ITileEntityContainer) {
				cable.setState("L");
				
			 }else if(world.getTileEntity((int)pos.x+1, (int)pos.y) instanceof ITileEntityContainer) {
					cable.setState("R");
					
			 }
			 
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
