package world;

public class Code {

	private Boolean code[] = new Boolean[9];
	
	public Code(){
		for (int i=0; i<9; i++)
			code[i] = new Boolean(false);
	}
	
	public void setCode(Boolean[] code) {
		this.code = code;
	}
	
	public Boolean getCode(int id) {
		return code[id];
	}
	
	public int getId() {
		int id = 0;
		for(int i=0; i<9; i++)
			if(code[i])
				id += 1 * Math.exp(i);
		return id;
	}
	
}
