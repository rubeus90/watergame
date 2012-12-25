package pkg_Command;
public enum CommandWord {
	GO("go"), QUIT("quit"), HELP("help"), LOOK("look"), BACK("back"), TEST("test"), 
	CREDITS("credits"), TAKE("take"), DROP("drop"), ITEMS("items"), 
	DRINK("drink"), UNKNOWN("?"), TELEPORTER("teleporter"), ATTAQUE("attaque"), 
	CHARGER("charger"), UTILISER("utiliser");

	private String commandString;

	CommandWord(String commandString) 
	{
		this.commandString = commandString;
	}

	@Override
	public String toString() 
	{
		return commandString;
	}

}
