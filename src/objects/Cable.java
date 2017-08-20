package objects;


import graphicEngine.Vector2D;
import graphicEngine.world.World;
import layout.Layout_chest;
import perso.Personnage;
import tileEntity.ITileEntityProvider;
import tileEntity.TileEntity;
import tileEntity.TileEntityCable;
import tileEntity.TileEntityChest;

public class Cable extends Object implements ITileEntityProvider {
	
	public Cable() {
		super("cable/cable");
		addState("L");
		addState("LR");
		addState("R");
		
	}
	
	public void click(Personnage perso, World world, Vector2D pos_click) {
		
	}

    public TileEntity createNewTileEntity(int x, int y)
    {
        return new TileEntityCable(x, y, this);
    }
	
	
}
