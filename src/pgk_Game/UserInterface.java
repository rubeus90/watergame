package pgk_Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
//import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.MenuKeyListener;

import java.awt.Image;
import javax.imageio.ImageIO;

import pkg_Command.Parser;

import java.awt.Graphics;

import java.io.File;
import java.io.IOException;

/**
 * This class implements a simple graphical user interface with a text entry
 * area, a text output area and an optional image.
 * 
 * @author Ngocky & Thibault
 * @version 0.00001
 */
public class UserInterface implements ActionListener 
{
	private GameEngine engine;
	private JFrame myFrame;
	private JTextField entryField;
	private JButton bouton1;
	private JButton bouton2;
	private JButton bouton3;
	private JButton bouton4;
	private JButton bouton5;
	private JButton bouton6;
	private JButton bouton7;
	private JButton bouton8;
	private JButton bouton9;
	private JButton bouton10;
	private JButton bouton11;
	private JButton bouton12;
	private JButton bouton13;
	private JMenuItem newAction;
	private JMenuItem saveAction;
	private JMenuItem quitAction;
	private JMenuItem authorAction;
	private JMenuItem copyrightAction;
	private JComboBox liste;
	private JTextArea log;
	private JLabel image;
	private Parser parser;
	private JPanel sspanel2;
	private JPanel panel;
	private JPanel panel2;


	/**
	 * Construct a UserInterface. As a parameter, a Game Engine (an object
	 * processing and executing the game commands) is needed.
	 * 
	 * @param gameEngine
	 *            The GameEngine object implementing the game logic.
	 */
	public UserInterface(GameEngine gameEngine) 
	{
		engine = gameEngine;
		createGUI();
		colorButton();
	}

	/**
	 * Actionlistener interface for entry textfield.
	 */
	public void actionPerformed(ActionEvent e) 
	{
		
		if (e.getSource() == bouton1) 
		{
			engine.interpretCommand("go");
		} 
		else if (e.getSource() == bouton2) 
		{
			engine.interpretCommand("go nord");
		} 
		else if (e.getSource() == bouton3) 
		{
			engine.interpretCommand("go monter");
		} 
		else if (e.getSource() == bouton4) 
		{
			engine.interpretCommand("go ouest");
		}
		// else if(e.getSource() == bouton5)
		// {
		// engine.interpretCommand();
		// }
		else if (e.getSource() == bouton6) 
		{
			engine.interpretCommand("go est");
		} 
		else if (e.getSource() == bouton7) 
		{
			engine.interpretCommand("back");
		} 
		else if (e.getSource() == bouton8) 
		{
			engine.interpretCommand("go sud");
		}
		 else if(e.getSource() == bouton9)
		 {
			 createGameOver("sante");
		 }
		else if (e.getSource() == bouton10) 
		{
			engine.interpretCommand("drink potion");
		} 
		else if (e.getSource() == bouton11) 
		{
			engine.interpretCommand("drink soin");
		} 
		else if (e.getSource() == bouton12) 
		{
			engine.interpretCommand("look");
		} 
		else if (e.getSource() == bouton13) 
		{
			engine.printInventaire();
		} 
		else if (e.getSource() == quitAction) 
		{
			engine.interpretCommand("quit");
		}
		else if (e.getSource() == authorAction) 
		{
			engine.interpretCommand("credits");
		}
		else if (e.getSource() == newAction) 
		{
			engine.newGame();
		}

		else
			processCommand();
	}

	/**
	 * Gérer User Interface La fenêtre du jeux comporte 2 panneaux, celui en
	 * haut sert à afficher l'image Le panneau en bas comporte 3 sous-panneaux,
	 * dont le panneau des boutons de directions, la partie texte et le panneau
	 * des boutons d'options
	 */
	public void createGUI() 
	{
		/*************************** Créer une nouvelle fenêtre *******************************/
		myFrame = new JFrame("Water Game");
		myFrame.setResizable(false);

		/***************************** Créer un menu bar **************************************/
		JMenuBar menubar = new JMenuBar();
		myFrame.setJMenuBar(menubar);
		// Créer les boutons du menu
		JMenu options = new JMenu("Options");
		JMenu credits = new JMenu("Credits");
		menubar.add(options);
		menubar.add(credits);
		// Créer les boutons du dropdown menu
		newAction = new JMenuItem("Nouvelle partie");
		saveAction = new JMenuItem("Sauvegarder la partie");
		quitAction = new JMenuItem("Quitter le jeu");
		authorAction = new JMenuItem("Auteurs");
		copyrightAction = new JMenuItem("Copyright");
		// Ajouter ces boutons au dropdown
		options.add(newAction);
		options.add(saveAction);
		options.addSeparator();
		options.add(quitAction);
		credits.add(authorAction);
		credits.add(copyrightAction);

		/**************************** JComboBox ******************************************************/
		liste = new JComboBox();
		liste.addItem("truc");

		/********************************* L'entrée de text *****************************************/
		entryField = new JTextField(34);

		/*********************************** Affichage du text du jeu *****************************/
		log = new JTextArea();
		log.setEditable(false);
		// Envelopper la partie texte pour que quand on a un texte trop longue, le
		// texte se recadre automatiquement
		log.setLineWrap(true);
		log.setWrapStyleWord(true);
		log.setOpaque(false);
		log.setFocusable(false);

		JScrollPane listScroller = new JScrollPane(log);
		listScroller.setPreferredSize(new Dimension(600, 280));
		listScroller.setMinimumSize(new Dimension(10, 10));

		/**************************** Image des salles ************************************/
		image = new JLabel();
		image.setPreferredSize(new Dimension(1200, 600));

		/********************************** Tous les boutons du jeu *******************************/
		bouton1 = new JButton();
		bouton1.setEnabled(false);
		bouton2 = new JButton("Nord");
		bouton3 = new JButton("Haut");
		bouton4 = new JButton("Ouest");
		bouton5 = new JButton();
		bouton5.setEnabled(false);
		bouton6 = new JButton("Est");
		bouton7 = new JButton("Back");
		bouton8 = new JButton("Sud");
		bouton9 = new JButton();
		bouton9.setEnabled(false);
		bouton10 = new JButton("Potion");
		bouton11 = new JButton("Soin");
		bouton12 = new JButton("Regarder");
		bouton13 = new JButton("Inventaire");

		/********************************* Les panels *************************************/
		// Panel 1 contient l'image en haut de la fenêtre
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(1200, 600));
		panel.setLayout(new BorderLayout());
		panel.add(image, BorderLayout.CENTER);

		// Panel 2 corps qui comporte les boutons de navigation, la partie texte
		// et les boutons de commandes
		panel2 = new JPanel();
		// Sous panel navigation
		JPanel sspanel1 = new JPanel();
		sspanel1.setPreferredSize(new Dimension(300, 300));
		sspanel1.setLayout(new GridLayout(3, 3));
		sspanel1.add(bouton1);
		sspanel1.add(bouton2);
		sspanel1.add(bouton3);
		sspanel1.add(bouton4);
		sspanel1.add(bouton5);
		sspanel1.add(bouton6);
		sspanel1.add(bouton7);
		sspanel1.add(bouton8);
		 sspanel1.add(bouton9);

//		sspanel1.add(liste);

		// Sous panel texte
		sspanel2 = new JPanel();
		sspanel2.setLayout(new BorderLayout());
		sspanel2.setPreferredSize(new Dimension(600, 300));
		sspanel2.add(listScroller, BorderLayout.NORTH);
		sspanel2.add(entryField, BorderLayout.SOUTH);
		// Sous panel boutons
		JPanel sspanel3 = new JPanel();
		sspanel3.setPreferredSize(new Dimension(300, 300));
		sspanel3.setLayout(new GridLayout(2, 2));
		sspanel3.add(bouton10);
		sspanel3.add(bouton11);
		sspanel3.add(bouton12);
		sspanel3.add(bouton13);

		panel2.setLayout(new BoxLayout(panel2, BoxLayout.LINE_AXIS));
		panel2.add(sspanel1);
		panel2.add(sspanel2);
		panel2.add(sspanel3);

//		// Panel 3 box qui contient les 2 autres panels
//		JPanel panel3 = new JPanel();
//		panel3.setLayout(new BoxLayout(panel3, BoxLayout.PAGE_AXIS));
//		panel3.add(panel);
//		panel3.add(panel2);

		// Ajouter le panel box à notre fenêtre de jeu
		myFrame.setLayout(new BorderLayout());
		myFrame.add(panel, BorderLayout.NORTH);
		myFrame.add(panel2, BorderLayout.SOUTH);

		/****************************** add some event listeners to some components *****************************/
		myFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				killFrame();
			}
		});

		entryField.addActionListener(this);
		// bouton1.addActionListener(this);
		bouton2.addActionListener(this);
		bouton3.addActionListener(this);
		bouton4.addActionListener(this);
		// bouton5.addActionListener(this);
		bouton6.addActionListener(this);
		bouton7.addActionListener(this);
		bouton8.addActionListener(this);
		bouton9.addActionListener(this);
		bouton10.addActionListener(this);
		bouton11.addActionListener(this);
		bouton12.addActionListener(this);
		bouton13.addActionListener(this);

		authorAction.addActionListener(this);
		quitAction.addActionListener(this);
		newAction.addActionListener(this);

		/****************************************************************/
		liste.getSelectedItem();
		/******************************************************************/

		myFrame.pack();
		myFrame.setVisible(true);
		entryField.requestFocus();
	}

	/**
	 * Enable or disable input in the input field.
	 */
	public void enable(boolean on) {
		entryField.setEditable(on);
		if (!on)
			entryField.getCaret().setBlinkRate(0);
	}

	public JTextArea getJTextArea()
	{
		return log;
	}

	public void killFrame() {
		myFrame.setVisible(false);
		myFrame.dispose();
	}

	// public void eat()
	// {
	// System.out.println("Tu as déjà mangé, tu n'as plus faim");
	// }

	/**
	 * Print out some text into the text area.
	 */
	public void print(String text) {
		log.append(text);
		log.setCaretPosition(log.getDocument().getLength());
	}

	/**
	 * Print out some text into the text area, followed by a line break.
	 */
	public void println(String text) {
		log.append(text + "\n");
		log.setCaretPosition(log.getDocument().getLength());
	}

	/**
	 * A command has been entered. Read the command and do whatever is necessary
	 * to process it.
	 */
	public void processCommand() {
		String input = entryField.getText();
		entryField.setText("");

		engine.interpretCommand(input);
	}

	 public void createGameOver(String raison)
	 {
		 myFrame.remove(panel2);
		 
		 switch(raison)
		 {
		  	case "eau" : 
			{
				  showImage("images/Mort.png");
				  break;
			}
		  	case "sante" :
		  	{
		  		showImage("images/gameover.jpg");
		  		break;
		  	}
		  	case "creeper" :
		  	{
		  		showImage("images/gameover.jpg");
		  		break;
		  	}
		  	default:
		  		showImage("images/gameover.jpg");
		 }
	 }
	 

	public void resetTextPanel()
	{
		log.setText("");
		print("\n");
		engine.printLocationInfo();
	}
	
	/**
	 * Show an image file in the interface.
	 */
	public void showImage(String imageName) 
	{
		URL imageURL = this.getClass().getClassLoader().getResource(imageName);
		if (imageURL == null)
			System.out.println("Image non trouvé");
		else {
			ImageIcon icon = new ImageIcon(imageURL);
			image.setIcon(icon);
			myFrame.pack();
		}
	}
	
	public JFrame getFrame()
	{
		return myFrame;
	}
	
	public void showDialogue()
	{
		myFrame.remove(panel2);
		JPanel panelDialogue = new JPanel();
		panelDialogue.add(log, BorderLayout.CENTER);
		myFrame.add(panelDialogue);
		log.setText("");
		print("\n");
	}
	
	public void colorButton()
	{
		if(engine.getPlayer().getRoom().getExit("nord") == null)
			bouton2.setEnabled(false);
		else
			bouton2.setEnabled(true);
		
		if(engine.getPlayer().getRoom().getExit("haut") == null)
			bouton3.setEnabled(false);
		else
			bouton3.setEnabled(true);
		
		if(engine.getPlayer().getRoom().getExit("ouest") == null)
			bouton4.setEnabled(false);
		else
			bouton4.setEnabled(true);
		
		if(engine.getPlayer().getRoom().getExit("est") == null)
			bouton6.setEnabled(false);
		else
			bouton6.setEnabled(true);
		
		if(engine.getPlayer().getRoom().getExit("sud") == null)
			bouton8.setEnabled(false);
		else
			bouton8.setEnabled(true);
		
		panel2.repaint();
	}
	
}
