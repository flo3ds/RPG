package tileEntity;

import graphicEngine.world.World;

public interface ITileEntityProvider {
    TileEntity createNewTileEntity(int x, int y);

}
