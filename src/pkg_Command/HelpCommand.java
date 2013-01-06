package pkg_Command;
import pkg_Characters.Player;

/**
 * Cette classe gère la commande "help" du jeu, qui affiche les commandes du jeu ainsi que quelques 
 * phrases d'aide.
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class HelpCommand extends Command
{
	/**
	 * Constructeur qui permet de créer une commande Help
	 */
	public HelpCommand()
	{}
	
	/**
	 * Appeler la méthode printHelp() de la classe GameEngine, qui affiche les commandes disponibles et les phrases d'aide 
	 * du jeu
	 */
	public void execute(Player player)
	{
		player.getGameEngine().printHelp();
	}
}
