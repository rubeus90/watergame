package pkg_Command;
import pkg_Characters.Player;



public class HelpCommand extends Command
{
	public HelpCommand()
	{}
	
	public void execute(Player player)
	{
		player.getGameEngine().printHelp();
	}
}
