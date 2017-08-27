package tileEntity;

import core.Stack;
import graphicEngine.world.World_extends;
import world.Code;

public class TileEntityDhd extends TileEntity implements ITileEntityElectriqueIn {

	private Boolean bouton[] = new Boolean[9];
	
	public TileEntityDhd() {
		for(int i=0; i<9; i++)
			bouton[i] = new Boolean(false);
	}

	public void update(World_extends world) {

	}

	public void setReset(int i) {
		if( ! bouton[i])
			bouton[i] = true;
		else
			bouton[i] = false;
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

	public Code getCode() {
		Code code = new Code();
		code.setCode(bouton);
		return code;
	}

	
}