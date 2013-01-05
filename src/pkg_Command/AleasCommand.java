package pkg_Command;

import pkg_Characters.Player;
import pkg_Room.Room;
import pkg_Room.TransporterRoom;

public class AleasCommand extends Command
{
	public AleasCommand()
	{}
	
	public void execute(Player player)
	{
		TransporterRoom secret = player.getGameEngine().getTransporterRoom();
		
		if (!this.hasSecondWord()) 
		{
			secret.setTestMode(false);
		}
		else
		{			
			secret.setTestMode(true);
			Room room = player.getGameEngine().chooseRoom(this.getSecondWord());
			secret.setTestModeRoom(room);
		}
	}
}
