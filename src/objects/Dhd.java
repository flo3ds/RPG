package objects;


import graphicEngine.Vector2D;
import graphicEngine.world.World;
import graphicEngine.world.Worldable;
import init.Tools;
import layout.Layout_chest;
import layout.Layout_dhd;
import layout.Layout_scirie;
import perso.Personnage;
import tileEntity.ITileEntityProvider;
import tileEntity.TileEntity;
import tileEntity.TileEntityChest;
import tileEntity.TileEntityDhd;
import tileEntity.TileEntityScirie;

public class Dhd extends Object implements ITileEntityProvider {
	
	public Dhd() {
		super("dhd");		
	}
	
	public void click(Personnage perso, Worldable world, Vector2D pos_click) {
			perso.getGUI().openLayout(new Layout_dhd((TileEntityDhd)world.getTileEntity(pos_click)));
	}

    public TileEntity createNewTileEntity(int x, int y)
    {
        return new TileEntityDhd();
    }
	
	
}
