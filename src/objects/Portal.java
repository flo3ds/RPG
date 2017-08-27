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
import tileEntity.TileEntityPortal;
import tileEntity.TileEntityScirie;

public class Portal extends Object implements ITileEntityProvider {
	
	public Portal() {
		super("portal");		
	}
	
	public void click(Personnage perso, Worldable world, Vector2D pos_click) {
		TileEntityDhd dhd = ((TileEntityDhd)world.getTileEntity(pos_click.top())); 
			perso.teleport(dhd.getCode().getId());
	}

    public TileEntity createNewTileEntity(int x, int y)
    {
        return new TileEntityPortal();
    }
	
	
}
