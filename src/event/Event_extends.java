package event;

abstract public class Event_extends {

	private String help;
	private String helpBase;
	private String intro;
	private String rapport;

	protected abstract String defHelp();

	public void setHelp(String help) {
		this.help = help;
	}

	public String getHelp() {
		return this.help;
	}
	
	public void setHelpBase(String help) {
		this.helpBase = help;
	}

	public String getHelpBase() {
		return this.helpBase;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getIntro() {
		return this.intro;
	}

	public String getRapport() {
		return this.rapport;
	}

	public void setRapport(String rapport) {
		this.rapport = rapport;
	}
}
