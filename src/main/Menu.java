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
import tool.Tool;

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
import biome.Sol;
import biome.Sol_extends;
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
import graphicEngine.base.BaseGUI;
import graphicEngine.world.Chunk;
import graphicEngine.world.World;
import graphicEngine.world.Worldable;
import init.Items;
import init.Objects;
import init.Sols;
import items.Item;
import layout.Layout;

public class Menu{

	MainGame main;

	Texture tex, tex2;
	TextureRegion tile;
	SpriteBatch batch;

	float panX, panY, rot, zoom = 1f;
	BitmapFont font;
	final float MOVE_SPEED = 10f;
	final float ZOOM_SPEED = 0.025f;
	final float ROT_SPEED = 0.05f;

	boolean holdGMouse = false;
	boolean holdDMouse = false;

	private Boolean exit = false;

	public Menu(MainGame main) throws LWJGLException {
		this.main = main;
		try {
			font = new BitmapFont(Util.getResource("res/ptsans.fnt"), Util.getResource("res/ptsans_00.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		glClearColor(0.5f, .5f, .5f, 1f);
		// create our sprite batch
		this.batch = main.batch;
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

		//batch.draw(tex, 0, 0);
		
		font.drawText(batch, "new game", 100, 10);
		
		font.drawText(batch, "load", 100, 200);

		// reset color
		// batch.setColor(Color.WHITE);

		// finish the sprite batch and push the tiles to the GPU
		batch.end();
	}

	

	protected void render() throws LWJGLException {

		if (Mouse.isButtonDown(0) && holdGMouse == false) {
			int x_m = (int) (Mouse.getX() - panX);
			int y_m = (int) (Mouse.getY() - (main.SCREEN_H - panY));
			y_m *= -1;
			
			if(y_m < 200) {
				System.out.println("New game start");
				exit = true;
				main.game.newGame();
			}else {
				System.out.println("Load game start");
				exit = true;
			}


			
			
			holdGMouse = true;
		}
		if (Mouse.isButtonDown(0) == false && holdGMouse == true)
			holdGMouse = false;
		
		drawGame();

	}
	
	public Boolean exit() {
		return exit;
	}

	

}