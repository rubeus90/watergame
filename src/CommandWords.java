import java.util.HashMap;
import java.util.Set;

/**
 * Cette classe fait parti du jeux " Water Games" "Water Games" est un jeux très
 * simple à prendre en main, c'est un jeux textuel.
 * 
 * Cette class énumé toutes les commandes que le jeux connait. Elle permet de
 * reconnaître les commandes quand elles sont saisies.
 * 
 */

public class CommandWords {
	// // a constant array that holds all valid command words
	// private static final String[] validCommands =
	// {
	// "credits", "take", "drop", "items", "boire"
	// };

	private HashMap<String, CommandWord> validCommands;

	public CommandWords() {
		validCommands = new HashMap<String, CommandWord>();

		for (CommandWord command : CommandWord.values())
			if (command != CommandWord.UNKNOWN)
				validCommands.put(command.toString(), command);

		// validCommands.put("go", CommandWord.GO);
		// validCommands.put("aller", CommandWord.GO);
		// validCommands.put("quit", CommandWord.QUIT);
		// validCommands.put("quitter", CommandWord.QUIT);
		// validCommands.put("help", CommandWord.HELP);
		// validCommands.put("aide", CommandWord.HELP);
		// validCommands.put("look", CommandWord.LOOK);
		// validCommands.put("regarder", CommandWord.LOOK);
		// validCommands.put("back", CommandWord.BACK);
		// validCommands.put("retourner", CommandWord.BACK);
		// validCommands.put("test", CommandWord.TEST);
		// validCommands.put("tester", CommandWord.TEST);
		// validCommands.put("credits", CommandWord.CREDITS);
		// validCommands.put("take", CommandWord.TAKE);
		// validCommands.put("prendre", CommandWord.TAKE);
		// validCommands.put("drop", CommandWord.DROP);
		// validCommands.put("jetter", CommandWord.DROP);
		// validCommands.put("items", CommandWord.ITEMS);
		// validCommands.put("inventaire", CommandWord.ITEMS);
		// validCommands.put("drink", CommandWord.DRINK);
		// validCommands.put("boire", CommandWord.DRINK);
	}
	

	/**
	 * Afficher tous les commandes
	 * 
	 * @return Tous les commandes valables
	 */
	public String getCommandlist() {
		String Commandlist = "Les commandes sont:";
		Set<String> keys = validCommands.keySet();
		for (String commands : keys) {
			Commandlist += " " + commands;
		}
		return Commandlist;
	}

	// /**
	// * Constructeur - initialise les commandes du jeux.
	// */
	// public CommandWords()
	// {
	// // nothing to do at the moment...
	// }

	/**
	 * Vérifie si la commande rentré par l'utilisateur est une commande valide.
	 * 
	 * @return vraie si la commande saisie est une commande valide, faux si ce
	 *         n'est pas le cas.
	 */
	// public boolean isCommand(String aString)
	// {
	// Set<String> keys = validCommands.keySet();
	// for(String commands : keys)
	// if(commands.equals(aString))
	// return true;
	//
	// // if we get here, the string was not found in the commands
	// return false;
	// }

	public boolean isCommand(CommandWord aCommands) 
	{
		Set<String> keys = validCommands.keySet();
		for (String commands : keys) {
			if (validCommands.get(commands) == aCommands)
				return true;
		}
		return false;
	}

	public CommandWord getCommandWord(String commandWord) 
	{
		CommandWord command = validCommands.get(commandWord);
		if (command != null) {
			return command;
		} else {
			return CommandWord.UNKNOWN;
		}
	}
}
