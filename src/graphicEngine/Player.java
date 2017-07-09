package graphicEngine;

import java.io.IOException;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import graphicEngine.world.World;
import init.Layouts;
import init.Objects;
import layout.Layout;
import layout.Layout_sac;
import objects.Object;

public class Player {

	private TextureRegion t_player;
	private Vector2D pos;
	private float speed = 3f;
	private int orientation = 0;

	private Animation[] anim = new Animation[8];
	private boolean moving = false;

	private boolean layout_state = false;
	private Layout layout = null;

	public Player() {
		try {
			Texture tex = new Texture(Util.getResource("res/persos.png"), Texture.NEAREST);

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
	}

	public void render(SpriteBatch batch) {
		batch.draw(anim[orientation + (moving ? 4 : 0)].getTexture(), pos.x - 32, pos.y - 55);
		if (layout_state)
			layout.draw(batch, (int) pos.x, (int) pos.y);

	}

	public Vector2D getPos() {
		return pos;
	}

	public void update(World world, float panX, float panY) {
		moving = false;
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
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			world.save();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_1)) {
			int x_m = (int) (Mouse.getX() - panX);
			int y_m = (int) (Mouse.getY() - (480 - panY));
			y_m *= -1;
			try {
				world.placeObject(x_m, y_m, Objects.CHEST.getClass().newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			if (!layout_state)
				openLayout(Layouts.SAC);
		}
	}

	public void openLayout(Layout layout) {
		layout_state = true;
		this.layout = layout;
	}

	public void setPos(Vector2D pos) {
		this.pos = pos;
	}

	public float getSpeed() {
		return this.speed;
	}

}
