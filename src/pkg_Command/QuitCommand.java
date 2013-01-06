package pkg_Command;
import pkg_Characters.Player;

/**
 * Cette classe gere la commande "quit" du jeu, qui permet à l'utilisateur de quiter le jeu.
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class QuitCommand extends Command
{
	/**
	 * Constructeur qui permet de creer une commande Quit
	 */
	public QuitCommand()
	{}
	
	/**
	 * Terminer le jeu et fermer la fenetre de jeu
	 */
	public void execute(Player player)
	{
		if (this.hasSecondWord())
			player.getGUI().println("Quit quoi? Si tu veux quitter le jeux, écris juste quit");
		else
			player.getGameEngine().endGame();
	}
}
