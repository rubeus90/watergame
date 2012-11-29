/**
 * Cette classe fait parti du jeux " Water Games" 
 * "Water Games" est un jeux très simple à prendre en main, c'est un jeux textuel.  
 * 
 * Cette class énumé toutes les commandes que le jeux connait.
 * Elle permet de reconnaître les commandes quand elles sont saisies.
 *
 */

public class CommandWords
{
    // a constant array that holds all valid command words
    private static final String[] validCommands = 
    {
        "go", "quit", "help", "look", "eat", "back", "test", "credits", "take", "drop", "items", "boire"
    };
    
    
    /**Afficher tous les commandes
     * @return Tous les commandes valables
     */
    public String getCommandlist()
    {
       String Commandlist = "Les commandes sont:";
        for(String commands : validCommands) 
        {
            Commandlist += " " + commands;
        }
        return Commandlist;
    }
   

    /**
     * Constructeur - initialise les commandes du jeux.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Vérifie si la commande rentré par l'utilisateur est une commande valide. 
     * @return vraie si la commande saisie est une commande valide,
     * faux si ce n'est pas le cas.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
}
