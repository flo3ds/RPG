package tileEntity;

import core.Stack;
import graphicEngine.Vector2D;
import graphicEngine.world.World_extends;
import objects.CableE;

public class TileEntityCableE extends TileEntity implements ITileEntityElectriqueOut, ITileEntityElectriqueIn {

	Vector2D pos;
	CableE cable;
	public Stack stack = new Stack();
	private int delay = 35;
	private int delta_delay = delay;
	private int buffer = 0;

	public TileEntityCableE(int x, int y, CableE cable) {
		this.pos = new Vector2D(x, y);
		this.cable = cable;
	}

	public void update(World_extends world) {
		String state = "";
		if (world.getTileEntity(pos.top()) instanceof ITileEntityElectriqueOut
				|| world.getTileEntity(pos.top()) instanceof ITileEntityElectriqueIn) {
			state += "T";
			if (world.getTileEntity(pos.top()) instanceof ITileEntityElectriqueOut) {
				if (buffer < getBufferMax()) {
					buffer += ((ITileEntityElectriqueOut) world.getTileEntity(pos.top())).suckBuffer(getDebit());
				}
			}
		}
		if (world.getTileEntity(pos.bottom()) instanceof ITileEntityElectriqueOut
				|| world.getTileEntity(pos.bottom()) instanceof ITileEntityElectriqueIn) {
			state += "B";
			if (world.getTileEntity(pos.bottom()) instanceof ITileEntityElectriqueOut) {
				if (buffer < getBufferMax()) {
					buffer += ((ITileEntityElectriqueOut) world.getTileEntity(pos.bottom())).suckBuffer(getDebit());
				}
			}
		}
		if (world.getTileEntity(pos.left()) instanceof ITileEntityElectriqueOut
				|| world.getTileEntity(pos.left()) instanceof ITileEntityElectriqueIn) {
			state += "L";
			if (world.getTileEntity(pos.left()) instanceof ITileEntityElectriqueOut) {
				if (buffer < getBufferMax()) {
					buffer += ((ITileEntityElectriqueOut) world.getTileEntity(pos.left())).suckBuffer(getDebit());
				}
			}
		}
		if (world.getTileEntity(pos.right()) instanceof ITileEntityElectriqueOut
				|| world.getTileEntity(pos.right()) instanceof ITileEntityElectriqueIn) {
			state += "R";
			if (world.getTileEntity(pos.right()) instanceof ITileEntityElectriqueOut) {
				if (buffer < getBufferMax()) {
					buffer += ((ITileEntityElectriqueOut) world.getTileEntity(pos.right())).suckBuffer(getDebit());
				}
			}
		}

		if (state != "")
			cable.setState(state);
	}

	@Override
	public int getBufferMax() {
		// TODO Auto-generated method stub
		return 5000;
	}

	@Override
	public int getPower() {
		return getDebit();
	}

	@Override
	public int suckBuffer(int power) {
		if (buffer - power >= 0) {
			buffer = -power;
			return power;
		} else {
			int saveBuffer = buffer;
			buffer = 0;
			return saveBuffer;
		}
	}

	@Override
	public int getDebit() {
		// TODO Auto-generated method stub
		return 512;
	}

	public int getBuffer() {
		// TODO Auto-generated method stub
		return buffer;
	}
}
