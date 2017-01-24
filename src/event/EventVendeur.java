package event;

public class EventVendeur extends Event_extends {

	public EventVendeur() {
		this.setIntro("Un vendeur est a la porte.\n");
		this.setRapport("Un vendeur bizare est a la porte.\nIl souhaite commercer avec nous.\n");
		this.setHelp(this.defHelp());
	}

	protected String defHelp() {
		String out = "";
		for (int i = 0; i < Action_vendeur.values().length; i++)
			out += Action_vendeur.values()[i].getName() + "\n";
		return out;
	}

	private enum Action_vendeur {

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
