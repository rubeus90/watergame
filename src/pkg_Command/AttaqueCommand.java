package pkg_Command;

import pkg_Characters.Bots;
import pkg_Characters.Player;

public class AttaqueCommand extends Command
{
	public AttaqueCommand()
	{}
		
	public void execute(Player player)
	{
		Bots bot = player.getRoom().getBot();
		if(bot != null)
		{
			if(bot.attaquable())
			{
				bot.diminueSante(40);
				player.diminueSante(20);
				player.getGUI().resetTextPanel();
				player.getGUI().println("La santé de " + bot.getNom() + ": " + bot.getSante());
				player.getGUI().println("Ta santé est de : " + player.getSante());
				
			}
			else
			{
				player.getGUI().createGameOver("creeper");
			}
		}
		else
		{
			player.getGUI().println("Il n'y a pas d'ennemis pour attaquer ici");
		}
		
	}
}
