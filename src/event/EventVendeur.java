package event;

public class EventVendeur extends Event_extends{

	public EventVendeur(){
		this.setIntro("un vendeur et a la porte.\n");
		this.setHelp(this.defHelp());
	}
	
	private String defHelp(){
		String out = "";
		for(int i=0; i < Action_vendeur.values().length; i++)
			out += Action_vendeur.values()[i].getName();
		return out;
	}
	
	private enum Action_vendeur{
		
		base("base"),
		parler("parler");
		
		private final String str;

		Action_vendeur(String str) {
			this.str = str;
		}

		public String getName() {
			return this.str;
		}

		public Boolean test(String in) {
			if (in.equals(this.str))
				return true;
			else
				return false;
		}
	}
	
}
