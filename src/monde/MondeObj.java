package monde;

import java.util.ArrayList;

import gui.Pattern;
import gui.map.Layer;
import gui.map.MultiTileset_I;
import gui.map.Tileset;


/**
 * Classe abstraite qui comporte tous le code nécéssaire a l'affichage d'objet lors de la generation d'un monde.
 */
public abstract class MondeObj {

	/** The layer. */
	private Layer layer;
	
	/** The firstgid. */
	private int firstgid;
	
	/**
	 * Instantiates a new monde obj.
	 *
	 * @param GestionId
	 * @param Tileset 
	 */
	public MondeObj(GestionId gid, Tileset tileset){
		firstgid = gid.getIdAndAddCount(tileset.getCount());
		tileset.setFirstgid(this.firstgid);
		this.layer = new Layer(tileset.getName(), Layer.Mode.plain, tileset);
	}
	
	public MondeObj(String name){
		
		this.layer = new Layer(name, Layer.Mode.plain);
	}
	
	public void addTileset(GestionId gid, Tileset tileset){
		layer.addArrayTileset(gid, tileset);
	}
	
	public void setPattern(Pattern pattern){
		layer.setPattern(pattern);
	}
	
	/**
	 * Passe le mode affichage de l'objet en mode parcemé avec le pourcentage en parametre.
	 *
	 * @param int ; pourcentage pour le rand position.
	 */
	public void setParsemé(int pop){
		this.layer.isParsemé(pop);
	}
	
	/**
	 * Defini quelle tuile sera affiché.
	 *
	 * @param int : resultat du rand ou de la texture choisi.
	 */
	public void setTexture(int idTexture){
		this.layer.setTextureId(idTexture+this.getFirstgid());		
	}
	
	/**
	 * Gets the layer.
	 *
	 * @return the layer
	 */
	public Layer getLayer(){
		return layer;
	}

	
	/**
	 * Gets the tileset.
	 *
	 * @return the tileset
	 */
	public Object getTileset(){
		return layer.getTileset();
	}

	
	/**
	 * Gets the firstgid.
	 *
	 * @return the firstgid
	 */
	public int getFirstgid() {
		return firstgid;
	}

	/**
	 * Sets the firstgid.
	 *
	 * @param firstgid the new firstgid
	 */
	public void setFirstgid(int firstgid) {
		this.firstgid = firstgid;
	}

	
	
}
