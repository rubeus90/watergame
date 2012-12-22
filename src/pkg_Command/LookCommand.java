package pkg_Command;
import pkg_Characters.Player;



public class LookCommand extends Command
{
	public LookCommand()
	{}
	
	public void execute(Player player)
	{
		player.getGUI().println(player.getRoom().getLongDescription());
	}
}
