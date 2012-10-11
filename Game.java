
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

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Créé le jeux et initialiser la carte.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Créé toutes les pièces et les liens entre chacunes.
     */
    private void createRooms()
    {
        Room foret, grotte, montagne, plaine, temple, plage;
      
        // create the rooms
        foret = new Room(" dans la forêt au nord-ouest de l'île");
        grotte = new Room("dans la grotte au nord de l'île");
        montagne = new Room("dans les montagnesau nord-est de l'île");
        plaine = new Room("à la plaine à l'ouest de l'île");
        temple = new Room("dans le temple au centre de l'île");
        plage = new Room("à la plage au sud de l'île");
        
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
     */
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
    	
    	System.out.println(currentRoom.getLongDescription());
    }
    
    
    /**Redonner les informations de la salle et les sorties disponibles
     */
    private void look()
    {
    	System.out.println(currentRoom.getLongDescription());
    }
    
    /**Renvoi ,pour le moment, un message au joueur l'indiquant qu'il a déjà mangé.
     * 
     */
    private void eat()
    {
    	System.out.println("Tu as déjà mangé, tu n'as plus faim");
    }
    
    /**
     * Message d'acccueil à l'ouverture du jeux !
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bienvenue à Water Game!");
        System.out.println("Prépare toi à jouer à THE jeu le plus merveilleux de l'univers");
        System.out.println("Tape help si tu as besoin d'aide");
        System.out.println();
                
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command La commande à analyser.
     * @return Vrai si la commande ferme le jeux, faux autrement.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("Je ne comprend pas ce que tu veux faire...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            printHelp();
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("quit"))
            wantToQuit = quit(command);
        else if (commandWord.equals("look"))
        	look();
        else if (commandWord.equals("eat"))
        	eat();

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Affiche l'aide ainsi que les commandes possibles du jeux.
     */
    private void printHelp() 
    {
        System.out.println("Tu es perdu sur l'île, tu es entouré d'ennemis");
        System.out.println("Cherche vite un abris ou de la nourriture");
        System.out.println();
        System.out.println (parser.showCommands() );
        System.out.println();
    }

    /** 
     * Procédure pour passer d'une salle à une autre. Si il n'y a pas de sortie, entré un nouvelle direction. 
     * Sinon affiché un message d'erreur.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Aller ou exactement, soit un peu plus précis!");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Il n'y a rien ici, tu vas tomber dans l'eau si tu continues!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /** 
     * Si "Quit" a été tapé par l'utilisateur, vérifié le reste de la commande pour voir si il veux vraiment quitter le jeux.
     * @return Vrai, si la commande quit a été correctement tapé, sinon retourne Faux.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit quoi? Si tu veux quitter le jeux, écris juste quit");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
