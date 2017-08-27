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
	
	public Vector2D bottom() {
		return new Vector2D(x, y+1);
	}
	
	public Vector2D bottom(int i) {
		return new Vector2D(x, y+i);
	}
	
	public Vector2D top() {
		return new Vector2D(x, y-1);
	}
	
	public Vector2D top(int i) {
		return new Vector2D(x, y-i);
	}
	
	public Vector2D left() {
		return new Vector2D(x-1, y);
	}
	
	public Vector2D left(int i) {
		return new Vector2D(x-i, y);
	}
	
	public Vector2D right() {
		return new Vector2D(x+1, y);
	}
	
	public Vector2D right(int i) {
		return new Vector2D(x+i, y);
	}
	
	

}
