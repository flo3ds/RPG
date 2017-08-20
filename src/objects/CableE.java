package objects;


import graphicEngine.Vector2D;
import graphicEngine.world.World;
import layout.Layout_chest;
import perso.Personnage;
import tileEntity.ITileEntityProvider;
import tileEntity.TileEntity;
import tileEntity.TileEntityCable;
import tileEntity.TileEntityCableE;
import tileEntity.TileEntityChest;

public class CableE extends Object implements ITileEntityProvider {
	
	public CableE() {
		super("cable/cableE");
		addState("L");
		addState("LR");
		addState("R");
		addState("T");
		addState("TB");
		addState("B");
		addState("TBL");
		addState("TBR");
		addState("TBLR");
		addState("TL");
		addState("TR");
		addState("TLR");
		addState("BL");
		addState("BR");
		addState("BLR");
	}
	
	public void click(Personnage perso, World world, Vector2D pos_click) {
		
	}

    public TileEntity createNewTileEntity(int x, int y)
    {
        return new TileEntityCableE(x, y, this);
    }
	
	
}
