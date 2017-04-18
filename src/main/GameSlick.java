package main;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.tiled.TiledMap;

import action.Action_Base;
import action.Action_Coffre;
import action.Action_CraftingTable;
import action.Action_Equiper;
import action.Action_Event;
import action.Action_Monde;
import action.Action_Portail;
import action.Position;
import base.Base;
import core.Time;
import event.GenEvent;
import items.Bois;
import items.Minerai;
import main.slick2d.Map;
import main.slick2d.PlayerGui;
import perso.Personnage;

public class GameSlick extends BasicGame {

	private GameContainer container;
	private Map map = new Map();
	private PlayerGui playerGui = new PlayerGui(map);
	private String out = "";

	private Time time = new Time();

	private Personnage perso = new Personnage(time);
	private GenEvent event = new GenEvent(perso);
	private Base base = new Base(event);

	private Action_Monde action_monde = new Action_Monde(perso, base);
	private Action_Base action_base = new Action_Base(perso, base);
	private Action_Portail action_portail = new Action_Portail(perso, base);
	private Action_Coffre action_coffre = new Action_Coffre(perso, base);
	private Action_CraftingTable action_craft = new Action_CraftingTable(perso, base);
	private Action_Event action_event = new Action_Event(perso, base);
	private Action_Equiper action_equiper = new Action_Equiper(perso, base);

	public GameSlick() {
		super("Lesson 1 :: WindowGame");

	}

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

	@Override
	public void init(GameContainer container) throws SlickException {
		this.container = container;
		map.init();
		playerGui.init();

		perso.inv.putItem(new Minerai(Minerai.matiere.cuivre, 16));
		perso.inv.putItem(new Minerai(Minerai.matiere.fer, 16));
		perso.inv.putItem(new Bois(8));

	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		map.render(g);
		playerGui.render(g);
		g.drawString(out, 200, 200);
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		playerGui.update(delta, perso);
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
		}
	}

	private void inventaire() {
		if (out.equals(""))
			out = this.action("inventaire");
		else
			out = "";
	}

	private void actionSpace() {
		System.out.println("position : " + perso.getPosition().toString());
	}

	@Override
	public void keyReleased(int key, char c) {
		if (Input.KEY_ESCAPE == key) {
			container.exit();
		}
	}
}
