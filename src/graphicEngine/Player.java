package graphicEngine;

import java.io.IOException;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;

import graphicEngine.world.World;
import objects.Object;


public class Player {

	private TextureRegion t_player;
	private Vector2D pos;
	private float speed = 4f;
	
	private Animation anim;
	
	public Player() {
		try {
			Texture tex = new Texture(Util.getResource("res/persos.png"), Texture.NEAREST);
			anim = new Animation(tex, 64, 3, 0, 10);
		} catch (IOException e) {
			Sys.alert("Error", "Player Texture not Load");
			e.printStackTrace();
			System.exit(0);
		}
		pos = new Vector2D(0, 0);
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(anim.getTexture(), pos.x-32, pos.y-55);
	}

	public Vector2D getPos() {
		return pos;
	}
	
	public void update(World world) {

		if (Keyboard.isKeyDown(Keyboard.KEY_D)){
			Object col = world.getObject((int)pos.x + (int)getSpeed()+10, (int)pos.y);
			if ( col != null){
				if( ! col.isColisable())
					pos.x += getSpeed();
			}else
				pos.x += getSpeed();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)){
			Object col = world.getObject((int)pos.x - (int)getSpeed()-10, (int)pos.y);
		if ( col != null){
			if( ! col.isColisable())
				pos.x -= getSpeed();
		}else
			pos.x -= getSpeed();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)){
			Object col = world.getObject((int)pos.x, (int)pos.y + (int)getSpeed() );
			if ( col != null){
				if( ! col.isColisable())
					pos.y += getSpeed();
			}else
				pos.y += getSpeed();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Z)){
			Object col = world.getObject((int)pos.x, (int)pos.y - (int)getSpeed() - 10);
			if ( col != null){
				if( ! col.isColisable())
					pos.y -= getSpeed();
			}else
				pos.y -= getSpeed();
		}
		
		anim.update();
	}

	public void setPos(Vector2D pos) {
		this.pos = pos;
	}
	
	public float getSpeed(){
		return this.speed;
	}
	
	
	
}
