package pkg_Command;

import pkg_Characters.Player;

public class ChargerCommand extends Command
{
	public ChargerCommand()
	{}
	
	public void execute(Player player)
	{
		player.getBeamerRoom().add(0, player.getRoom());
		player.getGUI().println("Tu as charge le beamer ici");
	}
}
