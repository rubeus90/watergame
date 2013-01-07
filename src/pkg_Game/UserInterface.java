package pkg_Game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

/**
 * Cette classe implemente une interface graphique qui comporte une image des salle du jeu, une partie texte,
 * une entree de texte, des boutons, 2 listes d'objets et une image de la carte du jeu
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 */
public class UserInterface implements ActionListener 
{
	private GameEngine engine;
	private JFrame myFrame;
	private JTextField entryField;
	private JScrollPane listScroller;
	private HashMap<String, JButton> buttons;
	private HashMap<String, JPanel>  panels;
	private HashMap<String, JMenuItem> menus;
	private JTextArea log;
	private JLabel image, map;
	private DefaultListModel<String> listRoom, listPlayer;
	private JList<String> list, list2;

	/**
	 * Construire une interface graphique. Ce constructeur prend comme parametre un GameEngine
	 * 
	 * @param gameEngine
	 *            L'objet GameEngine implemente toute la logique du jeu
	 */
	public UserInterface(GameEngine gameEngine) 
	{
		engine = gameEngine;
		createGUI();
		colorButton();
	}

	/**
	 * Interface Actionlistener pour les boutons du jeu
	 */
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == buttons.get("bouton1")) 
		{
			engine.interpretCommand("go");
		} 
		else if (e.getSource() == buttons.get("bouton2")) 
		{
			engine.interpretCommand("go nord");
		} 
		else if (e.getSource() == buttons.get("bouton3")) 
		{
			engine.interpretCommand("go monter");
		} 
		else if (e.getSource() == buttons.get("bouton4")) 
		{
			engine.interpretCommand("go ouest");
		}
		else if (e.getSource() == buttons.get("bouton6")) 
		{
			engine.interpretCommand("go est");
		} 
		else if (e.getSource() == buttons.get("bouton7")) 
		{
			engine.interpretCommand("back");
		} 
		else if (e.getSource() == buttons.get("bouton8")) 
		{
			engine.interpretCommand("go sud");
		}
		 else if(e.getSource() == buttons.get("bouton9"))
		 {
			 engine.interpretCommand("teleporter pic");
		 }
		else if (e.getSource() == buttons.get("bouton10")) 
		{
			engine.interpretCommand("drink potion");
		} 
		else if (e.getSource() == buttons.get("bouton11")) 
		{
			engine.interpretCommand("drink soin");
		} 
		else if (e.getSource() == buttons.get("bouton12")) 
		{
			engine.interpretCommand("look");
		} 
		else if (e.getSource() == buttons.get("bouton13")) 
		{
			engine.interpretCommand("help");
		} 
		else if (e.getSource() == menus.get("newAction")) 
		{
			engine.newGame();
		}
		else if (e.getSource() == menus.get("quitAction")) 
		{
			engine.interpretCommand("quit");
		}
		else if (e.getSource() == menus.get("helpAction")) 
		{
			createHelp();
		}
		else if (e.getSource() == menus.get("authorAction")) 
		{
			createCredits();
		}
		else if (e.getSource() == menus.get("copyrightAction")) 
		{
			createCopyright();
		}
		else if (e.getSource() == buttons.get("boutonParler")) 
		{
			engine.interpretCommand("parler");
		}
		else if (e.getSource() == buttons.get("boutonAttaque")) 
		{
			engine.interpretCommand("attaque");
		}
		else if (e.getSource() == buttons.get("boutonNext")) 
		{
			engine.interpretCommand("parler");
		}
		else if (e.getSource() == buttons.get("boutonHelp")) 
		{
			engine.interpretCommand("parler");
		}
		else if (e.getSource() == buttons.get("boutonNotHelp")) 
		{
			createGameOver("creeper not help");
		}
		else if (e.getSource() == buttons.get("boutonTake")) 
		{
			String nomItem = list.getSelectedValue();
			engine.interpretCommand("take " + nomItem);
		}
		else if (e.getSource() == buttons.get("boutonDrop")) 
		{
			String nomItem = list2.getSelectedValue();
			engine.interpretCommand("drop " + nomItem);
		}
		else if (e.getSource() == buttons.get("boutonDoor")) 
		{
			engine.interpretCommand("go secret");
			closeDialogue();
			createInteractionBot();
			panels.get("panelDialogue").remove(buttons.get("boutonDoor"));
		}
		else
			processCommand();
	}

	/**
	 * Methode pour fermer les dialogues avec les ennemis
	 */
	public void closeDialogue()
	{
		panels.get("panelLeft").remove(panels.get("panelDialogue"));
		listScroller = new JScrollPane(log);
		log.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 13));
		listScroller.setPreferredSize(new Dimension(600, 280));
		listScroller.setMinimumSize(new Dimension(10, 10));
		panels.get("sspanel2").add(listScroller, BorderLayout.NORTH);
		panels.get("sspanel2").add(entryField, BorderLayout.SOUTH);
		panels.get("panel2").add(panels.get("sspanel1"));
		panels.get("panel2").add(panels.get("sspanel2"));
		panels.get("panel2").add(panels.get("sspanel3"));
		panels.get("panelLeft").add(panels.get("panel2"));
		resetTextPanel();
		panels.get("panelLeft").updateUI();
	}

	/**Cette methode permet de desactiver les boutons quand la sortie correspondante n'est pas disponible et
	 * la reactive dans le cas contraire
	 */
	public void colorButton()
	{
			if(engine.getPlayer().getRoom().getExit("nord") == null)
				buttons.get("bouton2").setEnabled(false);
			else
				buttons.get("bouton2").setEnabled(true);
			
			if(engine.getPlayer().getRoom().getExit("haut") == null)
				buttons.get("bouton3").setEnabled(false);
			else
				buttons.get("bouton3").setEnabled(true);
			
			if(engine.getPlayer().getRoom().getExit("ouest") == null)
				buttons.get("bouton4").setEnabled(false);
			else
				buttons.get("bouton4").setEnabled(true);
			
			if(engine.getPlayer().getRoom().getExit("est") == null)
				buttons.get("bouton6").setEnabled(false);
			else
				buttons.get("bouton6").setEnabled(true);
			
			if(engine.getPlayer().getRoom().getExit("sud") == null)
				buttons.get("bouton8").setEnabled(false);
			else
				buttons.get("bouton8").setEnabled(true);
			
			panels.get("panel2").repaint();
	}

	/**
	 * Methode pour creer un bouton avec l'image d'une porte (utilise pour aller dans la salle secrete)
	 */
	public void createButtonDoor()
	{
		panels.get("panelDialogue").remove(buttons.get("boutonNext"));
		panels.get("panelDialogue").setLayout(new BorderLayout());
		
		Bouton boutonDoor = new Bouton();
		boutonDoor.setPreferredSize(new Dimension(200,300));
		buttons.put("boutonDoor", boutonDoor);
		boutonDoor.addActionListener(this);
		
		panels.get("panelDialogue").add(boutonDoor, BorderLayout.EAST);
	}

	/**Creer la fenetre Copyright
	 * 
	 */
	public void createCopyright()
	{
		JFrame gpl = new JFrame("Copyright");
		gpl.setSize(800, 1000);
	    gpl.setVisible(true);
	    
	    JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        java.net.URL helpURL = this.getClass().getClassLoader().getResource("gpl-3.0-standalone.html");
        if (helpURL != null) {
            try {
                editorPane.setPage(helpURL);
            } catch (IOException e) {
                System.err.println("Vous avez essaye de lire un mauvais URL: " + helpURL);
            }
        } else {
            System.err.println("Fichier non trouve");
        }
        
        JScrollPane editorScrollPane = new JScrollPane(editorPane);
        editorScrollPane.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editorScrollPane.setPreferredSize(new Dimension(250, 145));
        editorScrollPane.setMinimumSize(new Dimension(10, 10));
        editorPane.setEditable(false);
        
        gpl.setContentPane(editorScrollPane);
	}

	/**Creer la fenetre About Water Games
	 * 
	 */
	public void createCredits()
	{
		JFrame credits = new JFrame("About Water Games");
		credits.setSize(800, 1000);
	    credits.setVisible(true);
	    
	    JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        java.net.URL helpURL = this.getClass().getClassLoader().getResource("aboutwatergame.html");
        if (helpURL != null) {
            try {
                editorPane.setPage(helpURL);
            } catch (IOException e) {
                System.err.println("Vous avez essaye un mauvais URL: " + helpURL);
            }
        } else {
            System.err.println("Fichier non trouve");
        }
        
        JScrollPane editorScrollPane = new JScrollPane(editorPane);
        editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editorScrollPane.setPreferredSize(new Dimension(250, 145));
        editorScrollPane.setMinimumSize(new Dimension(10, 10));
        editorPane.setEditable(false);
        
        credits.setContentPane(editorScrollPane);
	}

	/**Cette methode cree la fenetre quand le joueur a perdu. L'image montre est choisie en fonction de la raison
	 * pour laquelle le joueur est mort
	 * 
	 * @param raison
	 */
	 public void createGameOver(String raison)
	 {
		 panels.get("panelLeft").remove(panels.get("panel2"));
		 if(panels.get("panelDialogue2") != null)
			{
			 panels.get("panelLeft").remove(panels.get("panelDialogue2"));
			}
		 myFrame.remove(panels.get("panelRight"));
		 
		 switch(raison)
		 {
		  	case "eau" : 
			{
				  showImage("images/mortNoye.png");
				  break;
			}
		  	case "sante" :
		  	{
		  		showImage("images/mortSante.png");
		  		break;
		  	}
		  	case "creeper" :
		  	{
		  		showImage("images/mortCreeper.png");
		  		break;
		  	}
		  	case "creeper not help" :
		  	{
		  		showImage("images/mortCreeperSansAide.png");
		  		break;
		  	}
		  	case "enderman" :
		  	{
		  		showImage("images/mortEnderman.png");
		  		break;
		  	}
		  	case "blaze" :
		  	{
		  		showImage("images/mortBlaze.png");
		  		break;
		  	}
		  	default: {}
		 }
	 }

	/**
	 * Gerer UserInterface
	 * La fenetre du jeux comporte 2 panneaux, celui a gauche sert a afficher l'image du jeu,
	 * les boutons, la partie texte et l'entree de texte. Celui a droite comporte les listes des objets et une image de
	 * la carte du jeu
	 */
	public void createGUI() 
	{
		
		buttons = new HashMap<String, JButton>();
		panels = new HashMap<String, JPanel>();
		menus = new HashMap<String, JMenuItem>();
		
		/*************************** Creer une nouvelle fenetre *******************************/
		myFrame = new JFrame("Water Game");
		myFrame.setResizable(true);
		
		/***************************** Creer un menu bar **************************************/
		JMenuBar menubar = new JMenuBar();
		myFrame.setJMenuBar(menubar);
		// Creer les boutons du menu
		JMenu options = new JMenu("Options");
		JMenu credits = new JMenu("Aide");
		menubar.add(options);
		menubar.add(credits);
		// Creer les boutons du dropdown menu
		JMenuItem newAction = new JMenuItem("Nouvelle partie");
		JMenuItem quitAction = new JMenuItem("Quitter le jeu");
		JMenuItem helpAction = new JMenuItem("Liste des commandes");
		JMenuItem authorAction = new JMenuItem("About Water Games");
		JMenuItem copyrightAction = new JMenuItem("Copyright");
		
		menus.put("newAction", newAction);
		menus.put("quitAction", quitAction);
		menus.put("helpAction", helpAction);
		menus.put("authorAction", authorAction);
		menus.put("copyrightAction", copyrightAction);
		
		// Ajouter ces boutons au dropdown
		options.add(newAction);
		options.addSeparator();
		options.add(quitAction);
		credits.add(helpAction);
		credits.addSeparator();
		credits.add(authorAction);
		credits.add(copyrightAction);


		/********************************* L'entree de text ***************************************/
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
		
		log.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 13));

		listScroller = new JScrollPane(log);
		listScroller.setPreferredSize(new Dimension(600, 280));
		listScroller.setMinimumSize(new Dimension(10, 10));

		/**************************** Image des salles ************************************/
		image = new JLabel();
		image.setPreferredSize(new Dimension(1200, 600));

		/********************************** Tous les boutons du jeu ************************/
		JButton bouton1 = new JButton();
		bouton1.setEnabled(false);
		JButton bouton2 = new JButton("Nord");
		JButton bouton3 = new JButton("Haut");
		JButton bouton4 = new JButton("Ouest");
		JButton bouton5 = new JButton();
		bouton5.setEnabled(false);
		JButton bouton6 = new JButton("Est");
		JButton bouton7 = new JButton("Back");
		JButton bouton8 = new JButton("Sud");
		JButton bouton9 = new JButton();
		bouton9.setEnabled(false);
		JButton bouton10 = new JButton("Potion");
		JButton bouton11 = new JButton("Soin");
		JButton bouton12 = new JButton("Regarder");
		JButton bouton13 = new JButton("Aide");
		
		JButton buttonParler = new JButton("Parler");
		buttonParler.setPreferredSize(new Dimension(300, 30));
		JButton buttonAttaque = new JButton("Attaquer");
		buttonAttaque.setPreferredSize(new Dimension(300, 30));
		
		JButton buttonNext = new JButton("Suivant");
		buttonNext.setPreferredSize(new Dimension(100,300));
		JButton buttonHelp = new JButton("Aider Creeper");
		buttonHelp.setPreferredSize(new Dimension(300,150));
		JButton buttonNotHelp = new JButton("Je ne veux pas l'aider");
		buttonNotHelp.setPreferredSize(new Dimension(300,150));
		
		JButton boutonTake = new JButton("Prendre");
		boutonTake.setPreferredSize(new Dimension(400,30));
		JButton boutonDrop = new JButton("Jeter");
		boutonDrop.setPreferredSize(new Dimension(400,30));
		
		buttons.put("bouton1", bouton1);
		buttons.put("bouton2", bouton2);
		buttons.put("bouton3", bouton3);
		buttons.put("bouton4", bouton4);
		buttons.put("bouton5", bouton5);
		buttons.put("bouton6", bouton6);
		buttons.put("bouton7", bouton7);
		buttons.put("bouton8", bouton8);
		buttons.put("bouton9", bouton9);
		buttons.put("bouton10", bouton10);
		buttons.put("bouton11", bouton11);
		buttons.put("bouton12", bouton12);
		buttons.put("bouton13", bouton13);
		buttons.put("boutonNext", buttonNext);
		buttons.put("boutonParler", buttonParler);
		buttons.put("boutonAttaque", buttonAttaque);
		buttons.put("boutonHelp", buttonHelp);
		buttons.put("boutonNotHelp", buttonNotHelp);
		buttons.put("boutonTake", boutonTake);
		buttons.put("boutonDrop", boutonDrop);
		
		/********************************* Les panels *************************************/
		// Panel 1 contient l'image en haut de la fenetre
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(1200, 600));
		panel.setLayout(new BorderLayout());
		panel.add(image, BorderLayout.CENTER);

		// Panel 2 corps qui comporte les boutons de navigation, la partie texte
		// et les boutons de commandes
		JPanel panel2 = new JPanel();
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

		// Sous panel texte
		JPanel sspanel2 = new JPanel();
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
		
		
		panels.put("panel1", panel);
		panels.put("panel2", panel2);
		panels.put("sspanel1", sspanel1);
		panels.put("sspanel2", sspanel2);
		panels.put("sspanel3", sspanel3);
		
		JPanel panel3 = new JPanel();
		panels.put("panel3", panel3);
		
		JPanel panelDialogue = new JPanel();
		JPanel panelDialogue2 = new JPanel();
		
		panels.put("panelDialogue", panelDialogue);
		panels.put("panelDialogue2", panelDialogue2);
		

		// PanelLeft box qui contient les 2 autres panels
		JPanel panelLeft = new JPanel();
		panels.put("panelLeft", panelLeft);
		panelLeft.setLayout(new BoxLayout(panelLeft, BoxLayout.PAGE_AXIS));
		panelLeft.add(panel);
		panelLeft.add(panel2);
		
		//PanelRight qui contient la liste des items dans la salle et l'inventaire du joueur
		JPanel panelRight = new JPanel();
		panels.put("panelRight", panelRight);
		panelRight.setPreferredSize(new Dimension(400, 900));
		panelRight.setLayout(new BoxLayout(panelRight, BoxLayout.PAGE_AXIS));
		
		//Les objets de la salle
		JPanel sspanelRoom = new JPanel();
		sspanelRoom.setLayout(new BorderLayout());
		sspanelRoom.setPreferredSize(new Dimension(400,347));
		sspanelRoom.setBorder(BorderFactory.createTitledBorder("Les objets presents dans cet endroit:"));
		
		listRoom = new DefaultListModel<String>();		
		list = new JList<String>(listRoom);	
		
		sspanelRoom.add(list, BorderLayout.NORTH);		
		sspanelRoom.add(boutonTake, BorderLayout.SOUTH);
		
		//Les objets du joueur
		JPanel sspanelJoueur = new JPanel();
		sspanelJoueur.setLayout(new BorderLayout());
		sspanelJoueur.setPreferredSize(new Dimension(400,347));
		sspanelJoueur.setBorder(BorderFactory.createTitledBorder("Les objets presents dans ton inventaire:"));
		
		listPlayer = new DefaultListModel<String>();		
		list2 = new JList<String>(listPlayer);	
		
		sspanelJoueur.add(list2, BorderLayout.NORTH);		
		sspanelJoueur.add(boutonDrop, BorderLayout.SOUTH);
		
		/**********************Map du jeu***************************************************/
		map = new JLabel();
		URL imageURL = this.getClass().getClassLoader().getResource("images/map.png");
		if (imageURL == null)
			System.out.println("Image non trouve");
		else 
		{
			ImageIcon icon = new ImageIcon(imageURL);
			map.setIcon(icon);
		}
		
		JPanel panelMap = new JPanel();
		panelMap.setPreferredSize(new Dimension(400,205));
		panelMap.add(map);
		
		
		panelRight.add(sspanelRoom);
		panelRight.add(sspanelJoueur);
		panelRight.add(panelMap);

		// Ajouter le panel box a notre fenetre de jeu
		myFrame.setLayout(new BorderLayout());
		myFrame.add(panelLeft, BorderLayout.WEST);
		myFrame.add(panelRight, BorderLayout.EAST);

		/****************************** add some event listeners to some components *****************************/
		myFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				killFrame();
			}
		});

		entryField.addActionListener(this);
		
		bouton1.addActionListener(this);
		bouton2.addActionListener(this);
		bouton3.addActionListener(this);
		bouton4.addActionListener(this);
		bouton5.addActionListener(this);
		bouton6.addActionListener(this);
		bouton7.addActionListener(this);
		bouton8.addActionListener(this);
		bouton9.addActionListener(this);
		bouton10.addActionListener(this);
		bouton11.addActionListener(this);
		bouton12.addActionListener(this);
		bouton13.addActionListener(this);
		
		buttonParler.addActionListener(this);
		buttonAttaque.addActionListener(this);
		buttonHelp.addActionListener(this);
		buttonNotHelp.addActionListener(this);
		buttonNext.addActionListener(this);
		
		boutonTake.addActionListener(this);
		boutonDrop.addActionListener(this);

		quitAction.addActionListener(this);
		newAction.addActionListener(this);
		helpAction.addActionListener(this);
		authorAction.addActionListener(this);
		copyrightAction.addActionListener(this);
		

		
		myFrame.pack();
		myFrame.setVisible(true);
		entryField.requestFocus();
	}
	 
	 /**
	 *Creer la fenetre des commandes disponibles 
	 */
	public void createHelp()
	{
		JFrame help = new JFrame("Liste des commandes");
		help.setSize(800, 1000);
	    help.setVisible(true);
	    
	    JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        java.net.URL helpURL = this.getClass().getClassLoader().getResource("commandword.html");
        if (helpURL != null) {
            try {
                editorPane.setPage(helpURL);
            } catch (IOException e) {
                System.err.println("Vous avez essaye de lire ce URL: " + helpURL);
            }
        } else {
            System.err.println("Fichier non trouve");
        }
        
        JScrollPane editorScrollPane = new JScrollPane(editorPane);
        editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editorScrollPane.setPreferredSize(new Dimension(250, 145));
        editorScrollPane.setMinimumSize(new Dimension(10, 10));
        editorPane.setEditable(false);
        
        help.setContentPane(editorScrollPane);
	}
	 

	 /**
	 * Cette methode cree les boutons qui permettent d'interagir avec les bots (boutons Attaquer et Parler)
	 * Les boutons sont retirees si aucun bot n'est present dans l'endroit
	 */
	public void createInteractionBot()
	{
		panels.get("panel3").setLayout(new BorderLayout());
		panels.get("panel3").add(buttons.get("boutonParler"), BorderLayout.WEST);
		panels.get("panel3").add(buttons.get("boutonAttaque"), BorderLayout.EAST);
		
		//S'il y a un bot
		if(engine.getPlayer().getRoom().getBot() != null)
		{
			panels.get("sspanel2").setLayout(new BorderLayout());
				panels.get("sspanel2").removeAll();
				panels.get("sspanel2").add(listScroller, BorderLayout.NORTH);
				listScroller.setPreferredSize(new Dimension(600, 270));
				panels.get("sspanel2").add(panels.get("panel3"), BorderLayout.SOUTH);
				panels.get("sspanel2").updateUI();
		}
		//S'il n'y a pas de bot
		else
		{
			if(panels.get("panel3") != null)
			{
				panels.get("sspanel2").setLayout(new BorderLayout());
				panels.get("sspanel2").removeAll();
				listScroller.setPreferredSize(new Dimension(600, 280));
				panels.get("sspanel2").add(entryField, BorderLayout.SOUTH);
				panels.get("sspanel2").add(listScroller, BorderLayout.NORTH);
				panels.get("sspanel2").updateUI();
			}
		}
	}
	
	/**
	 * Creer la fenetre quand le joueur a gagne
	 */
	public void createWinGame()
	 {
		 panels.get("panelLeft").remove(panels.get("panel2"));
		 myFrame.remove(panels.get("panelRight"));
		 showImage("images/gagne.png");
	 }
	
	/**
	 * Activer ou desactiver l'entree de texte
	 */
	public void enable(boolean on) {
		entryField.setEditable(on);
		if (!on)
			entryField.getCaret().setBlinkRate(0);
	}
	
	/**
	 * Retourner la fenetre du jeu
	 * 
	 * @return la fenetre du jeu
	 */
	public JFrame getFrame()
	{
		return myFrame;
	}
	
	/**
	 * Retourner la partie texte du jeu
	 * 
	 * @return la partie texte du jeu
	 */
	public JTextArea getJTextArea()
	{
		return log;
	}
	
	/**
	 * Cette methode ferme la fenetre du jeu
	 */
	public void killFrame() {
		myFrame.setVisible(false);
		myFrame.dispose();
	}
	
	/**
	 * Ecrire un texte dans la partie texte du jeu
	 */
	public void print(String text) {
		log.append(text);
		log.setCaretPosition(log.getDocument().getLength());
	}
	
	/**
	 * Ecrire un texte dans la partie texte du jeu
	 */
	public void println(String text) {
		log.append(text + "\n");
		log.setCaretPosition(log.getDocument().getLength());
	}
	
	/**
	 * Quand une commande est entree, lire la commande et proceder cette commande
	 */
	public void processCommand() 
	{
		String input = entryField.getText();
		entryField.setText("");

		engine.interpretCommand(input);
	}
	
	/**
	 * Cette methode permet d'actualiser le panneau de texte a chaque fois qu'une commande est rentre, ainsi
	  * on a pas le texte de tous les mouvements du joueur affiche
	  */
	public void resetTextPanel()
	{
		log.setText("");
		print("\n");
		engine.printLocationInfo();
		entryField.requestFocus();
	}
	
	/**
	 * Creer un bouton qui permet au joueur de se teleporter vers le pic.
	 * Ce bouton s'affiche des que le joueur possede la pierre magique et que cette pierre est chargee
	 */
	public void showBoutonTeleporter()
	{
		if(engine.getPierre().getValueActivation())
		{
			buttons.get("bouton9").setEnabled(true);
			buttons.get("bouton9").setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 11));
			buttons.get("bouton9").setText("Aller au pic");
		}
		else
		{
			buttons.get("bouton9").setEnabled(false);
			buttons.get("bouton9").setText(null);
		}
	}
	
	/**
	 * Cette methode permet de passer du jeu en mode normal en mode dialogue
	 * c'est-a-dire enlever les boutons, afficher le texte du dialogue et les boutons pour l'avancement du dialogue	 * 
	 */
	public void showDialogue(int mode)
	{
		switch(mode)
		{
			case 1 :
			{
				panels.get("panelLeft").remove(panels.get("panel2"));
				if(panels.get("panelDialogue2") != null)
				{
					panels.get("panelLeft").remove(panels.get("panelDialogue2"));
				}
				
				
				panels.get("panelDialogue").setLayout(new BorderLayout());
				panels.get("panelDialogue").add(log, BorderLayout.CENTER);
				panels.get("panelLeft").setLayout(new BoxLayout(panels.get("panelLeft"), BoxLayout.PAGE_AXIS));
				panels.get("panelLeft").add(panels.get("panelDialogue"));
				log.setText("");
				log.setFont(new Font("Verdana", Font.BOLD, 13));
				print("\n");
				
				panels.get("panelDialogue").add(buttons.get("boutonNext"), BorderLayout.EAST);
				panels.get("panelDialogue").repaint();
				break;
			}
			case 2:
			{
				panels.get("panelLeft").remove(panels.get("panelDialogue"));
				
				panels.get("panelDialogue2").setLayout(new BorderLayout());
				panels.get("panelDialogue2").add(log, BorderLayout.CENTER);
				panels.get("panelLeft").setLayout(new BoxLayout(panels.get("panelLeft"), BoxLayout.PAGE_AXIS));
				panels.get("panelLeft").add(panels.get("panelDialogue2"));
				log.setText("");
				log.setFont(new Font("Verdana", Font.BOLD, 13));
				print("\n");
				
				JPanel help = new JPanel();
				help.setLayout(new BorderLayout());
				help.add(buttons.get("boutonHelp"), BorderLayout.NORTH);
				help.add(buttons.get("boutonNotHelp"), BorderLayout.SOUTH);
				panels.get("panelDialogue2").add(help, BorderLayout.EAST);
				panels.get("panelDialogue2").repaint();
				break;
			}
			default: {}			
		}
		
	}
	
	/**
	 * Afficher une image dans l'interface
	 */
	public void showImage(String imageName) 
	{
		URL imageURL = this.getClass().getClassLoader().getResource(imageName);
		if (imageURL == null)
			System.out.println("Image non trouve");
		else 
		{
			ImageIcon icon = new ImageIcon(imageURL);
			image.setIcon(icon);
			myFrame.pack();
		}
	}
	/**
	 * Creer la liste de l'inventaire du joueur. Cette liste est rafraichie a chaque fois le joueur va dans une
	 * nouvelle salle
	 */
	public void showInventairePlayer()
	{
		listPlayer.removeAllElements();
		
		Set<String> cles = engine.getPlayer().getItemListe().getKeys();
		for (String nom : cles) 
		{
			listPlayer.addElement(nom);
		}
	}
	
	/**
	 * Creer la liste des objets dans une salle. Cette liste est rafraichie a chaque fois le joueur va dans une
	 * nouvelle salle
	 */
	public void showInventaireRoom()
	{
		listRoom.removeAllElements();
		
		Set<String> cles = engine.getPlayer().getRoom().getItemListe().getKeys();
		for (String nom : cles) 
		{
			if(nom != "beamer")
			{
				listRoom.addElement(nom);
			}
		}
	}
	
	/**
	 * Cette classe sert a creer un bouton personnalise (un bouton avec une image de fond qui est une porte).
	 * Ce bouton est utilise pour aller dans la salle secrete 
	 * 
	 * @author NGUYEN Hong Ngoc
	 * @author PATOIS Thibault
	 */
	public class Bouton extends JButton
	{
		private static final long serialVersionUID = 1L;
		private Image img;

		  public Bouton()
		  {
		    super();
		    try {
		      img = ImageIO.read(new File("./src/images/Door.jpg"));
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		  }

		  public void paintComponent(Graphics g)
		  {
		    Graphics2D g2d = (Graphics2D)g;
		    g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		  }
	}
}
