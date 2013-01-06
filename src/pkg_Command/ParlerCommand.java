package pkg_Command;

import pkg_Characters.Player;
import pkg_Dialogue.*;

/**
 * Cette classe gere la commande "parler" du jeu, qui permet au joueur d'interagir avec un personnage non joueur
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class ParlerCommand extends Command
{
	private DialogueCreeper1 dialogueCreeper1;
	private DialogueCreeper2 dialogueCreeper2;
	private DialogueCreeper3 dialogueCreeper3;
	private DialogueCreeper4 dialogueCreeper4;
	private DialogueEnderman dialogueEnderman;
	private DialogueBlaze dialogueBlaze;
	
	/**
	 * Constructeur qui permet de creer une commande Parler
	 * Instancier les differents dialogues avecs les ennemis
	 */
	public ParlerCommand()
	{
		dialogueCreeper1 = new DialogueCreeper1();
		dialogueCreeper2 = new DialogueCreeper2();
		dialogueCreeper3 = new DialogueCreeper3();
		dialogueCreeper4 = new DialogueCreeper4();
		dialogueEnderman = new DialogueEnderman();
		dialogueBlaze = new DialogueBlaze();
	}
	
	/**
	 * Affiche des dialogues avec les ennemis dans le jeu
	 */
	public void execute(Player player)
	{
		if(player.getRoom().getBot() != null)
		{
			String nomBot = player.getRoom().getBot().getNom();
			String nomRoom = player.getRoom().getNomRoom();
			
			//Les dialogues sont différents pour chaque ennemi
			switch(nomBot)
			{
				case("Creeper"):
				{
					//Le dialogue affiché dépend de la salle ou l'intéraction se passe
					switch(nomRoom)
					{
						case "foret":
						{
							dialogueCreeper1.afficheDialogue(player.getGameEngine());
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
			player.getGUI().println("Il n'y pas d'ennemis ici pour parler avec toi!");
		
		
	}
}
