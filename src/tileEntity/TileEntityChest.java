package tileEntity;

import core.Container;
import core.Inventable;
import core.Stack;
import items.Item;

public class TileEntityChest extends TileEntity {

	public Container container = new Container();
	
	 public void update()
	 {
		 
	 }
	 
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
}
