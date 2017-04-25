package monde;

public class GestionId {

	private int id = 1;
	
	public int getIdAndAddCount(int count){
		int i = id;
		id = id + count;
		return i;
	}
	
	
	
}
