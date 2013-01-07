package pkg_Game;


import java.util.ArrayList;

import pkg_Characters.Player;
import pkg_Characters.Bots;
import pkg_Command.Command;
import pkg_Command.Parser;
import pkg_Items.Beamer;
import pkg_Items.Item;
import pkg_Items.Pierre;
import pkg_Items.Potion;
import pkg_Room.Room;
import pkg_Room.TransporterRoom;


/**
 * Cette class est la Class principale du jeux "Water Games". "Water Games" est
 * un jeux tres simple à prendre en main, c'est un jeux textuel.
 * 
 * Cette class cree les salles, les personnages non joueurs, les objets, les potions, le Parser 
 * et demarre le jeux. Elles evalue aussi et execute les commandes que la class Parser renvoi.
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 * 
 */

public class GameEngine 
{
	private Parser parser;
	private UserInterface gui;
	private Player player;
	private Pierre pierre;
	private Beamer beamer;
	private ArrayList<Room> rooms;
	private boolean gameResetted;
	private TransporterRoom secret;
	

	/**
	 * Instancier le joueur et cree le jeu
	 */
	public GameEngine(final Player pPlayer) 
	{
		player = pPlayer;		
		createGame();
		parser = new Parser();
		gameResetted = false;
	}

	/** 
	 * Methode qui permet de charger la pierre magique si l'utilisateur la possede et est a la plaine	  
	 */
	public void chargerPierre()
	{
		if(player.getRoom().getNomRoom() == "plaine") //si l'utilisateur est à la plaine
		{
			if(player.getItemListe().getHashMap().containsKey("EnderPearl")) // si il possède la pierre
			{
				gui.println("Ta da, un autel magique est apparu!!!! Cet autel transforme ta pierre en une pierre magique" +
						" qui te permet depuis la montagne de te téléporter n'importe ou!!!!" + "\n" + 
						"Il suffit d'utiliser la commande: teleporter + nom de la salle");
				pierre.setActivation(true); //charger la pierre
			}
		}
	}
	
	/**
	 * Methode qui permet de recommencer le jeu: reinitialiser les listes d'objets ainsi que l'interface
	 */
	public void newGame()
	{
		endGame();
		createGame();
		gameResetted = true;
		gui.createGUI();
				
		player.setSante(80);
		player.getItemListe().getHashMap().clear();
		
		if (player.getRoom().getImageName() != null) 
		{
			gui.showImage(player.getRoom().getImageName());
		}
		gui.resetTextPanel();	
		gui.showInventaireRoom();
		gui.showInventairePlayer();
	}
		

	/**
	 * Creer tous les elements du jeu : les salles, les objets, creer et placer les personnages non joueurs (les ennemis),
	 * placer les salles du jeu
	 */
	private void createGame() 
	{
		Room foret, grotte, montagne, plaine, temple, plage, vallee, pic, eau;

		//Créer les salles du jeu
		foret = new Room("dans la forêt", "images/foretAvecCreeper.png", "foret");
		grotte = new Room("dans la grotte", "images/grotte.png", "grotte");
		montagne = new Room("dans les montagnes", "images/montagne.png", "montagne");
		plaine = new Room("à la plaine", "images/plaine.png", "plaine");
		temple = new Room("dans le temple Dharma", "images/temple.png", "temple");
		plage = new Room("à la plage", "images/plageAvecBlaze.png", "plage");
		vallee = new Room("dans la vallée de Dana", "images/valleeAvecEnderman.png", "vallee");
		pic = new Room("en haut du pic", "images/pic.png", "pic");
		eau = new Room("mort noyé", "images/mortNoye.png", "eau");

		/*Créer une ArrayList qui contient toutes les salles du jeu, cette ArrayList est utilisée dans 
		 * la classe TransporterRoom et RoomRandomizer*/
		rooms = new ArrayList<Room>();
		rooms.add(plage);
		rooms.add(temple);
		rooms.add(plaine);
		rooms.add(montagne);
		rooms.add(grotte);
		rooms.add(foret);
		rooms.add(vallee);
		rooms.add(pic);
		rooms.add(eau);
		
		secret = new TransporterRoom(" dans la salle secrète", "images/SalleSecrete.png", "secret", rooms);
		
		// Créer les sorties pour les salles
		foret.setExit("est", vallee);		
		foret.setExit("sud", plaine);
		foret.setExit("nord", eau);
		
		grotte.setExit("sud", vallee);
		grotte.setExit("ouest", eau);
		grotte.setExit("est", eau);
		
		montagne.setExit("ouest", vallee);
		montagne.setExit("nord", eau);
		
		plaine.setExit("nord", foret);
		plaine.setExit("est", temple);
		plaine.setExit("sud", eau);
		
		temple.setExit("nord", vallee);
		temple.setExit("sud", plage);
		temple.setExit("ouest", plaine);
				
		plage.setExit("nord", temple);
		plage.setExit("ouest", eau);
		plage.setExit("est", eau);
				
		
		pic.setExit("ouest", montagne);
		pic.setExit("nord", eau);
		pic.setExit("sud", eau);
		
		vallee.setExit("nord", grotte);
		vallee.setExit("sud", temple);
		vallee.setExit("ouest", foret);
		vallee.setExit("est", montagne);
		
		/*La salle secrète*/
		temple.setExit("secret", secret);
		
		//Créer les objets dans les salles
		plaine.addItem("massue", new Item("Une grande massue pas très bien foutue", 30));
		temple.addItem("hache", new Item("Une petite hache toute pourrie", 40));
		pic.addItem("corde", new Item("Une corde", 10));
		secret.addItem("hallebarde", new Item("Une hallebarde très puissante", 60));
		
		//Ajouter le teleporteur
		pierre = new Pierre();
		temple.addItem(pierre);

		//Créeer les potions dans les salles
		temple.addPotion(new Potion("Potion"));
		temple.addPotion(new Potion("Soin"));
		foret.addPotion(new Potion("Potion"));
		plaine.addPotion(new Potion("Soin"));

		//Position de départ du joueur
		player.setCurrentRoom(temple);
		
		//Ajouter les bots dans les salles
		Bots creeper = new Bots("Creeper", null, 80, false);
		Bots blaze = new Bots("Blaze", null, 50, true);
		Bots enderman = new Bots("Enderman", null, 20, true);
		foret.addBot("Creeper", creeper);
		plage.addBot("Blaze", blaze);	
		vallee.addBot("Enderman", enderman);
		
		//Ajouter le Beamer
		beamer = new Beamer();
		temple.addItem(beamer);
	
	}
		
	/**
	 * Terminer le jeu en fermant la fenetre de jeu 
	 */
	public void endGame() 
	{
		gui.killFrame();
	}

	/**
	 * Retourner la pierre magique EnderPearl qui permet de se teleporter n'importe ou sur la carte
	 * 
	 * @return la pierre magique EnderPearl
	 */
	public Pierre getPierre()
	{
		return pierre;
	}

	/**
	 * Retourner l'interface graphique du jeu
	 * 
	 * @return l'interface graphique du jeu
	 */
	public UserInterface getGUI()
	{
		return gui;
	}

	/**
	 * Retourner le joueur
	 * 
	 * @return le joueur
	 */
	public Player getPlayer()
	{
		return player;
	}
	
	
	/**
	 * Quand une commande est enregistree, executer l'action associée à cette
	 * commande. Si la commande n'est pas dans la liste des commandes
	 * disponibles, afficher une message d'erreur.
	 * 
	 * @param command
	 *            La commande à analyser.
	 * 
	 */
	public void interpretCommand(String commandLine) 
	{
		Command command = parser.getCommand(commandLine);
		command.execute(player);
		
		if(player.getSante() > 0) // si la santé du joueur n'est pas descendue à 0
		{		
			gui.createInteractionBot();
			gui.colorButton();
			//Ajouter une ligne de "-" qui l'esthétisme du jeu
			gui.getJTextArea().append("\n" + "----------------------------------------------------------------------------" + "\n");
			gui.showInventaireRoom(); //actualiser la fenêtre qui affiche la liste des objets dans la salle
			gui.showInventairePlayer(); //actualiser la fenêtre qui affiche l'inventaire du joueur
		}
		else //si la santé est descendue à 0, le joueur a perdu
		{
			gui.createGameOver("sante");
		}
	}

	/**
	 * Affiche l'aide ainsi que les commandes possibles du jeu.
	 */
	public void printHelp() 
	{
		gui.println("Tu es perdu sur l'île, tu es entouré d'ennemis");
		gui.println("\n" + "Quand tu te sens fatiguée, tu peux prendre des potions ou des soins pour augmenter .");
		gui.print("\n");
		gui.println(parser.showCommands());
		gui.println("\n" + "Tu peux aussi utiliser les menus en haut de la fenêtre pour voir les explications des " +
				"commandes ainsi que de redémarrer ou de quitter le jeu");
		gui.print("\n");
	}

	/**
	 * Afficher les objets et le poids total des objets dans l'inventaire du joueur
	 */
	public void printInventaire() 
	{
		gui.println(player.getInventaire());
		gui.println("Le poids total de ton inventaire est de "
				+ player.getPoidsInventaire() + " kg");
	}

	/**
	 * Afficher une description complete de la salle ou se trouve le joueur
	 */
	public void printLocationInfo() 
	{
		Room currentRoom = player.getRoom();
		gui.println(currentRoom.getLongDescription());
		gui.println("\n" + "Ta santé: " + player.getSante());
		
		chargerPierre(); /*appelle la fonction charge pierre, donc à chaque nouvelle salle, on vérifie si on peut charger la pierre 
						*magique*/		
	}
	
	/**
	 * Message d'acccueil a l'ouverture du jeux 
	 */
	private void printWelcome() 
	
	{
		gui.print("\n");
		gui.println("Bienvenue à Water Game!");
		gui.println("Prépare toi à jouer à THE jeu le plus merveilleux de l'univers");
		gui.println("Tape help si tu as besoin d'aide");
		gui.print("\n");
		gui.println(player.getLongDescriptionPlayer() + "\n");
		gui.println("Pour regarder autour de toi, tu peux utiliser le bouton Regarder");

		Room currentRoom = player.getRoom();
		gui.showImage(currentRoom.getImageName());
		gui.getJTextArea().append("\n" + "----------------------------------------------------------------------------" + "\n");
	}
	
	/**
	 * Imposer l'interface graphique du jeu
	 * 
	 * @param userInterface
	 */
	public void setGUI(UserInterface userInterface) 
	{
		gui = userInterface;
		printWelcome();
		gui.showInventaireRoom();
		gui.showInventairePlayer();
		}
	
	/**
	 * Retourner si le jeu a ete recommence par le menu "Nouvelle partie"	
	 * @return
	 */
	public boolean gameResetted()
	{
		return gameResetted;
	}
	
	/**
	 * Imposer que le jeu n'est plus à l'etat recommence
	 */
	public void setResetGame()
	{
		gameResetted = false;
	}
	
	/**
	 * Retourner l'ArrayList qui contient toutes les salles du jeu (hormis la salle secrete)
	 * 
	 * @return toutes les salles du jeu hormis la salle secrète
	 */
	public ArrayList<Room> getArrayListRoom()
	{
		return rooms;
	}
	
	/**
	 * Retourner la salle secrete
	 * 
	 * @return la salle secrete
	 */
	public TransporterRoom getTransporterRoom()
	{
		return secret;
	}
	
	/**
	 * Retourner la salle avec le nom qui correspond au String passe en parametre
	 * 
	 * @param nomRoom
	 * @return salle avec le nom demande
	 */
	public Room chooseRoom(String nomRoom)
	{
		Room room = null;
		for(int i=0; i< rooms.size(); i++)
		{
			if(nomRoom.equals(rooms.get(i).getNomRoom()))
			{
				room = rooms.get(i);				
			}
		}
		return room;
	}
}


