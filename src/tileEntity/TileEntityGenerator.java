package tileEntity;

import graphicEngine.Vector2D;
import graphicEngine.world.World_extends;
import objects.Generator;

public class TileEntityGenerator extends TileEntity implements ITileEntityElectriqueOut {

	private Vector2D pos;
	private Generator generator;
	private Boolean build = false;
	private int buffer = 0;

	public TileEntityGenerator(Generator generator, int x, int y) {
		pos = new Vector2D(x, y);
		this.generator = generator;
	}

	public void update(World_extends world) {
		multiBlock(world);
		if (build) {
			TileEntityFurnace furnace = (TileEntityFurnace) world.getTileEntity(pos.bottom());
			if (furnace.getFuel() > 0)
				if (buffer < getBufferMax())
					buffer += getPower();
		}
	}

	private void multiBlock(World_extends world) {
		if (world.getTileEntity(pos.bottom()) instanceof TileEntityFurnace) {
			generator.setState("build");
			build = true;
		} else {
			generator.setState(0);
			build = false;
		}
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
	public int getPower() {
		// TODO Auto-generated method stub
		return 80;
	}

	@Override
	public int getBufferMax() {
		// TODO Auto-generated method stub
		return 5000000;
	}

	public int getBuffer() {
		return buffer;
	}

}
