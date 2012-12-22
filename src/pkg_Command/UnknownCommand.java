package pkg_Command;
import pkg_Characters.Player;



public class UnknownCommand extends Command
{
	public UnknownCommand()
	{}
	
	public void execute(Player player)
	{
		player.getGUI().println("Je ne comprend pas ce que tu veux faire...");
	}
}
