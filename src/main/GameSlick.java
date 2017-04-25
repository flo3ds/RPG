package main;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import action.Action_Coffre;
import action.Action_CraftingTable;
import action.Action_Equiper;
import action.Action_Event;
import action.Action_Monde;
import action.Action_Portail;
import action.Monde_Actif;
import action.Position;
import armor.Armure_Cuir;
import armor.Armure_Fer;
import base.Base;
import core.Time;
import event.GenEvent;
import gui.PlayerGui;
import gui.layout.Layout;
import gui.map.MapBase;
import gui.map.Map_I;
import items.Bois;
import items.Minerai;
import perso.Personnage;
import weapons.Epee_Fer;

public class GameSlick extends BasicGame {

	private GameContainer container;
	private Input input;
	private MapBase map = new MapBase();
	private PlayerGui playerGui = new PlayerGui();
	private String out = "";

	private Time time = new Time();

	private Personnage perso = new Personnage(time);
	private GenEvent event = new GenEvent(perso);
	private Base base = new Base(event);

	private Monde_Actif monde_actif = new Monde_Actif();
	private Action_Monde action_monde = new Action_Monde(perso, base, monde_actif);
	private Action_Portail action_portail = new Action_Portail(perso, base, monde_actif);
	private Action_Coffre action_coffre = new Action_Coffre(perso, base);
	private Action_CraftingTable action_craft = new Action_CraftingTable(perso, base);
	private Action_Event action_event = new Action_Event(perso, base);
	private Action_Equiper action_equiper = new Action_Equiper(perso, base);
	private Layout layout;

	public GameSlick() {
		super("Lesson 1 :: WindowGame");
	}
/*
	public String action(String in) {
		if (perso.position == Position.base) {
			return action_base.action(in);
		} else if (perso.position == Position.portail) {
			if (Action_Portail.Action_portail.explorer.action.test(in)) {
				if (!this.action_portail.sonder)
					this.action_monde.newMonde();
				else
					this.action_portail.sonder = false;
				action_portail.action(in);
				return this.action_monde.getDescriptionGlobal();
			} else if (Action_Portail.Action_portail.sonder.action.test(in)) {
				this.action_portail.sonder = true;
				this.action_monde.newMonde();
				action_portail.action(in);
				return this.action_monde.getDescriptionSonde();
			}
			return action_portail.action(in);
		} else if (perso.position == Position.coffre) {
			return action_coffre.action(in);
		} else if (perso.position == Position.craft) {
			return action_craft.action(in);
		} else if (perso.position == Position.event) {
			return action_event.action(in);
		} else if (perso.position == Position.equiper) {
			return action_equiper.action(in);
		} else if (perso.position == Position.monde || perso.position == Position.faune
				|| perso.position == Position.flore) {
			return action_monde.action(in);

		} else
			return "error GAME GUI";
	}
*/
	
	@Override
	public void init(GameContainer container) throws SlickException {
		this.container = container;
		input = container.getInput();
		layout = new Layout(null, input, 50, 50, 500, 500, "lol");
		map.init();
		playerGui.init();
		perso.setMap(map);

		perso.inv.putItem(new Minerai(Minerai.matiere.cuivre, 16));
		perso.inv.putItem(new Minerai(Minerai.matiere.fer, 16));
		perso.inv.putItem(new Bois(8));
		perso.inv.putItem(new Armure_Fer());
		perso.inv.putItem(new Armure_Cuir());
		perso.inv.putItem(new Epee_Fer());

	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		((Map_I)perso.getMap()).render(g);
		playerGui.render(g);
		g.drawString(out, 200, 200);
		layout.render(container, g);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		playerGui.update(delta, perso);
		layout.update();
		this.event.genEvent();

	}

	@Override
	public void keyPressed(int key, char c) {
		switch (key) {
		case Input.KEY_UP:
			playerGui.setDirection(0);
			playerGui.setMoving(true);
			break;
		case Input.KEY_LEFT:
			playerGui.setDirection(1);
			playerGui.setMoving(true);
			break;
		case Input.KEY_DOWN:
			playerGui.setDirection(2);
			playerGui.setMoving(true);
			break;
		case Input.KEY_RIGHT:
			playerGui.setDirection(3);
			playerGui.setMoving(true);
			break;
		case Input.KEY_SPACE:
			actionSpace();
			break;
		case Input.KEY_E:
			inventaire();
			break;
		case Input.KEY_A:
			stuff();
			break;
		}
	}

	private void inventaire() {
		if (layout.isShow())
			layout.close();
		else {
			layout.drawSquare(perso.inv.liste());
		}
	}
	
	private void stuff() {
		if (layout.isShow())
			layout.close();
		else {
			layout.open(action_equiper);
		}
	}

	private void actionSpace() {
		if (layout.isShow())
			layout.close();
		else {
			if (perso.getPosition().toString().equals(Position.craft.name()))
				layout.open(action_craft);
			else if (perso.getPosition().toString().equals(Position.coffre.name()))
				layout.open(action_coffre);
			else if (perso.getPosition().toString().equals(Position.portail.name()))
				layout.open(action_portail);
			else if (perso.getPosition().toString().equals(Position.rapport.name()))
				layout.open(action_event);
		}
	}

	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			container.exit();
		}
	}
}
