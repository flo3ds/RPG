package objects;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.Sys;

import core.Inventable;
import gameData.GameData;
import graphicEngine.ITexture;
import graphicEngine.Texture;
import graphicEngine.TextureManager;
import graphicEngine.Util;
import graphicEngine.Vector2D;
import graphicEngine.world.World;
import perso.Personnage;
import tileEntity.ITileEntityProvider;
import tileEntity.TileEntity;


public class Object extends ObjectState implements Serializable, Inventable, Placable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -4894911588146667266L;

	public static final GameData REGISTRY = GameData.getMain();

	private boolean colisable = true;

	private String name;
	private int dx = 0;
	private int dy = 0;

	public Object(String name) {
		this.name = name;
		setState(0);

		try {
			if (!TextureManager.getInstance().exist(name)) {
				Texture tex;
				tex = new Texture(Util.getResource("res/" + name + ".png"));
				TextureManager.getInstance().register(name, tex);
			}
		} catch (IOException e) {
			Sys.alert("Error", "Texture object not load : " + name);
			e.printStackTrace();
			System.exit(0);
		}

	}

	public void addState(String key) {
		try {
			addStateExtends(key);
			if (!TextureManager.getInstance().exist(name + "_" + key)) {
				Texture tex;
				tex = new Texture(Util.getResource("res/" + name + "_" + key + ".png"));
				TextureManager.getInstance().register(name + "_" + key, tex);
			}
		} catch (IOException e) {
			Sys.alert("Error", "Texture object not load : " + name + "_" + key);
			e.printStackTrace();
			System.exit(0);
		}
	}

	public String getName() {
		return name;
	}

	public String getTex() {
		return name + ((getActivStateId() == 0) ? "" : "_" + getActivStateName());
	}

	public void click(Personnage perso, World world, Vector2D pos_click) {
		System.out.println("click on " + name);
	}

	public static void registerBlocks() {
		// registerBlock(0, "air", (new BlockAir()).setUnlocalizedName("air"));
		registerBlock(1, new Pierre());
		registerBlock(2, new Arbre());
		registerBlock(3, new Chest());
		registerBlock(4, new Scirie());
	}

	private static void registerBlock(int id, Object object_) {
		REGISTRY.registerObject(id, object_);
	}

	public int getDx() {
		return dx;
	}

	public void setDecalageSpriteX(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDecalageSpriteY(int dy) {
		this.dy = dy;
	}

	public boolean isColisable() {
		return colisable;
	}

	public void setColisable(boolean colisable) {
		this.colisable = colisable;
	}
	
	private boolean isTileProvider = this instanceof ITileEntityProvider;
    /**
     * Called throughout the code as a replacement for block instanceof BlockContainer
     * Moving this to the Block base class allows for mods that wish to extend vanilla
     * blocks, and also want to have a tile entity on that block, may.
     *
     * Return true from this function to specify this block has a tile entity.
     *
     * @param state State of the current block
     * @return True if block has a tile entity, false otherwise
     */
    public boolean hasTileEntity()
    {
        return isTileProvider;
    }

	
    public TileEntity createNewTileEntity()
    {
        return null;
    }

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return name;
	}

}
