package action;

public enum Action {

	inventaire("inventaire"),
	base("base"),
	
	explorer("explorer"),
	coffre("coffre"),
	
	coffre_liste("liste"),
	coffre_put("coffre put"),
	
	analyser_sol("analyser sol"),
	miner("miner"),
	
	craftTable("craft"),
	build_item("build item"),
	build_tool("build tool"),
	analyser_flore("analyser flore"),
	analyser_faune("analyser faune"),
	couper_bois("couper bois"),
	chasser("chasser"),
	arrêter_chasse("arrêter la chasse"); 

	private final String str;

	Action(String str) {
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
