package pkg_Command;

import pkg_Characters.Bots;
import pkg_Characters.Player;
import pkg_Dialogue.*;

public class ParlerCommand extends Command
{
	private DialogueCreeper1 dialogueCreeper1;
	private DialogueCreeper2 dialogueCreeper2;
	private DialogueEnderman dialogueEnderman;
	
	public ParlerCommand()
	{
		dialogueCreeper1 = new DialogueCreeper1();
		dialogueCreeper2 = new DialogueCreeper2();
		dialogueEnderman = new DialogueEnderman();
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
							
							//Juste pour debug le jeu
//							player.getGUI().println("" + dialogueCreeper1.getEtape());
//							player.getGUI().println("" + player.getGameEngine().gameResetted());
							
							break;
						}
						case "grotte":
						{
							dialogueCreeper2.afficheDialogue(player.getGameEngine());
							break;
						}
						default:{}
					}
					break;
				}
				case("Blaze"):
				{
					player.getGUI().println("A implementer");
					break;
				}
				case("Enderman"):
				{
					dialogueEnderman.afficheDialogue(player.getGameEngine());
					break;
				}
				default: {}
			}
		}
		else
			player.getGUI().println("Il n'y pas d'ennemis ici pour parler avec toi imbécile!");
		
		
	}
}
