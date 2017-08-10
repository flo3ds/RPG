package graphicEngine;

import java.io.IOException;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import core.Inventable;
import core.Stack;
import graphicEngine.world.World;
import graphicEngine.world.Worldable;
import init.Objects;
import init.Tools;
import layout.Container;
import layout.Layout;
import layout.Layout_sac;
import objects.Object;
import perso.Personnage;

public class Player {

	private TextureRegion t_player;
	private Vector2D pos;
	private float speed = 3f;
	private int orientation = 0;

	private Animation[] anim = new Animation[8];
	private boolean moving = false;

	private boolean layout_state = false;
	private Layout layout = null;

	private Container inv_bar[] = new Container[9];
	private Stack item[] = new Stack[9];
	private int selector = 0;
	private Texture selector_tex;
	
	private boolean key_down = false;
	private int wheel;

	public Player() {
		try {
			Texture tex = new Texture(Util.getResource("res/persos.png"), Texture.NEAREST);
			selector_tex = new Texture(Util.getResource("res/selector.png"), Texture.NEAREST);

			anim[0] = new Animation(tex, 64, 1, 0, 0);
			anim[1] = new Animation(tex, 64, 1, 1, 0);
			anim[2] = new Animation(tex, 64, 1, 2, 0);
			anim[3] = new Animation(tex, 64, 1, 3, 0);

			anim[4] = new Animation(tex, 64, 9, 0, 4);
			anim[5] = new Animation(tex, 64, 9, 1, 4);
			anim[6] = new Animation(tex, 64, 9, 2, 4);
			anim[7] = new Animation(tex, 64, 9, 3, 4);
		} catch (IOException e) {
			Sys.alert("Error", "Player Texture not Load");
			e.printStackTrace();
			System.exit(0);
		}
		pos = new Vector2D(0, 0);
		for(int i=0; i < 9; i++){
			inv_bar[i] = new Container();
			item[i] = null;
		}
		wheel = Mouse.getDWheel();
		
	}
	
	public void postInit(){
		item[0] = new Stack(Objects.CHEST, 1);
		item[1] = new Stack(Objects.SCIRIE, 1);
		item[2] = new Stack(Objects.CRAFTING_STATION, 1);
		item[3] = new Stack(Objects.MUR_BOIS, 1);
		item[4] = new Stack(Objects.DOOR, 1);
		item[5] = new Stack(Tools.AXE, 1);
	}

	public void render(SpriteBatch batch, Personnage perso) {
		batch.draw(anim[orientation + (moving ? 4 : 0)].getTexture(), pos.x - 32, pos.y - 55);
	}
	
	public void renderLayout(SpriteBatch batch, Personnage perso) {
		if (layout_state)
			layout.draw(batch, (int) pos.x, (int) pos.y, perso.inv);
		else
			for(int i=0; i < 9; i++){
				inv_bar[i].draw(batch, (int)pos.x +(i * 40) - 200, (int)pos.y+200, item[i]);
				if (i == selector)
					batch.draw(selector_tex, (int)pos.x +(i * 40) - 200, (int)pos.y+200);
			}
			}
	

	public Vector2D getPos() {
		return pos;
	}

	public void update(Worldable world, float panX, float panY) {
		wheel = Mouse.getDWheel();
		moving = false;
		if( ! layout_state) {
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			Object col = world.getObject((int) pos.x + (int) getSpeed() + 10, (int) pos.y);
			if (col != null) {
				if (!col.isColisable())
					pos.x += getSpeed();
			} else
				pos.x += getSpeed();
			orientation = 3;
			moving = true;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			Object col = world.getObject((int) pos.x - (int) getSpeed() - 10, (int) pos.y);
			if (col != null) {
				if (!col.isColisable())
					pos.x -= getSpeed();
			} else
				pos.x -= getSpeed();
			orientation = 1;
			moving = true;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			Object col = world.getObject((int) pos.x, (int) pos.y + (int) getSpeed());
			if (col != null) {
				if (!col.isColisable())
					pos.y += getSpeed();
			} else
				pos.y += getSpeed();
			orientation = 2;
			moving = true;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
			Object col = world.getObject((int) pos.x, (int) pos.y - (int) getSpeed() - 10);
			if (col != null) {
				if (!col.isColisable())
					pos.y -= getSpeed();
			} else
				pos.y -= getSpeed();
			orientation = 0;
			moving = true;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			int x_m = (int) (Mouse.getX() - panX);
			int y_m = (int) (Mouse.getY() - (600 - panY));
			y_m *= -1;
			if(y_m < 0)
				y_m -= 32;
			if(x_m < 0)
				x_m -= 32;
			System.out.print("pos = "+x_m/32+" : "+y_m/32+"\n");

		}
		if (0 < wheel) {
			selector ++;
			if (selector > 8)
				selector = 0;
		}
		if (0 > wheel) {
			selector --;
			if(selector < 0)
				selector = 8;
		}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			world.save();
		}
		

		if (Keyboard.isKeyDown(Keyboard.KEY_E) && key_down == false) {
			key_down = true;
			
		}
		if ( ! Keyboard.isKeyDown(Keyboard.KEY_E) && key_down == true) {
			key_down = false;
			if (!layout_state){
				try {
					openLayout(new Layout_sac());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else
				closeLayout();
			
		}
		
	}
	
	public void closeLayout() {
		layout_state = false;
		layout = null;
	}
	
	public Stack getCurrentItem() {
		return item[selector];
	}

	public void openLayout(Layout layout) {
		layout_state = true;
		this.layout = layout;
	}
	
	public Layout getLayout() {
		return layout;
	}

	public void setPos(Vector2D pos) {
		this.pos = pos;
	}

	public float getSpeed() {
		return this.speed;
	}
	
	public boolean getLayoutState() {
		return layout_state;
	}
	
	public void layoutUpdate () {
		if(layout_state)
			layout.update();
	}

}
