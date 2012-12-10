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
// import java.io.IOException;


/**
 * Cette class est la Class principale du jeux "Water Games".
 * "Water Games" est un jeux très simple à prendre en main, c'est un jeux textuel.   
 * 
 *  Pour joueur à ce superbe jeux, créé un instance de cette classé et appelé la méthode "play"
 * 
 *  Cette class créé et initialise toutes les autres classes. 
 *  Elle créé les Salles, le Parser et démarre le jeux.
 *  Elles évalue aussi et exécute les commandes que la class Parser renvoi.
 * 
 */

public class GameEngine 
{
    private Parser parser;
//    private Room currentRoom;
    private UserInterface gui;
//    private Stack<Room> salles;
    private Player player;
      
    
        
    /**
     * Créé le jeux et initialiser la carte.
     */
    public GameEngine(final Player pPlayer) 
    {
    	player = pPlayer;
        createGame();
        parser = new Parser();
    }
    
    public void setGUI(UserInterface userInterface)
    {
        gui = userInterface;
        printWelcome();
    }

       
    /**
     * Créé toutes les pièces et les liens entre chacunes.
     */
    private void createGame()
    {
        Room foret, grotte, montagne, plaine, temple, plage;
      
        // create the rooms
        foret = new Room(" dans la forêt au nord-ouest de l'île", "images/foret.png");
        grotte = new Room("dans la grotte au nord de l'île", "images/grotte.jpg" );
        montagne = new Room("dans les montagnesau nord-est de l'île", "images/montagne.png" );
        plaine = new Room("à la plaine à l'ouest de l'île", "images/plaine.jpg");
        temple = new Room("dans le temple au centre de l'île", "images/temple.png");
        plage = new Room("à la plage au sud de l'île", "images/plage.jpeg");
        
        // initialise room exits
        foret.setExit("est", grotte);
        foret.setExit("sud", plaine);
        grotte.setExit("ouest", foret);
        grotte.setExit("monter", montagne);
//        grotte.setExit("sud", temple);
        montagne.setExit("ouest", grotte);
        plaine.setExit("nord", foret);
        plaine.setExit("est", temple);
        temple.setExit("nord", grotte);
        temple.setExit("sud", plage);
        temple.setExit("ouest", plaine);
        plage.setExit("nord", temple);
        
        
        foret.addItem("hache", new Item("une petite hache toute pourrie", 40));
        plaine.addItem("sabre", new Item("un sabre lumineux", 30));
        grotte.addItem("massue", new Item("une grande massue", 45));
        plage.addItem("filet", new Item("un grand filet", 50));
        temple.addItem("arc", new Item("un arc en bois", 400));
        temple.addItem("torche", new Item("une petite torche", 10));
        
        temple.addPotion(new Potion("Potion"));
        temple.addPotion(new Potion("Soin"));
               

//        currentRoom = temple;  // le jeu commence au temple       
        
//        salles = new Stack<Room>();  /*créer un Stack vide pour la méthode back, cette création est mise ici car si on la crée dans la méthode, lorsque 
//        l'on exécute la commande back dès le début du jeu, ça génère des exceptions*/
        
//        //On crée le joueur
//        player = new Player("Julie","f");    
        player.setCurrentRoom(temple);        
    }
    
    
    
    

    /**Retourne les informations des sorties possibles
     * @return Les sorties possibles
     */
    
    public void printLocationInfo()
    {   
    	Room currentRoom = player.getRoom();
        gui.println(currentRoom.getLongDescription());
//        printInventaire();
        gui.println("Ta santé: " + player.getSante());   
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
    }
    
    
    /**
     * Affiche l'aide ainsi que les commandes possibles du jeu.
     */
    private void printHelp() 
    {
        gui.println("Tu es perdu sur l'île, tu es entouré d'ennemis");
        gui.println("Cherche vite un abris ou de la nourriture.");
        gui.print("\n");
        gui.println (parser.showCommands() );
        gui.print("\n");
    }
    
    public void printInventaire()
    {
    	gui.println(player.getInventaire());
    	gui.println("Le poids total de ton inventaire est de " + player.getPoidsInventaire() + "kg");
    }

    /**
     * Quand une commande est enregistrée, excécuter l'action associée à cette commande.
     * Si la commande n'est pas dans la liste des commandes disponibles, afficher une message d'erreur.
     * 
     * @param command La commande à analyser.
     * 
     */
    public void interpretCommand(Command command) 
    {
//        gui.println(commandLine);
//        Command command = parser.getCommand(commandLine);
        CommandWord commandWord = command.getCommandWord();
        gui.println(command.toString());
        
        switch(commandWord)
        {
        	default:
        		gui.processCommand();
//        
        	case UNKNOWN:
        	{
        		gui.println("Je ne comprend pas ce que tu veux faire...");
//              return;
        		break;
        	}
        	case HELP: 
        		{
        			printHelp(); 
        			break;
        		}
        	case GO:
        	{
        		player.diminueSante(10);
            	player.setMaxPoids();
            	player.goRoom(command);
            	break;
        	}
        	case LOOK: 
        		{
        			player.look();
        			break;
        		}
        	case BACK:
        	{
        		player.diminueSante(10);
            	player.back(command);
            	break;
        	}
        	case QUIT:
        	{
        		if(command.hasSecondWord())
                    gui.println("Quit quoi? Si tu veux quitter le jeux, écris juste quit");
                else
                    endGame();
        		break;
        	}
        	case TEST: 
        		{
        			test();
        			break;
        		}
        	case CREDITS: 
        		{
        			credits();
        			break;
        		}
        	case TAKE:
        	{
        		if(!command.hasSecondWord())
            	{
            		gui.println("Il faut préciser quel objet tu veux prendre!");
            	}
            	else
            	{
            		player.take(command);
            	}
        		break;
        	}
        	case DROP:
        	{
        		if(!command.hasSecondWord())
            	{
            		gui.println("Il faut préciser quel objet tu veux jeter!");
            	}
            	else
            	{
            		player.drop(command);
            	}
        		break;
        	}
        	case ITEMS: 
        		{
        			printInventaire();
        			break;
        		}
        	case DRINK:
        	{
        		if(!command.hasSecondWord())
            	{
            		gui.println("Tu veux boire quoi crétin?");
            	}
            	else
            	{
            		player.boire(command);
            		gui.println("Ton niveau de santé est maintenant de " + player.getSante());        
            	}
        		break;
        	}
        }
    }
    

    
    /**
     * Une méthode test qui permet de tester tous les commandes du jeu en exécutant toutes les lignes d'un fichier de texte 
     * qui contient tous ces commandes. Cette méthode évite aux programmeurs de devoir tester à la main chaque commande du jeu.
     */
    public void test()
    {
        try
        {
            Scanner sr = new Scanner(new File("Tests/testCommand.txt") );
            
            while( sr.hasNextLine() )
            {
            	String line = sr.nextLine();
            	Command command = parser.getCommand(line);
            	
                interpretCommand( command );
            }
            
    }
        catch(FileNotFoundException ex) 
        {
            gui.println("Fichier non trouvé");
        }
   }
   
   public void credits()
   {
       gui.println("Nous sommes NGUYEN Hong Ngoc aka Ngocky et PATOIS Thibault, deux étudiants très brillants en 3ème de l'ESIEE (LES E3S EN FORCE!!!!). Nous avons crée ce jeu dans le cadre de l'apprentissage de Java, mais nous avons mis là dedans tout notre amour (et n'oublie pas Thibault qui a mis tout son coeur dans les nombreuses versions de notre page web), donc j'espère que tu vas aimer notre jeu :D");
   }

   /**Terminer le jeu en fermant la fenetre de jeu
    * 
    */
   public void endGame()
   {
	   gui.killFrame();
   }
   
//   public void gameOver()
//   {
//	   gui.createGameOver();
//   }

}
