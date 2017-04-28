package monde;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

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
	private Color color = null;
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
	
	public void pngProcess(String path){
		BufferedImage loadImg = null;
		try {
			loadImg = ImageIO.read(new File("sprites/tileset/" + ((Tileset)this.getTileset()).getFile()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(color != null)
		tint(loadImg, color);
		
		File outputfile = new File(path+layer.getTileset().getFile());
		try {
			ImageIO.write(loadImg, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setColor(Color color){
		this.color = color;
	}

	public static void tint(BufferedImage image, Color color) {
		    for (int x = 0; x < image.getWidth(); x++) {
		        for (int y = 0; y < image.getHeight(); y++) {
		            Color pixelColor = new Color(image.getRGB(x, y), true);
		            int r = (pixelColor.getRed() + color.getRed()) / 2;
		            int g = (pixelColor.getGreen() + color.getGreen()) / 2;
		            int b = (pixelColor.getBlue() + color.getBlue()) / 2;
		            int a = pixelColor.getAlpha();
		            int rgba = (a << 24) | (r << 16) | (g << 8) | b;
		            image.setRGB(x, y, rgba);
		        }
		    }
		}

	
	
}
