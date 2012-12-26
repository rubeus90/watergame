package pkg_Command;

import pkg_Characters.Bots;
import pkg_Characters.Player;
import pkg_Dialogue.*;

public class ParlerCommand extends Command
{
	private Dialogue dialogueCreeper1;
	
	public ParlerCommand()
	{
		dialogueCreeper1 = new DialogueCreeper1();
	}
	
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
							dialogueCreeper1.afficheDialogue(player.getGameEngine());
							dialogueCreeper1.suivant();
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
