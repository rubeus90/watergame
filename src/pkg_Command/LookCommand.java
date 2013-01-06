package pkg_Command;
import pkg_Characters.Player;

/**
 * Cette classe gère la commande "look" du jeu, qui affiche les informations de la salle ou le joueur se retrouve
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class LookCommand extends Command
{
	/**
	 * Constructeur qui permet de créer une commande Look
	 */
	public LookCommand()
	{}
	
	/**
	 * Afficher les informations complètes de la salle ou le joueur se retrouve
	 */
	public void execute(Player player)
	{
		player.getGUI().println(player.getRoom().getLongDescription());
	}
}
