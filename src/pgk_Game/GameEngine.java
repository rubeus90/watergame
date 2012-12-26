package pgk_Game;
//import java.util.Stack;
//import java.io.FileReader;
//import java.io.BufferedReader;

import java.util.Scanner;
// import java.io.FileWriter;
// import java.io.BufferedWriter;
// import java.io.PrintWriter;
// import java.net.URL;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import pkg_Characters.Player;
import pkg_Characters.Bots;
import pkg_Command.Command;
import pkg_Command.CommandWords;
import pkg_Command.Parser;
import pkg_Items.Beamer;
import pkg_Items.Item;
import pkg_Items.Pierre;
import pkg_Items.Potion;
import pkg_Room.Room;
import pkg_Room.TransporterRoom;


// import java.io.IOException;

/**
 * Cette class est la Class principale du jeux "Water Games". "Water Games" est
 * un jeux très simple à prendre en main, c'est un jeux textuel.
 * 
 * Pour joueur à ce superbe jeux, créé un instance de cette classé et appelé la
 * méthode "play"
 * 
 * Cette class créé et initialise toutes les autres classes. Elle créé les
 * Salles, le Parser et démarre le jeux. Elles évalue aussi et exécute les
 * commandes que la class Parser renvoi.
 * 
 */

public class GameEngine 
{
	private Parser parser;
	// private Room currentRoom;
	private UserInterface gui;
	// private Stack<Room> salles;
	private Player player;
	private CommandWords commandWords;
	private Pierre pierre;
	private Beamer beamer;
	private ArrayList<Room> rooms;
	private Scanner sr;
	private Bots creeper;
	private Bots blaze;
	

	/**
	 * Créé le jeux et initialiser la carte.
	 */
	public GameEngine(final Player pPlayer) 
	{
		player = pPlayer;		
		createGame();
		parser = new Parser();
		commandWords = new CommandWords();
	}

	/** 
	 * methode qui permet de charger la pierre magique si l utilisateur la possede et est dans la foret
	 *  
	 */
	public void chargerPierre()
	{
		if(player.getRoom().getNomRoom() == "foret") //si l'utilisateur est dans la forêt
		{
			if(player.getItemListe().getHashMap().containsKey("EnderPearl")) // si il possède la pierre
			{
				gui.println("Ta da, un autel magique est apparu!!!! Cet autel transforme ta pierre en une pierre magique qui te permet depuis la montagne de te téléporter n'importe ou!!!!" + "\n" + "Il suffit d'utiliser la commande: teleporter + nom de la salle");
				pierre.setActivation(true);
			}
		}
	}
	
	public void newGame()
	{
		endGame();
		createGame();
		gui.createGUI();
				
		player.setSante(80);
		player.getItemListe().getHashMap().clear();
		
		if (player.getRoom().getImageName() != null) 
		{
			gui.showImage(player.getRoom().getImageName());
		}
		gui.resetTextPanel();	
	}
		

	/**
	 * Créé toutes les pièces et les liens entre chacunes.
	 */
	private void createGame() 
	{
		Room foret, grotte, montagne, plaine, temple, plage, vallee, pic, eau;

		// Créer les salles du jeu
		foret = new Room("dans la forêt", "images/foret.png", "foret");
		grotte = new Room("dans la grotte", "images/grotte.jpg", "grotte");
		montagne = new Room("dans les montagnes", "images/montagne.png", "montagne");
		plaine = new Room("à la plaine", "images/plaine.png", "plaine");
		temple = new Room("dans le temple Dharma", "images/temple.png", "temple");
		plage = new Room("à la plage", "images/plage.jpeg", "plage");
		vallee = new Room("dans la vallée de Dana", "images/vallee.jpg", "vallee");
		pic = new Room("en haut du pic", "images/pic.jpg", "pic");
		eau = new Room("mort noyé", "images/Mort.png", "eau");

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
		
		TransporterRoom secret = new TransporterRoom(" dans la salle secrète", "images/SalleSecrete.png", "secret", rooms);
				
		
		// Créer les sorties pour les salles
		foret.setExit("est", vallee);		
		foret.setExit("sud", plaine);
		foret.setExit("nord", eau);
		
		grotte.setExit("sud", vallee);
		grotte.setExit("ouest", eau);
		grotte.setExit("est", eau);
		
		montagne.setExit("ouest", vallee);
		montagne.setExit("nord", eau);
		/*Pour le teleporteur*/
		montagne.setExit("foret", foret);
		montagne.setExit("grotte", grotte);
		montagne.setExit("plaine", plaine);
		montagne.setExit("temple", temple);
		montagne.setExit("plage", plage);
		montagne.setExit("vallee", vallee);
		montagne.setExit("pic", pic);
		
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
		temple.setExit("est", secret);
		
		//Créer les objets dans les salles
		foret.addItem("hache", new Item("Une petite hache toute pourrie", 40));
		plaine.addItem("sabre", new Item("Un sabre lumineux", 30));
		grotte.addItem("massue", new Item("Une grande massue", 45));
		plage.addItem("filet", new Item("Un grand filet", 50));
		temple.addItem("arc", new Item("Un arc en bois", 40));
		temple.addItem("torche", new Item("Une petite torche", 10));
		
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
		creeper = new Bots("Creeper", null, false);
		blaze = new Bots("Blaze", null, true);
		foret.addBot("Creeper", creeper);
		plage.addBot("Blaze", blaze);		
		
		//Ajouter le Beamer
		beamer = new Beamer();
		temple.addItem(beamer);
	}
	

//	public void credits() {
//		gui.println("Nous sommes NGUYEN Hong Ngoc aka Ngocky et PATOIS Thibault, deux étudiants très brillants en 3ème de l'ESIEE (LES E3S EN FORCE!!!!). Nous avons crée ce jeu dans le cadre de l'apprentissage de Java, mais nous avons mis là dedans tout notre amour (et n'oublie pas Thibault qui a mis tout son coeur dans les nombreuses versions de notre page web), donc j'espère que tu vas aimer notre jeu :D");
//	}
	
	/**
	 * Terminer le jeu en fermant la fenetre de jeu
	 * 
	 */
	public void endGame() 
	{
		gui.killFrame();
	}

	public Pierre getPierre()
	{
		return pierre;
	}

	public UserInterface getGUI()
	{
		return gui;
	}

	public Player getPlayer()
	{
		return player;
	}
	
	
	/**
	 * Quand une commande est enregistrée, excécuter l'action associée à cette
	 * commande. Si la commande n'est pas dans la liste des commandes
	 * disponibles, afficher une message d'erreur.
	 * 
	 * @param command
	 *            La commande à analyser.
	 * 
	 */
	public void interpretCommand(String commandLine) 
	{
//		gui.println(commandLine);
		Command command = parser.getCommand(commandLine);
		command.execute(player);
		if(player.getSante() > 0)
		{			
			gui.colorButton();
			gui.createInteractionBot();
			gui.getJTextArea().append("\n" + "----------------------------------------------------------------------------" + "\n");
		}
		else
		{
			gui.createGameOver("sante");
		}
		
//		String commandWord = command.getStringCommandWord();
//		CommandWord truc = commandWords.getCommandWord(commandWord);
//		
//		
//		switch(truc)
//		{
//			case UNKNOWN:/
//			{
//				gui.println("Je ne comprend pas ce que tu veux faire...");
//				return;
//			}
//			
//			case GO:/
//			{
//				player.diminueSante(10);
//				player.setMaxPoids();
//				player.goRoom(command);
//				break;
//			}
//			case HELP:/
//			{
//				printHelp();
//				break;
//			}
//			case LOOK:/
//			{
//				player.look();
//				break;
//			}
//			case QUIT:/
//			{
//				if (command.hasSecondWord())
//					gui.println("Quit quoi? Si tu veux quitter le jeux, écris juste quit");
//				else
//					endGame();
//				break;
//			}
//			case BACK:/
//			{
//				player.diminueSante(10);
//				player.back(command);
//				break;
//			}
//			case TEST:/
//			{
//				test();
//				break;
//			}
//			case CREDITS:/
//			{
//				credits();
//				break;
//			}
//			case TAKE:/
//			{
//				if (!command.hasSecondWord()) {
//					gui.println("Il faut préciser quel objet tu veux prendre!");
//				} else {
//					player.take(command);
//					printInventaire();
//				}
//				break;
//			}
//			case DROP:/
//			{
//				if (!command.hasSecondWord()) {
//					gui.println("Il faut préciser quel objet tu veux jeter!");
//				} else {
//					player.drop(command);
//					printInventaire();
//				}
//				break;
//			}
//			case ITEMS:/
//			{
//				printInventaire();
//				break;
//			}
//			case DRINK:/
//			{
//				if (!command.hasSecondWord()) 
//				{
//					gui.println("Tu veux boire quoi crétin?");
//				} 
//				else 
//				{
//					player.boire(command);
//					gui.println("Ton niveau de santé est maintenant de " + player.getSante());
//				}
//				break;
//			}
//			case TELEPORTER:/
//			{
//				if(beamer.getValueActivation())
//				{
//					player.teleporter(command);
//					beamer.setActivation(false);
//				}
//				else
//					gui.println("Mais c'est possible ça? Mon petit doigt me dit qu'il faut une pierre magique couplé avec la force surnaturelle de l'autel magique!");
//				break;
//			}
//			default: 
//			{
//				gui.processCommand();
//				break;
//			}
//		}
		
		
	}

	/**
	 * Affiche l'aide ainsi que les commandes possibles du jeu.
	 */
	public void printHelp() 
	{
		gui.println("Tu es perdu sur l'île, tu es entouré d'ennemis");
		gui.println("Cherche vite un abris ou de la nourriture.");
		gui.print("\n");
		gui.println(parser.showCommands());
		gui.print("\n");
	}

	public void printInventaire() 
	{
		gui.println(player.getInventaire());
		gui.println("Le poids total de ton inventaire est de "
				+ player.getPoidsInventaire() + " kg");
	}

	/**
	 * Retourne les informations des sorties possibles
	 * 
	 * @return Les sorties possibles
	 */

	public void printLocationInfo() 
	{
		Room currentRoom = player.getRoom();
		gui.println(currentRoom.getLongDescription());
		// printInventaire();
		gui.println("\n" + "Ta santé: " + player.getSante());
		
		chargerPierre(); //appelle la fonction charge pierre
		
	}
	
	/**
	 * Message d'acccueil à l'ouverture du jeux !
	 */
	private void printWelcome() 
	
	{
		gui.print("\n");
		gui.println("Bienvenue à Water Game!");
		gui.println("Prépare toi à jouer à THE jeu le plus merveilleux de l'univers");
		gui.println("Tape help si tu as besoin d'aide");
		gui.print("\n");
		gui.println(player.getLongDescriptionPlayer() + "\n");

		printLocationInfo();
		Room currentRoom = player.getRoom();
		gui.showImage(currentRoom.getImageName());
		gui.getJTextArea().append("\n" + "----------------------------------------------------------------------------" + "\n");
	}
	
	public void setGUI(UserInterface userInterface) 
	{
		gui = userInterface;
		printWelcome();
	}
	
	/**
	 * Une méthode test qui permet de tester tous les commandes du jeu en
	 * exécutant toutes les lignes d'un fichier de texte qui contient tous ces
	 * commandes. Cette méthode évite aux programmeurs de devoir tester à la
	 * main chaque commande du jeu.
	 */
//	public void test(Command command) 
//	{
//		try 
//		{
//			if(command.hasSecondWord())
//			{
//				String secondWord = command.getSecondWord();
//			
//				switch(secondWord)
//				{
//					case "gagner":
//					{
//						sr = new Scanner(new File("./src/Tests/gagner.txt"));
//						break;
//					}
//					case "commands":
//					{
//						sr = new Scanner(new File("./src/Tests/testCommand.txt"));
//						break;
//					}
//								
//				}			
//			
//				while (sr.hasNextLine()) 
//				{
//					interpretCommand(sr.nextLine());
//				}
//			}
//			else
//				gui.println("Je sais pas quoi tester....");
//		}
//					
//
//		catch (FileNotFoundException ex) 
//		{
//			gui.println("Fichier non trouvé");
//		}
//	}
	
}


