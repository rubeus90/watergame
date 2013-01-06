package pkg_Command;
import java.util.HashMap;
import java.util.Set;


/**
 * Cette class énumé toutes les commandes que le jeux connait. Elle permet de
 * reconnaître les commandes quand elles sont saisies.
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 * 
 */

public class CommandWords 
{
	private HashMap<CommandWord, String> validCommands;
	private HashMap<String, Command> commands;

	/**
	 * Instancier 2 HashMap, la première qui relie le String des commandes et les commandes correspondantes.
	 * La deuxième HashMap relie le String de la commande à la classe de commmande associée
	 */
	public CommandWords() 
	{
		validCommands = new HashMap<CommandWord, String>();

		for (CommandWord command : CommandWord.values())
			validCommands.put(command, command.toString());
		
		commands = new HashMap<String, Command>();
        commands.put(validCommands.get(CommandWord.GO), new GoCommand());
        commands.put(validCommands.get(CommandWord.HELP), new HelpCommand());
        commands.put(validCommands.get(CommandWord.QUIT), new QuitCommand());
        commands.put(validCommands.get(CommandWord.LOOK), new LookCommand());
        commands.put(validCommands.get(CommandWord.BACK), new BackCommand());
        commands.put(validCommands.get(CommandWord.TEST), new TestCommand());
        commands.put(validCommands.get(CommandWord.TAKE), new TakeCommand());
        commands.put(validCommands.get(CommandWord.DROP), new DropCommand());
        commands.put(validCommands.get(CommandWord.ITEMS), new ItemsCommand());
        commands.put(validCommands.get(CommandWord.DRINK), new DrinkCommand());
        commands.put(validCommands.get(CommandWord.UNKNOWN), new UnknownCommand());
        commands.put(validCommands.get(CommandWord.TELEPORTER), new TeleporterCommand());
        commands.put(validCommands.get(CommandWord.CHARGER), new ChargerCommand());
        commands.put(validCommands.get(CommandWord.UTILISER), new UtiliserCommand());
        commands.put(validCommands.get(CommandWord.ATTAQUE), new AttaqueCommand());
        commands.put(validCommands.get(CommandWord.PARLER), new ParlerCommand());
        commands.put(validCommands.get(CommandWord.ALEAS), new AleasCommand());
   	}
	

	/**
	 * Afficher tous les commandes
	 * 
	 * @return tous les commandes valables
	 */
	public String getCommandlist() {
		String Commandlist = "Les commandes sont:";
		Set<CommandWord> keys = validCommands.keySet();
		for (CommandWord commands : keys) {
			Commandlist += " " + commands.toString();
		}
		return Commandlist;
	}

	
	/**
	 * Vérifie si la commande rentré par l'utilisateur est une commande valide.
	 * 
	 * @return vraie si la commande saisie est une commande valide, faux si ce
	 *         n'est pas le cas.
	 */
	public boolean isCommand(CommandWord aCommands) 
	{
		Set<CommandWord> keys = validCommands.keySet();
		for (CommandWord commands : keys) {
			if (commands == aCommands)
				return true;
		}
		return false;
	}

	/**
	 * Retourner la commande correspondante au mot rentré par le joueur.
	 * Retourner une commande inconnue quand le mot rentré n'est pas une commande valable
	 * 
	 * @param word
	 * @return la commande correspondante avec le mot saisi
	 */
	public Command get(String word)
    {
		if(commands.containsKey(word))
		{
			return (Command)commands.get(word);
		}
		else
			return new UnknownCommand();
    }
}
