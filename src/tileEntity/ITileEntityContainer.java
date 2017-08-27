package tileEntity;

import core.Stack;

public interface ITileEntityContainer {

	public Stack getStack();

	public void putStack(Stack stack);

	public Boolean checkPut(Stack stack);

}
