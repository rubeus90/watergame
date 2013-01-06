package pkg_Command;
import pkg_Characters.Player;

/**
 * Cette classe gère la commande "items" du jeu, qui affiche l'inventaire du joueur
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class ItemsCommand extends Command
{
	/**
	 * Constructeur qui permet de créer une commande Items
	 */
	public ItemsCommand()
	{}
	
	/**
	 * Appeler la méthode printInventaire() de la classe GameEngine, qui affiche l'inventaire du joueur
	 */
	public void execute(Player player)
	{
		player.getGameEngine().printInventaire();
	}

}
