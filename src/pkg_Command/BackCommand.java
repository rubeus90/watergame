package pkg_Command;
import pkg_Characters.Player;




public class BackCommand extends Command
{
	public BackCommand()
	{}
	
	public void execute(Player player)
	{
		player.diminueSante(10);		
		
		if (this.hasSecondWord()) 
		{
			player.getGUI().println("Tu ne peux pas revenir ou tu veux, c'est pas la fête ici!");
		} 
		else 
		{
			if (player.getStackRoom().empty() == true)
				player.getGUI().println("Tu es revenu au début du jeu!");
			else 
			{
				player.setRoom(player.getStackRoom().pop());
				player.getGameEngine().printLocationInfo();

				if (player.getRoom().getImageName() != null)
					player.getGUI().showImage(player.getRoom().getImageName());
				
				player.getGUI().resetTextPanel();
			}
		}
	}
}
