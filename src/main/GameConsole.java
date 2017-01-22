package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import action.Action_Base;
import action.Action_Coffre;
import action.Action_CraftingTable;
import action.Action_Monde;
import action.Position;
import base.Base;
import item.Bois;
import item.Minerai;
import perso.Personnage;

public class GameConsole {

	private Base base = new Base();
	private Action_Monde action_monde = new Action_Monde();
	private Action_Base action_base = new Action_Base();
	private Action_Coffre action_coffre = new Action_Coffre();
	private Action_CraftingTable action_craft = new Action_CraftingTable();

	private Personnage perso = new Personnage();

	
	public String action(String in) {
		if (perso.position == Position.base) {
			if (in.equals("explorer")) {
				this.action_monde.newMonde();
				action_base.action(perso, in);
				return this.action_monde.getDescriptionGlobal();
			}
			return action_base.action(perso, in);
		} else if (perso.position == Position.coffre) {
			return action_coffre.action(perso, base, in);
		}else if (perso.position == Position.craft) {
			return action_craft.action(perso, base, in);
		} else if (perso.position == Position.monde) {
			return action_monde.action(perso, in);
		} else
			return "error";
	}
	
	public void game() throws IOException {
		
		perso.inv.putItem(new Minerai(Minerai.matiere.cuivre, 8));
		perso.inv.putItem(new Minerai(Minerai.matiere.fer, 8));
		perso.inv.putItem(new Bois(4));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Bienvenue dans votre base !");
		System.out.println("taper 'help' pour voir les commandes disponibles.\n");
		int jours = 1;
		while (true) {
			System.out.println("===========================================================\nJours " + jours + "\n");
			String in = br.readLine();

			System.out.println(this.action(in));
			jours++;
		}

	}
}


