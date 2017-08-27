package objects;


import graphicEngine.Vector2D;
import graphicEngine.world.World;
import graphicEngine.world.Worldable;
import init.Tools;
import layout.Layout_chest;
import layout.Layout_furnace;
import perso.Personnage;
import tileEntity.ITileEntityProvider;
import tileEntity.TileEntity;
import tileEntity.TileEntityChest;
import tileEntity.TileEntityFurnace;
import tileEntity.TileEntityGenerator;

public class Generator extends Object implements ITileEntityProvider {
	
	public Generator() {
		super("generator");
		addState("build");	
	}
	
	public void click(Personnage perso, Worldable world, Vector2D pos_click) {
		if(perso.getGUI().getCurrentItem().compareID(Tools.PICK))
			breakObj(perso, world, pos_click);
		else
		System.out.println("buffer :" + ((TileEntityGenerator)world.getTileEntity(pos_click)).getBuffer());
	}

    public TileEntity createNewTileEntity(int x, int y)
    {
        return new TileEntityGenerator(this, x, y);
    }
	
	
}
