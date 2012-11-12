import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Cette classe fait parti du jeux " Water Games" 
 * "Water Games" est un jeux très simple à prendre en main, c'est un jeux textuel.
 * 
 * La class Parser ligne les entrées utilisateurs et essaye de les convertir en commandes pour le jeux.
 * A Chaque fois qu'elle est appelée, la class Parser lit les lignes du terminal et les découpes en deux mots qui sont deux commandes.
 * Cette class permet ensuite de convertir les commandes en un objet de la class Command.
 *
 * La class Parser a un nombre déterminé de commande.
 * La class Parser permet de comparer les entrés du joueur avec les commandes connues et 
 * si la commande est inconnu, elle revoie un objet signé comme une command invalide.
 * 
 */
public class Parser 
{
    private CommandWords commands;  // holds all valid command words
    private Scanner reader;         // source of command input

    /**
     * Constructeur qui permet de créé un parser qui va lire les entrées.
     */
    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }
    

    /**
     * @return The next command from the user.
     */
    public Command getCommand(String inputLine) 
    {
        //String inputLine = "";   // will hold the full input line
        String word1;
        String word2;

        StringTokenizer tokenizer = new StringTokenizer(inputLine);

        if(tokenizer.hasMoreTokens())
            word1 = tokenizer.nextToken();      // get first word
        else
            word1 = null;
        if(tokenizer.hasMoreTokens())
            word2 = tokenizer.nextToken();      // get second word
        else
            word2 = null;

        // note: we just ignore the rest of the input line.

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).

        if(commands.isCommand(word1))
            return new Command(word1, word2);
        else
            return new Command(null, word2);
    }
    
    public String showCommands()
    {
        return commands.getCommandlist();
    }
}
