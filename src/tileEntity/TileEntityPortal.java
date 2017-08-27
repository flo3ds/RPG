package tileEntity;

import core.Stack;
import graphicEngine.world.World_extends;

public class TileEntityPortal extends TileEntity implements ITileEntityElectriqueIn {

	private Boolean bouton[] = new Boolean[9];
	
	public TileEntityPortal() {
		for(int i=0; i<9; i++)
			bouton[i] = new Boolean(false);
	}

	public void update(World_extends world) {

	}


	@Override
	public int getBufferMax() {
		// TODO Auto-generated method stub
		return 99999;
	}

	@Override
	public int getDebit() {
		// TODO Auto-generated method stub
		return 999999;
	}

}