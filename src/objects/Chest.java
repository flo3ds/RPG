package objects;


import graphicEngine.Vector2D;
import graphicEngine.world.World;
import graphicEngine.world.Worldable;
import init.Tools;
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
	
	public void click(Personnage perso, Worldable world, Vector2D pos_click) {
		if(perso.getGUI().getCurrentItem().compareID(Tools.PICK))
			breakObj(perso, world, pos_click);
		else
		perso.getGUI().openLayout(new Layout_chest((TileEntityChest)world.getTileEntity(pos_click), perso));
	}

    public TileEntity createNewTileEntity(int x, int y)
    {
        return new TileEntityChest();
    }
	
	
}
