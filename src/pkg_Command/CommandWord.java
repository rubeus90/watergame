package pkg_Command;

/**
 * Cette classe est une enumeration des commandes du jeu
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public enum CommandWord {
	GO("go"), QUIT("quit"), HELP("help"), LOOK("look"), BACK("back"), TEST("test"), TAKE("take"), DROP("drop"), ITEMS("items"), 
	DRINK("drink"), UNKNOWN("?"), TELEPORTER("teleporter"), ATTAQUE("attaque"), 
	CHARGER("charger"), UTILISER("utiliser"), PARLER("parler"), ALEAS("aleas");

	private String commandString;

	/**
	 * Imposer la String de commande
	 * 
	 * @param commandString
	 */
	CommandWord(String commandString) 
	{
		this.commandString = commandString;
	}

	/**
	 * Retourner les valeurs de l'enum sous forme de string
	 * 
	 * @return le String des commandes
	 */
	@Override
	public String toString() 
	{
		return commandString;
	}

}
