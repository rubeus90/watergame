package pkg_Command;

import pkg_Characters.Player;

public class UtiliserCommand extends Command
{
	public UtiliserCommand()
	{}
	
	public void execute(Player player)
	{
		if(!player.getBeamerRoom().isEmpty())
		{
			player.setRoom(player.getBeamerRoom().get(0));
			player.getGameEngine().printLocationInfo();

			if (player.getRoom().getImageName() != null) 
			{
				player.getGUI().showImage(player.getRoom().getImageName());
			}
			
			player.getGUI().resetTextPanel();
			player.getGUI().println("Tu as été téléporter à l'endroit ou le Beamer était chargé. Pour le réutiliser de nouveau," +
					" il faut que tu le recharge");
			
			//Enlever la salle de l'ArrayList, comme ça à chaque fois on veut utiliser le Beamer, il faut le recharger
			player.getBeamerRoom().clear();
		}
		else
		{
			player.getGUI().println("Tu n'as pas encore chargé le beamer");
		}
	}
}
