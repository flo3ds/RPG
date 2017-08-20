package objects;


import graphicEngine.Vector2D;
import graphicEngine.world.World;
import graphicEngine.world.Worldable;
import layout.Layout_chest;
import layout.Layout_crafting;
import perso.Personnage;
import tileEntity.ITileEntityProvider;
import tileEntity.TileEntity;
import tileEntity.TileEntityChest;
import tileEntity.TileEntityCrafting_station;

public class Crafting_station extends Object implements ITileEntityProvider {
	
	public Crafting_station() {
		super("crafting_station");
		
	}
	
	public void click(Personnage perso, Worldable world, Vector2D pos_click) {
			perso.getGUI().openLayout(new Layout_crafting((TileEntityCrafting_station)world.getTileEntity(pos_click), perso.inv));
	}

    public TileEntity createNewTileEntity(int x, int y)
    {
        return new TileEntityCrafting_station();
    }
	
	
}
