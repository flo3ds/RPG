package core;

import java.util.Random;

public class Rand {

	public static Random rand = new Random();

	public static int entier(int min, int max) {
		if (min > 0)
			max++;
		return rand.nextInt(max - min) + min;
	}

}
