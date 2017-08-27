package items;


public class Branche extends Item implements Burnable {

	public Branche() {
		super("branche");
	}

	@Override
	public int getFuelTime() {
		// TODO Auto-generated method stub
		return 100;
	}

}