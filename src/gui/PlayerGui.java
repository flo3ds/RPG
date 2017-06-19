package gui;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import action.Position;
import perso.Personnage;

public class PlayerGui {

	private float x = 1300, y = 1400;
	private int direction = 0;
	private boolean moving = false;
	private Animation[] animations = new Animation[8];

	public PlayerGui() {

	}

	public void init() throws SlickException {
		SpriteSheet spriteSheet = new SpriteSheet("sprites/persos.png", 64, 64);
		this.animations[0] = loadAnimation(spriteSheet, 0, 1, 0);
		this.animations[1] = loadAnimation(spriteSheet, 0, 1, 1);
		this.animations[2] = loadAnimation(spriteSheet, 0, 1, 2);
		this.animations[3] = loadAnimation(spriteSheet, 0, 1, 3);
		this.animations[4] = loadAnimation(spriteSheet, 1, 9, 0);
		this.animations[5] = loadAnimation(spriteSheet, 1, 9, 1);
		this.animations[6] = loadAnimation(spriteSheet, 1, 9, 2);
		this.animations[7] = loadAnimation(spriteSheet, 1, 9, 3);
	}

	private Animation loadAnimation(SpriteSheet spriteSheet, int startX, int endX, int y) {
		Animation animation = new Animation();
		for (int x = startX; x < endX; x++) {
			animation.addFrame(spriteSheet.getSprite(x, y), 100);
		}
		return animation;
	}

	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 0, .5f));
		g.fillOval(x - 16, y - 8, 32, 16);
		g.drawAnimation(animations[direction + (moving ? 4 : 0)], x - 32, y - 60);
	}

	public void update(int delta, Personnage perso) {
		for (int objectID = 0; objectID < perso.getMap().getTiledMap().getObjectCount(0); objectID++) {
			if (x > perso.getMap().getTiledMap().getObjectX(0, objectID)
					&& x < perso.getMap().getTiledMap().getObjectX(0, objectID)
							+ perso.getMap().getTiledMap().getObjectWidth(0, objectID)
					&& y > perso.getMap().getTiledMap().getObjectY(0, objectID)
					&& y < perso.getMap().getTiledMap().getObjectY(0, objectID)
							+ perso.getMap().getTiledMap().getObjectHeight(0, objectID)) {
				if ("craft".equals(perso.getMap().getTiledMap().getObjectType(0, objectID))) {
					perso.setPosition(Position.craft);
					break;
				} else if ("coffre".equals(perso.getMap().getTiledMap().getObjectType(0, objectID))) {
					perso.setPosition(Position.coffre);
					break;
				} else if ("portail".equals(perso.getMap().getTiledMap().getObjectType(0, objectID))) {
					perso.setPosition(Position.portail);
					break;
				} else if ("rapport".equals(perso.getMap().getTiledMap().getObjectType(0, objectID))) {
					perso.setPosition(Position.rapport);
					break;
				} else
					perso.setPosition(Position.base);
			}
		}

		if (this.moving) {
			float futurX = getFuturX(delta);
			float futurY = getFuturY(delta);
			boolean collision = isCollision(perso, futurX, futurY);
			if (collision) {
				this.moving = false;
			} else {
				this.x = futurX;
				this.y = futurY;
			}
		}
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	private boolean isCollision(Personnage perso, float x, float y) {
		int tileW = perso.getMap().getTiledMap().getTileWidth();
		int tileH = perso.getMap().getTiledMap().getTileHeight();
		int logicLayer = perso.getMap().getTiledMap().getLayerIndex("col");
		if (logicLayer == -1)
			return false;
		Image tile = perso.getMap().getTiledMap().getTileImage((int) x / tileW, (int) y / tileH, logicLayer);
		boolean collision = tile != null;
		if (collision) {
			Color color = tile.getColor((int) x % tileW, (int) y % tileH);
			collision = color.getAlpha() > 0;
		}
		return collision;
	}

	private float getFuturX(int delta) {
		float futurX = this.x;
		switch (this.direction) {
		case 1:
			futurX = this.x - .1f * delta;
			break;
		case 3:
			futurX = this.x + .1f * delta;
			break;
		}
		return futurX;
	}

	private float getFuturY(int delta) {
		float futurY = this.y;
		switch (this.direction) {
		case 0:
			futurY = this.y - .1f * delta;
			break;
		case 2:
			futurY = this.y + .1f * delta;
			break;
		}
		return futurY;
	}
}
