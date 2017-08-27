package objects;


import graphicEngine.Vector2D;
import graphicEngine.world.World;
import graphicEngine.world.Worldable;
import init.Tools;
import layout.Layout_chest;
import perso.Personnage;
import tileEntity.ITileEntityProvider;
import tileEntity.TileEntity;
import tileEntity.TileEntityCable;
import tileEntity.TileEntityCableE;
import tileEntity.TileEntityChest;
import tileEntity.TileEntityGenerator;

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
	
	public void click(Personnage perso, Worldable world, Vector2D pos_click) {
		if(perso.getGUI().getCurrentItem().compareID(Tools.PICK))
			breakObj(perso, world, pos_click);
		else
		System.out.println("buffer cable :" + ((TileEntityCableE)world.getTileEntity(pos_click)).getBuffer());
	}

    public TileEntity createNewTileEntity(int x, int y)
    {
        return new TileEntityCableE(x, y, this);
    }
	
	
}
