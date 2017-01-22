package main;

import java.io.IOException;

public class GUIMain {

	public static void main(String[] args) {
		try {
			new GameConsole().game();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
