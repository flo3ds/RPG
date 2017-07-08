package graphicEngine;

public class Vector2D {

	public float x;
	public float y;

	public Vector2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + (int)x;
		result = prime * result +(int) y;
		return result;
	}


	public boolean compare(Vector2D pos) {
		if(pos.x == x && pos.y == y)
			return true;
		return false;
	}

}
