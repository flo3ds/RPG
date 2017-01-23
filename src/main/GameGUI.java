package main;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import action.Action_Base;
import action.Action_Coffre;
import action.Action_CraftingTable;
import action.Action_Monde;
import action.Position;
import base.Base;
import items.Bois;
import items.Minerai;
import perso.Personnage;

public class GameGUI {

	JFrame frame;
	private DefaultListModel dlm = new DefaultListModel();
	private String bufferOut = "";
	private JLabel label = new JLabel("New label");

	private Base base = new Base();
	private Action_Monde action_monde = new Action_Monde();
	private Action_Base action_base = new Action_Base();
	private Action_Coffre action_coffre = new Action_Coffre();
	private Action_CraftingTable action_craft = new Action_CraftingTable();

	private Personnage perso = new Personnage();

	/**
	 * Create the application.
	 */
	public GameGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.DARK_GRAY);
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 276, 0 };
		gridBagLayout.rowHeights = new int[] { 27, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setBackground(Color.DARK_GRAY);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setResizeWeight(1.0);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.gridheight = 2;
		gbc_splitPane.insets = new Insets(0, 0, 5, 0);
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 0;
		frame.getContentPane().add(splitPane, gbc_splitPane);

		JPanel panel_label = new JPanel();
		panel_label.setBackground(Color.DARK_GRAY);
		splitPane.setLeftComponent(panel_label);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel_label.setLayout(new CardLayout(0, 0));

		JScrollPane scrollPane_labele = new JScrollPane(label);
		panel_label.add(scrollPane_labele, "name_9737361530187");
		this.label.setForeground(Color.WHITE);
		this.label.setBackground(Color.DARK_GRAY);
		this.label.setOpaque(true);

		JPanel panel_list = new JPanel();
		panel_list.setBackground(Color.DARK_GRAY);
		splitPane.setRightComponent(panel_list);

		JList list = new JList(this.dlm);
		list.setForeground(Color.WHITE);
		list.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 0, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		panel_list.setLayout(new CardLayout(0, 0));

		JScrollPane scrollPane_list = new JScrollPane(list);
		panel_list.add(scrollPane_list, "name_9759254302099");

		list.addMouseListener(new Click(this));

		frame.setVisible(true);

		perso.inv.putItem(new Minerai(Minerai.matiere.cuivre, 16));
		perso.inv.putItem(new Minerai(Minerai.matiere.fer, 16));
		perso.inv.putItem(new Bois(4));

		this.print("Bienvenue dans votre base !\n");
		this.listeAction();
	}

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
		} else if (perso.position == Position.craft) {
			return action_craft.action(perso, base, in);
		} else if (perso.position == Position.monde || perso.position == Position.faune) {
			return action_monde.action(perso, in);
		} else
			return "error";
	}

	public void listeAction() {
		String in[] = this.action("").split("\n");
		dlm.clear();

		for (int i = 0; i < in.length; i++)
			dlm.addElement(in[i]);
	}

	public void print(String str) {
		// this.bufferOut += str.replace("\n", "<br/>");
		this.bufferOut = str.replace("\n", "<br/>");
		this.label.setText("<html>" + this.bufferOut + "</html");

	}

	private class Click extends MouseAdapter {
		private GameGUI gui;

		public Click(GameGUI gui) {
			this.gui = gui;
		}

		public void mouseClicked(MouseEvent evt) {
			JList list = (JList) evt.getSource();
			if (evt.getClickCount() == 2) {
				int index = list.locationToIndex(evt.getPoint());
				this.gui.print(this.gui.action(list.getModel().getElementAt(index).toString()));
				this.gui.listeAction();
			}
		}
	}
}
