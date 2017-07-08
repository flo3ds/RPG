package main;

import java.io.IOException;


public class MainGame {

	public static void main(String[] args) throws IOException {
		/*
		 * if (GraphicsEnvironment.isHeadless()) { new GameConsole().game(); }
		 * else { new GameGUI(); }
		 */
		// GameGUI window = new GameGUI();
		/*
		try {
			new AppGameContainer(new GameSlick(), 600, 600, false).start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		new GameLWJGL();

	}

}
