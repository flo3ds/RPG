package objects;


import graphicEngine.Vector2D;
import graphicEngine.world.World;
import graphicEngine.world.Worldable;
import layout.Layout_chest;
import layout.Layout_scirie;
import perso.Personnage;
import tileEntity.ITileEntityProvider;
import tileEntity.TileEntity;
import tileEntity.TileEntityChest;
import tileEntity.TileEntityScirie;

public class Scirie extends Object implements ITileEntityProvider {
	
	public Scirie() {
		super("scirie");
		addState("run");
		
	}
	
	public void click(Personnage perso, Worldable world, Vector2D pos_click) {
		if( getActivStateId() == 0){
			setState("run");
			perso.getGUI().openLayout(new Layout_scirie((TileEntityScirie)world.getTileEntity(pos_click), perso.inv));
		}else
			setState(0);
	}

    public TileEntity createNewTileEntity()
    {
        return new TileEntityScirie();
    }
	
	
}
