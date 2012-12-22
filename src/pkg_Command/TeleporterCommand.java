package pkg_Command;
import pkg_Characters.Player;
import pkg_Room.Room;



public class TeleporterCommand extends Command
{
	public TeleporterCommand()
	{}
	
	public void execute(Player player)
	{
		if(player.getGameEngine().getBeamer().getValueActivation())
		{
			if (!this.hasSecondWord()) 
			{
				player.getGUI().println("Il faut préciser ou tu veux être téléporté!");
			}

			// Stocker la salle actuelle dans le stack (pour la méthode back)
			player.getStackRoom().push(player.getRoom());

			String direction = this.getSecondWord();

			Room nextRoom = player.getRoom().getExit(direction);

			if (nextRoom == null) 
			{
				player.getGUI().println("L'endroit que tu viens de demander n'existe pas, ou tu peux pas te téléporter là-bas!");
			} 
			else 
			{
				player.setRoom(nextRoom);
				// player.setCurrentRoom(nextRoom);
				player.getGameEngine().printLocationInfo();

				if (player.getRoom().getImageName() != null) 
				{
					player.getGUI().showImage(player.getRoom().getImageName());
				}
				
				player.getGUI().resetTextPanel();
			}			
			
			player.getGameEngine().getBeamer().setActivation(false);
		}
		else
			player.getGUI().println("Mais c'est possible ça? Mon petit doigt me dit qu'il faut une pierre magique couplé avec la force surnaturelle de l'autel magique!");
	}
}
