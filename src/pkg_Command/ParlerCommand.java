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
							//Si le jeu est recommencé, on remet le compteur des étapes de dialogue à 1
							if(player.getGameEngine().gameResetted() && dialogueCreeper1.getEtape() != 1)
							{
								dialogueCreeper1.setEtape(1);
								dialogueCreeper1.afficheDialogue(player.getGameEngine());
								
								/*on remet la valeur de gameResetted à false, donc quand on fait suivant, on va tomber dans
								 * la condition de else, comme ça la valeur de l'étape n'est pas réinitialisée à 1 à chaque fois
								 */
								player.getGameEngine().setResetGame(); 
							}
							else
							{
								dialogueCreeper1.afficheDialogue(player.getGameEngine());
							}
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
				case("Blaze"):
				{
					player.getGUI().println("A implementer");
					break;
				}
				default: {}
			}
		}
		else
			player.getGUI().println("Il n'y pas d'ennemis ici pour parler avec toi imbécile!");
		
		
	}
}
