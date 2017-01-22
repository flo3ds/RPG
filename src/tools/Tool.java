package tools;

abstract public class Tool {
	private String id;
	
	public String getId()
	{
		return this.id;
	}
	
	public String setId(String id)
	{
		return this.id = id;
	}
	
	public String toString() {
		return this.id;
	}
}