package objects;


import graphicEngine.Vector2D;
import graphicEngine.world.World;
import layout.Layout_chest;
import perso.Personnage;
import tileEntity.ITileEntityProvider;
import tileEntity.TileEntity;
import tileEntity.TileEntityChest;

public class Chest extends Object implements ITileEntityProvider {
	
	public Chest() {
		super("chest");
		addState("open");
		
	}
	
	public void click(Personnage perso, World world, Vector2D pos_click) {
		System.out.print("getclick = "+pos_click.x+" : "+pos_click.y+"\n");

		if( getActivStateId() == 0){
			setState("open");
			perso.getGUI().openLayout(new Layout_chest((TileEntityChest)world.getTileEntity(pos_click), perso.inv));
		}else
			setState(0);
	}

    public TileEntity createNewTileEntity()
    {
        return new TileEntityChest();
    }
	
	
}
