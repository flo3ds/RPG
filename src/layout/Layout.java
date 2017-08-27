package layout;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import core.Stack;
import gameData.GameData;
import graphicEngine.SpriteBatch;
import graphicEngine.Texture;
import graphicEngine.TextureManager;
import graphicEngine.Util;
import objects.Arbre;
import objects.Chest;
import objects.Object;
import objects.Pierre;
import perso.Inventaire;
import perso.Personnage;

public class Layout {
	

	private List<Container> containers = new ArrayList<Container>();
	public static final GameData REGISTRY = GameData.getMain();
	private String name;
	private Stack clicked = null;
	private int m_x;
	private int m_y;
	private int saveI = -1;
	private short texId;
	
	public Layout (String name)
	{
		this.name = name;
		if( ! TextureManager.getInstance().exist("layout-"+name)){
			try {
				texId = TextureManager.getInstance().register("layout-"+name, new Texture(Util.getResource("res/layout/"+name+".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		texId = TextureManager.getInstance().getIdByName(name);
	}
	
	public Container addContainer(Stack stack) {
		Container con = new Container(stack);
		containers.add(con);
		return con;
	}
	
	public void draw (SpriteBatch batch, int x, int y) {
	}
	
	public void draw (SpriteBatch batch, int x, int y, Inventaire inv) {
	}
	
	public void update () {
		
	}
	
	public void click (int x, int y, Personnage perso) {
		if (Mouse.isButtonDown(0) && clicked == null) {
			for(int i=0; i<containers.size(); i++) {
				if( containers.get(i).clicked(x, y)) {
					Stack item = containers.get(i).getStack();
					if (item != null) {
						clicked = new Stack(item);
						item.reset();
						saveI = i;
				}}
			}
			
			}else{
				m_x = x;
				m_y = y;
				if (Mouse.isButtonDown(0) == false && clicked != null){
					for(int i=0; i<containers.size(); i++) {
						if( containers.get(i).clicked(x, y)) {
							if(containers.get(i).getStack().empty()){
								containers.get(i).setStack(clicked);
							}else{
								if(containers.get(i).getStack().getId().equals(clicked.getId())){
									if(containers.get(i).getStack().getNombre() + clicked.getNombre() < Stack.MAXSIZE)
										containers.get(i).getStack().setNombre(containers.get(i).getStack().getNombre() + clicked.getNombre());
									else{
										containers.get(i).getStack().setNombre(Stack.MAXSIZE);
										containers.get(saveI).getStack().setNombre(containers.get(i).getStack().getNombre() + clicked.getNombre() - Stack.MAXSIZE);
									}
								}else{
								containers.get(saveI).setStack(containers.get(i).getStack());
								containers.get(i).setStack(clicked);
								}
							}
						}
						}
					
					clicked = null;
					}
				}
	}
	
	public void draw_extends(SpriteBatch batch, float x, float y) {
		//System.out.println("layout : "+ name);
			
			
		batch.draw(TextureManager.getInstance().getTexture(texId), x, y);

	}
	
	public void draw_post_extends(SpriteBatch batch) {
		//System.out.println("layout : "+ name);
		if(clicked != null)
			if(clicked.getId() != "")
				batch.draw(TextureManager.getInstance().getTexture(clicked.getTexId()), m_x, m_y);
	}
	
	
	
	
	
	
	
}
