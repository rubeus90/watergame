package pkg_Command;

import pkg_Characters.Bots;
import pkg_Characters.Player;
import pkg_Dialogue.*;

public class ParlerCommand extends Command
{
	private DialogueCreeper1 dialogueCreeper1;
	private DialogueCreeper2 dialogueCreeper2;
	private DialogueCreeper3 dialogueCreeper3;
	private DialogueCreeper4 dialogueCreeper4;
	private DialogueEnderman dialogueEnderman;
	private DialogueBlaze dialogueBlaze;
	
	public ParlerCommand()
	{
		dialogueCreeper1 = new DialogueCreeper1();
		dialogueCreeper2 = new DialogueCreeper2();
		dialogueCreeper3 = new DialogueCreeper3();
		dialogueCreeper4 = new DialogueCreeper4();
		dialogueEnderman = new DialogueEnderman();
		dialogueBlaze = new DialogueBlaze();
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
						case "temple":
						{
							dialogueCreeper3.afficheDialogue(player.getGameEngine());
							break;
						}
						case "secret":
						{
							dialogueCreeper4.afficheDialogue(player.getGameEngine());
							break;
						}
						default:{}
					}
					break;
				}
				case("Blaze"):
				{
					dialogueBlaze.afficheDialogue(player.getGameEngine());
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
