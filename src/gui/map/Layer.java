package gui.map;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import core.Rand;
import gui.Pattern;
import monde.GestionId;

public class Layer {

	private String name;
	private int height = 20;
	private int width = 20;
	private Mode mode;
	private int textureId = 1;
	private int populate;
	private Tileset tileset;
	private Pattern pattern = null;
	private int[] data;
	
	private ArrayList<Tileset> ArrayTileset = new ArrayList<Tileset>();

	public ArrayList<Tileset> getArrayTileset() {
		return ArrayTileset;
	}

	public void addArrayTileset(GestionId gid, Tileset tileset) {
		tileset.setFirstgid(gid.getIdAndAddCount(tileset.getCount()));
		this.ArrayTileset.add(tileset);
	}
	
	public Layer(String name, Mode mode, Tileset tileset){
		this.name = name;
		this.mode = mode;
		this.tileset = tileset;
		this.textureId = 1 + tileset.getFirstgid();
	}
	
	public Layer(String name, Mode mode){
		this.name = name;
		this.mode = mode;
		this.textureId = 0;
	}
	
	public void setPattern(Pattern pattern){
		this.pattern = pattern;
	}
	
	public void isParsem�(int pop){
		mode = Mode.parsem�;
		this.populate = pop;
	}
	
	public Tileset getTileset(){
		return tileset;
	}
	
	public void setPopulate(int populate){
		this.populate = populate;
	}
	
	public void setHW(int height, int width){
		this.height = height;
		this.width = width;
		this.setData(new int[height * width]);
	}
	
	public void setTextureId(int textureId){
		this.textureId = textureId;
	}
	
	public Element getXml(Document doc){
		Element layer = doc.createElement("layer");
		layer.setAttribute("name", name);
		layer.setAttribute("width", height+"");
		layer.setAttribute("height", width+"");
		Element data = doc.createElement("data");
		data.setAttribute("encoding", "base64");
		data.setAttribute("compression", "gzip");
		data.setTextContent(this.defData());
		layer.appendChild(data);
		return layer;
	}
	
	private int[] plain(){
		int[] data = new int[width * height];
		
		for(int i=0; i<data.length; i++){
				data[i] = this.textureId;
		}
		return data;
	}
	
	private int[] parsem�(){
		int[] data = null;
		if(pattern == null){
		data = new int[width * height];
		int index = 0;
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				if(Rand.entier(0, 100) < populate)
					data[index++] = this.textureId;
				else
					data[index++] = 0;
			}
		}
		}else{
			data = new int[width * height];
			int index = 0;
			for(int i=0; i<width; i++){
				for(int j=0; j<height; j++){
					if(Rand.entier(0, 100) < populate)
						data = this.mergeMatrice(data, pattern, j, i);
				}
			}
		}
		
		return data;
	}
	
	private int[] mergeMatrice(int[] data, Pattern pattern, int x, int y) {
		int index = 0;
		int[] out = new int[20 * 20];
		int[][] pat = pattern.getPattern();
		int pw = pat[0].length;
		int ph = pat.length;
		int lil = 0;
		int lol = 0;
		Boolean ok = false;
		x--;
		y--;

		System.out.println("wh = " + pw + " | " + ph);
		
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				out[index] = data[index];
				if (x + lol == j && y + lil == i && ok == false) {
					
					out[index] = pat[lil][lol++];
					if(lol+1 > pw){
						lol = 0;
						lil++;
						if(lil+1 > ph)
							ok = true;
						
					}
					
				}
				index++;
			}
		}
		return out;
	}
	
	private String defData(){
		int[] data = null;
		
		if(this.mode == Mode.plain)
			data = plain();
		else if (this.mode == Mode.parsem�)
			data = parsem�();
		else
			data = this.data;
		
		 ByteBuffer byteBuffer = ByteBuffer.allocate(data.length * 4);
		 byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
	     IntBuffer intBuffer = byteBuffer.asIntBuffer();
	     intBuffer.put(data);
	     byte[] array = byteBuffer.array();

	     try {
			array = this.compress(array);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     	System.out.println("gzip = " +array.length );
	    
	    return Base64.getEncoder().encodeToString(array);
	}
	
	
	private byte[] decompress(byte[] contentBytes) throws IOException{


		java.util.zip.Inflater inf = new java.util.zip.Inflater();
		java.io.ByteArrayInputStream bytein = new java.io.ByteArrayInputStream(contentBytes);
		java.util.zip.GZIPInputStream gzin = new java.util.zip.GZIPInputStream(bytein);
		java.io.ByteArrayOutputStream byteout = new java.io.ByteArrayOutputStream();

		int res = 0;
		byte buf[] = new byte[1024];
		while (res >= 0) {
		    res = gzin.read(buf, 0, buf.length);
		    if (res > 0) {
		        byteout.write(buf, 0, res);
		    }
		}
		return byteout.toByteArray();


		    }
	
	private byte[] compress(byte[] input) throws IOException{

		 try (ByteArrayOutputStream bout = new ByteArrayOutputStream();
	                GZIPOutputStream gzipper = new GZIPOutputStream(bout))
	        {
	            gzipper.write(input, 0, input.length);
	            gzipper.close();

	            return bout.toByteArray();
	        }

	}
	
	public int[] getData() {
		return data;
	}

	public void setData(int[] data) {
		this.data = data;
	}
	
	public void setDataFeuillage(int[] data){
		this.data = data;
	}

	public enum Mode{
		
		none, plain, parsem�;
		
	}
	
}
