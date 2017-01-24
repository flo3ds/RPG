package core;

public class Time {

	private long jours = 1;
	
	public void addTime(long jours){
		this.jours += jours;
	}
	
	public long getTime(){
		return this.jours;
	}
}
