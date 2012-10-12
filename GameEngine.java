
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
    private Room currentRoom;
    private UserInterface gui;
        
    /**
     * Créé le jeux et initialiser la carte.
     */
    public GameEngine() 
    {
        createRooms();
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
    private void createRooms()
    {
        Room foret, grotte, montagne, plaine, temple, plage;
      
        // create the rooms
        foret = new Room(" dans la forêt au nord-ouest de l'île", "foret.png");
        grotte = new Room("dans la grotte au nord de l'île", "grotte.jpg" );
        montagne = new Room("dans les montagnesau nord-est de l'île", "montagne.png" );
        plaine = new Room("à la plaine à l'ouest de l'île", "plaine.jpg");
        temple = new Room("dans le temple au centre de l'île", "plaine.jpg");
        plage = new Room("à la plage au sud de l'île", "plage.jpeg");
        
        // initialise room exits
        foret.setExit("est", grotte);
        foret.setExit("sud", plaine);
        grotte.setExit("ouest", foret);
        grotte.setExit("monter", montagne);
        grotte.setExit("sud", temple);
        montagne.setExit("ouest", grotte);
        plaine.setExit("nord", foret);
        plaine.setExit("est", temple);
        temple.setExit("nord", grotte);
        temple.setExit("sud", plage);
        temple.setExit("ouest", plaine);
        plage.setExit("nord", temple);

        currentRoom = temple;  // start game outside
    }

    /**
     *  Déroulement du jeux. Boucle infinie, jusqu'à ce que la command quit soit ecrite.
    
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Rooooh tu veux plus jouer déjà? Bon ben bye bye alors");
    }

    /**Retourne les informations des sorties possibles
     * @return Les sorties possibles
     */
    
    private void printLocationInfo()
    {
//        System.out.println("Tu es " + currentRoom.getDescription());
//        
//        System.out.println(currentRoom.getExitString());
    	
    	gui.println(currentRoom.getLongDescription());
    }
    
    
    /**Redonner les informations de la salle et les sorties disponibles
     */
    private void look()
    {
    	gui.println(currentRoom.getLongDescription());
    }
    
    /**Renvoi ,pour le moment, un message au joueur l'indiquant qu'il a déjà mangé.
     * 
     */
    public void eat()
    {
    	gui.println("Tu as déjà mangé, tu n'as plus faim");
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
                
        printLocationInfo();
        gui.showImage(currentRoom.getImageName());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command La commande à analyser.
     * @return Vrai si la commande ferme le jeux, faux autrement.
     */
  public void interpretCommand(String commandLine) 
    {
        gui.println(commandLine);
        Command command = parser.getCommand(commandLine);

        if(command.isUnknown()) {
            gui.println("Je ne comprend pas ce que tu veux faire...");
            return;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("eat"))
            eat();
        else if (commandWord.equals("look"))
            look();   
        else if (commandWord.equals("quit")) {
            if(command.hasSecondWord())
                gui.println("Quit quoi? Si tu veux quitter le jeux, écris juste quit");
            else
                endGame();
        }
    }

    // implementations of user commands:

    /**
     * Affiche l'aide ainsi que les commandes possibles du jeux.
     */
    private void printHelp() 
    {
        gui.println("Tu es perdu sur l'île, tu es entouré d'ennemis");
        gui.println("Cherche vite un abris ou de la nourriture");
        gui.print("\n");
        gui.println (parser.showCommands() );
        gui.print("\n");
    }

    /** 
     * Procédure pour passer d'une salle à une autre. Si il n'y a pas de sortie, entré un nouvelle direction. 
     * Sinon affiché un message d'erreur.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gui.println("Aller ou exactement, soit un peu plus précis!");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            gui.println("Il n'y a rien ici, tu vas tomber dans l'eau si tu continues!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
                if(currentRoom.getImageName() != null)
                gui.showImage(currentRoom.getImageName());
        }
    }

    /** 
     * Si "Quit" a été tapé par l'utilisateur, vérifié le reste de la commande pour voir si il veux vraiment quitter le jeux.
     * @return Vrai, si la commande quit a été correctement tapé, sinon retourne Faux.
     */
private void endGame()
    {
        gui.println("Merci d'avoir jouer, à Bientôt");
        gui.enable(false);
    }
}
