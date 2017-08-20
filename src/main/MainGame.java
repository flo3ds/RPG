package main;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import graphicEngine.Game;
import graphicEngine.SimpleGame;
import graphicEngine.SpriteBatch;

public class MainGame extends SimpleGame {

	final static short SCREEN_W = 800;
	final static short SCREEN_H = 600;
	
	static SpriteBatch batch;
	
	Menu menu;
	public GameLWJGL game;

	public static void main(String[] args) throws LWJGLException {
		
		MainGame game = new MainGame();
		game.setDisplayMode(SCREEN_W, SCREEN_H, false);
		game.start();
	}
	
	protected void create() throws LWJGLException {
		super.create();
		batch = new SpriteBatch();
		game = new GameLWJGL();
		menu = new Menu(this);
		
	}

	protected void render() throws LWJGLException {
		super.render();
		
		if(menu.exit()) {
			game.render();
		}else {
			menu.render();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void resize() throws LWJGLException {
		super.resize();
		batch.resize(Display.getWidth(), Display.getHeight());
		
	}

}
