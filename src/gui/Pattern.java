package gui;

public class Pattern {

	private int [][] pattern;
	
	public Pattern(int[][] pattern){
		this.setPattern(pattern);
	}

	public int [][] getPattern() {
		return pattern;
	}

	public void setPattern(int [][] pattern) {
		this.pattern = pattern;
	}
}
