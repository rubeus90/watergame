package pkg_Command;

import pkg_Characters.Player;
import pkg_Room.Room;

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
			
			//Enlever la salle de l'ArrayList, comme ça à chaque fois on veut utiliser le Beamer, il faut le recharger
			player.getBeamerRoom().clear();
		}
		else
		{
			player.getGUI().println("Tu n'as pas encore chargé le beamer");
		}
	}
}
