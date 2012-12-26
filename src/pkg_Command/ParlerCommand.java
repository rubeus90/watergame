package pkg_Command;

import pkg_Characters.Bots;
import pkg_Characters.Player;
import pkg_Dialogue.*;

public class ParlerCommand extends Command
{
	public ParlerCommand()
	{}
	
	public void execute(Player player)
	{
		if(player.getRoom().getBot() != null)
		{
			String nomBot = player.getRoom().getBot().getNom();
			String nomRoom = player.getRoom().getNomRoom();
			
			switch(nomBot)
			{
				case("Creeper"):
				{
					switch(nomRoom)
					{
						case "foret":
						{
							Dialogue dialogue = new DialogueCreeper1();
							dialogue.afficheDialogue(player.getGameEngine());
							break;
						}
						case "grotte":
						{
							player.getGUI().println("A implementer");
							break;
						}
						default:{}
					}
					break;
				}
				default: {}
			}
		}
		else
			player.getGUI().println("Il n'y pas d'ennemis ici pour parler avec toi imbécile!");
		
		
	}
}
