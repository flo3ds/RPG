package tileEntity;

import core.Container;
import core.Stack;

public class TileEntityChest extends TileEntity implements ITileEntityContainer {

	public Container container = new Container(18);

	public void removeItem(int i) {
		container.removeItem(i);
	}

	public Stack getItem(int i) {
		return container.getItem(i);
	}

	public void putItem(Stack item) {
		container.putItem(item);
	}

	public int getSize() {
		return container.getSize();
	}

	@Override
	public Stack getStack() {
		for (int i = 0; i < container.getSize(); i++) {
			if (!container.getItem(i).empty())
				return container.getItem(i);
		}
		return null;
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
