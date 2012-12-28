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
				if(player.getItemListe().containsKey("epee"))
				{
					bot.diminueSante(80);
					player.diminueSante(20);
					player.getGUI().resetTextPanel();
					player.getGUI().println("La santé de " + bot.getNom() + ": " + bot.getSante());
					player.getGUI().println("Ta santé est de : " + player.getSante());
				}
				else if(player.getItemListe().containsKey("hallebarde"))
				{
					bot.diminueSante(180);
					player.diminueSante(20);
					player.getGUI().resetTextPanel();
					player.getGUI().println("La santé de " + bot.getNom() + ": " + bot.getSante());
					player.getGUI().println("Ta santé est de : " + player.getSante());
				}
				else
				{
					bot.diminueSante(40);
					player.diminueSante(20);
					player.getGUI().resetTextPanel();
					player.getGUI().println("La santé de " + bot.getNom() + ": " + bot.getSante());
					player.getGUI().println("Ta santé est de : " + player.getSante());
					
					if(player.getRoom().getBot().getNom() == "Enderman" || player.getRoom().getBot().getNom() == "Blaze") 
					{
						player.getGUI().println("\n" + "\n" + "Peut-être cet ennemis est trop fort pour toi. Il te faut d'autres armes plus puissantes. Enfuis-toi avant qu'il ne te tue!!!!");
					}
				}				
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
