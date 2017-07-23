/**
 * Copyright (c) 2012, Matt DesLauriers All rights reserved.
 *
 *	Redistribution and use in source and binary forms, with or without
 *	modification, are permitted provided that the following conditions are met:
 *
 *	* Redistributions of source code must retain the above copyright notice, this
 *	  list of conditions and the following disclaimer.
 *
 *	* Redistributions in binary
 *	  form must reproduce the above copyright notice, this list of conditions and
 *	  the following disclaimer in the documentation and/or other materials provided
 *	  with the distribution.
 *
 *	* Neither the name of the Matt DesLauriers nor the names
 *	  of his contributors may be used to endorse or promote products derived from
 *	  this software without specific prior written permission.
 *
 *	THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 *	AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 *	IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 *	ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 *	LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *	CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 *	SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 *	INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 *	CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 *	ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *	POSSIBILITY OF SUCH DAMAGE.
 */
package main;

import objects.Object;
import objects.Placable;
import perso.Personnage;

import static org.lwjgl.opengl.GL11.glClearColor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import biome.Biome;
import core.Inventable;
import core.Time;
import graphicEngine.BitmapFont;
import graphicEngine.Color;
import graphicEngine.Game;
import graphicEngine.Player;
import graphicEngine.SimpleGame;
import graphicEngine.SpriteBatch;
import graphicEngine.Texture;
import graphicEngine.TextureManager;
import graphicEngine.TextureRegion;
import graphicEngine.Util;
import graphicEngine.Vector2D;
import graphicEngine.world.Chunk;
import graphicEngine.world.World;
import init.Items;
import init.Objects;
import items.Item;
import layout.Layout;

public class GameLWJGL extends SimpleGame {

	final short SCREEN_W = 640;
	final short SCREEN_H = 480;

	public static void main(String[] args) throws LWJGLException {
		Game game = new GameLWJGL();
		game.setDisplayMode(640, 480, false);
		game.start();
	}

	Texture tex, tex2;
	TextureRegion tile;
	SpriteBatch batch;

	World world;
	Player player;
	world.World wworld;

	Personnage perso;
	Time time;

	float panX, panY, rot, zoom = 1f;
	BitmapFont font;
	final float MOVE_SPEED = 10f;
	final float ZOOM_SPEED = 0.025f;
	final float ROT_SPEED = 0.05f;

	boolean holdGMouse = false;
	boolean holdDMouse = false;

	protected void create() throws LWJGLException {
		super.create();

		// Load some textures
		try {

			tex = new Texture(Util.getResource("res/sol.png"));
			tile = new TextureRegion(tex, 0, 0, 32, 32);
			
			player = new Player();
			time = new Time();
			perso = new Personnage(time, player);
			
			
			Object.registerBlocks();
			Biome.registerBiomes();
			Item.registerItems();

			
			wworld = new world.World();

			world = new World(player.getPos(), wworld);
			
			player.postInit();


		} catch (IOException e) {
			// ... do something here ...
			Sys.alert("Error", "init");
			e.printStackTrace();
			System.exit(0);
		}
		glClearColor(0.5f, .5f, .5f, 1f);
		// create our sprite batch
		batch = new SpriteBatch();
	}

	void drawGame() {
		// get the instance of the view matrix for our batch
		Matrix4f view = batch.getViewMatrix();

		// reset the matrix to identity, i.e. "no camera transform"
		view.setIdentity();

		// scale the view
		if (zoom != 1f) {
			view.scale(new Vector3f(zoom, zoom, 1f));
		}

		// pan the camera by translating the view matrix
		view.translate(new Vector2f(panX, panY));

		// after translation, we can rotate...
		if (rot != 0f) {
			// we want to rotate by a center origin point, so first we translate
			view.translate(new Vector2f(Display.getWidth() / 2, Display.getHeight() / 2));

			// then we rotate
			view.rotate(rot, new Vector3f(0, 0, 1));

			// then we translate back
			view.translate(new Vector2f(-Display.getWidth() / 2, -Display.getHeight() / 2));
		}

		// apply other transformations here...

		// update the new view matrix
		batch.updateUniforms();

		// start the sprite batch
		batch.begin();

		for (int i = 0; i < world.mesh.length; i++) {
			renderSol(world.mesh[i]);
		}
		if (!world.waiting())
			for (int i = 0; i < world.mesh.length; i++) {
				renderChunk(world.getChunk(world.mesh[i].hashCode()), world.mesh[i]);
			}
		
		player.renderLayout(batch, perso);

		// reset color
		// batch.setColor(Color.WHITE);

		// finish the sprite batch and push the tiles to the GPU
		batch.end();
	}

	private void renderChunk(Chunk chunk, Vector2D pos) {
		int x = (int) (32 * Chunk.SIZE * pos.x);
		int y = (int) (32 * Chunk.SIZE * pos.y);
		// System.out.println("ch "+x+" | cy"+pos.y);
		int py = (int) (player.getPos().y / 32 % (Chunk.SIZE));
		if (player.getPos().y > 0) {

		} else {
			py *= -1;
			py = 31 - py;
		}
		// System.out.println("pos : " + world.getChunkPos(new
		// Vector2D(player.getPos().x, player.getPos().y)).y);
		// System.out.println("posp : " + py);
		Object[][] data = chunk.getGrid();
		for (int j = 0; j < Chunk.SIZE; j++) {
			if (j == py && world.getChunkPos(new Vector2D(player.getPos().x, player.getPos().y)).compare(pos))
				player.render(batch, perso);
			for (int i = Chunk.SIZE - 1; i >= 0; i--) {
				if (data[i][j] != null)
					drawObject(data[i][j], 32 * i + x, 32 * j + y);
			}
		}

	}

	private void renderSol(Vector2D pos) {
		int x = (int) (32 * Chunk.SIZE * pos.x);
		int y = (int) (32 * Chunk.SIZE * pos.y);
		for (int j = 0; j < Chunk.SIZE; j++) {
			for (int i = 0; i < Chunk.SIZE; i++) {
				batch.draw(tile, 32 * i + x, 32 * j + y);
			}
		}

	}

	private void drawObject(Object obj, int x, int y) {
		// System.out.println(" text = "+obj.getTex()+"\n");
		batch.draw(TextureManager.getInstance().getTexture(obj.getTex()), x + obj.getDx(), y + obj.getDy());
	}

	void drawHUD() {
		// draw the text with identity matrix, i.e. no camera transformation
		batch.getViewMatrix().setIdentity();
		batch.updateUniforms();

		batch.begin();

		font.drawText(batch, "Control qzds", 10, 10);
		batch.end();
	}

	protected void render() throws LWJGLException {
		super.render();

		panX = -1 * (player.getPos().x - SCREEN_W / 2);
		panY = -1 * (player.getPos().y - SCREEN_H / 2);

		if (Mouse.isButtonDown(0) && holdGMouse == false) {
			int x_m = (int) (Mouse.getX() - panX);
			int y_m = (int) (Mouse.getY() - (SCREEN_H - panY));
			y_m *= -1;
			if(player.getLayoutState()) {
				player.getLayout().click(x_m, y_m, perso);
			}else{
				Object obj = world.getObject(x_m, y_m);
				if (obj != null){
					obj.click(perso, world, new Vector2D(x_m/32, y_m/32));
				}
			}
			holdGMouse = true;
		}
		if (Mouse.isButtonDown(0) == false && holdGMouse == true)
			holdGMouse = false;
		
		if (Mouse.isButtonDown(1) && holdDMouse == false) {
			int x_m = (int) (Mouse.getX() - panX);
			int y_m = (int) (Mouse.getY() - (SCREEN_H - panY));
			y_m *= -1;
			if(player.getLayoutState()) {
				player.getLayout().click(x_m, y_m, perso);
			}else{
				Object obj = world.getObject(x_m, y_m);
				if (obj == null)
					try {
						world.placeObject(x_m,  y_m, (Object)player.getCurrentItem().getItem().getClass().newInstance());
					} catch (InstantiationException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//}
			}
			holdDMouse = true;
		}
		if (Mouse.isButtonDown(1) == false && holdDMouse == true)
			holdDMouse = false;

		world.update(player.getPos(), wworld);
		player.update(world, panX, panY);
		
		world.updateTileEntity();
		player.layoutUpdate();

		// System.out.println("x: " + player.getPos().x);

		drawGame();

		// drawHUD();
	}

	protected void resize() throws LWJGLException {
		super.resize();
		batch.resize(Display.getWidth(), Display.getHeight());
	}

}