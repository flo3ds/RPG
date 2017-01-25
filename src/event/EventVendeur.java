package event;

import java.util.concurrent.Callable;

import perso.Personnage;

public class EventVendeur extends Event_extends {

	
	public EventVendeur() {
		this.setIntro("Un vendeur est a la porte.\n");
		this.setRapport("Un vendeur bizare est a la porte.\nIl souhaite commercer avec nous.\n");
		this.setHelp(this.defHelp());
		this.setHelpBase("Parler"); // Ici c'est une action, ne pas mettre de \n
		this.setContact("Hé ta des sous? j'ai de la bonne cam!");
		for(int i=0; i<Action_vendeur.values().length; i++)
			this.addAction(Action_vendeur.values()[i].getAction());
		
		
		
	}

	protected String defHelp() {
		String out = "";
		for (int i = 0; i < Action_vendeur.values().length; i++)
			out += Action_vendeur.values()[i].action_event.getName() + "\n";
		return out;
	}
	
	public static class FuncVendeur {

		public String call(Personnage perso, String in) throws Exception {
			if(Action_vendeur.commerce.action_event.test(in))
				return "Tien mon poto;\n" + perso.getTime().getTime()+"H\n";
			else
				return "Nan nan nan.\n";
		}

		
		
	}
	
	

	private enum Action_vendeur {

		commerce("commerce",  new FuncVendeur());

		public Action_event action_event;
		
		Action_vendeur(String str, FuncVendeur func) {
			this.action_event = new Action_event(str, func);
		}
		

		public Action_event getAction(){
			return this.action_event;
		}

		
	}

	

}
