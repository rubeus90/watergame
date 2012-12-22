package pkg_Command;
import pkg_Characters.Player;
import pkg_Room.Room;
import pkg_Dialogue.*;



public class GoCommand extends Command
{
	
	public GoCommand()
    {
    }

    public void execute(Player player)
    {
    	player.diminueSante(10);
		player.setMaxPoids();
		
		
		if (!this.hasSecondWord()) 
		{
			player.getGUI().println("Aller ou exactement, soit un peu plus précis!");
		}

		// Stocker la salle actuelle dans le stack (pour la méthode back)
		player.getStackRoom().push(player.getRoom());

		String direction = this.getSecondWord();

		Room nextRoom = player.getRoom().getExit(direction);

		if (nextRoom == null) 
		{
			player.getGUI().println("Tu ne peux pas aller par là, soit il n'y a rien ici, soit le chemin que tu viens de prendre est sens unique");
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
		}
		
		player.getGUI().resetTextPanel();
		
	}
}
