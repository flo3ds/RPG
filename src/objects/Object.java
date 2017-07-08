package objects;

import java.util.Map;

import gameData.GameData;
import perso.Personnage;


public class Object extends ObjectState {

    public static final GameData REGISTRY = GameData.getMain();
    
    private boolean colisable = true;
    
    private String name;
    private int dx = 0;
    private int dy = 0;
    
    public Object(String name) {
    	this.name = name;
    	setState(0);
    }
    
    public String getName() {
    	return name;
    }
    
    public String getTex() {
    	return name + ((getState() == 0)?"":"_"+getState());
    }
    
    public void click (Personnage perso) {
    	System.out.println("click on " + name);
    }
	
	
	
	 public static void registerBlocks()
	    {
	        //registerBlock(0, "air", (new BlockAir()).setUnlocalizedName("air"));
	        registerBlock(1, new Pierre());
	        registerBlock(2, new Arbre());
	    }
	
	
	
	
	 private static void registerBlock(int id, Object object_)
	    {
	        REGISTRY.registerObject(id, object_);
	    }

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public boolean isColisable() {
		return colisable;
	}

	public void setColisable(boolean colisable) {
		this.colisable = colisable;
	}
	    
	    
}
