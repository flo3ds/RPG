package event;

abstract public class Event_extends {

	private String help;
	private String intro;
	
	public void setHelp(String help){
		this.help = help;
	}
	
	public String getHelp(){
		return this.help;
	}
	
	public void setIntro(String intro){
		this.intro = intro;
	}
	
	public String getIntro(){
		return this.intro;
	}
}
