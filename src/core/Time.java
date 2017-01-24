package core;

public class Time {

	private double jours = 1.0;
	
	public void addTime(double jours){
		this.jours += jours;
	}
	
	public double getTime(){
		return this.jours;
	}
}
