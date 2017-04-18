package main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;

public class GameSlick extends BasicGame {

	private TiledMap map;
	private GameContainer container;
	private float x = 300, y = 300;
	private int direction = 0;
	private boolean moving = false;
	private Animation[] animations = new Animation[8];
	
	 public GameSlick() {
	        super("Lesson 1 :: WindowGame");
	    }

	    @Override
	    public void init(GameContainer container) throws SlickException {
	    	 this.container = container;
	    	 this.map = new TiledMap("sprites/map.map");
	    	  SpriteSheet spriteSheet = new SpriteSheet("sprites/perso.png", 64, 64);
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

	    @Override
	    public void render(GameContainer container, Graphics g) throws SlickException {
	        this.map.render(0, 0);
	        g.drawAnimation(animations[direction + (moving ? 4 : 0)], x, y);
	    }

	    @Override
	    public void update(GameContainer container, int delta) throws SlickException {
	    	if (this.moving) {
				switch (this.direction) {
				case 0:
					this.y -= .1f * delta;
					break;
				case 1:
					this.x -= .1f * delta;
					break;
				case 2:
					this.y += .1f * delta;
					break;
				case 3:
					this.x += .1f * delta;
					break;
				}
	}
	    }
	    
	    @Override
		public void keyPressed(int key, char c) {
			switch (key) {
			case Input.KEY_UP:
				this.direction = 0;
				this.moving = true;
				break;
			case Input.KEY_LEFT:
				this.direction = 1;
				this.moving = true;
				break;
			case Input.KEY_DOWN:
				this.direction = 2;
				this.moving = true;
				break;
			case Input.KEY_RIGHT:
				this.direction = 3;
				this.moving = true;
				break;
			}
	}
	    
	    @Override
	    public void keyReleased(int key, char c) {
	        if (Input.KEY_ESCAPE == key) {
	            container.exit();
	        }
	    }
}
