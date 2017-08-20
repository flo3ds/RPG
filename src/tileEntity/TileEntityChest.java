package tileEntity;

import core.Container;
import core.Inventable;
import core.Stack;
import items.Item;

public class TileEntityChest extends TileEntity implements ITileEntityContainer{

	public Container container = new Container();

	 
	 public void removeItem(int i) {
		 container.removeItem(i);
	 }
	 
	 public Stack getItem(int i) {
		 return container.getItem(i);
	 }
	 
	 public void putItem (Stack item) {
		 container.putItem(item);
	 }
	 
	 public int getSize() {
		 return container.getSize();
	}

	@Override
	public Stack getStack() {
		
		return container.getItem(0);
	}

	@Override
	public void putStack(Stack stack) {
		container.putItem(stack);
		
	}

	@Override
	public Boolean checkPut(Stack stack) {
		
		return true;
	}
}
