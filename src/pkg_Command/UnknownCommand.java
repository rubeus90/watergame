package pkg_Command;
import pkg_Characters.Player;

/**
 * Cette classe gere les commandes non valables
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class UnknownCommand extends Command
{
	/**
	 * Constructeur qui cree une commande inconnue du jeu
	 */
	public UnknownCommand()
	{}
	
	/**
	 * Afficher un message qui indique que la commande entree par l'utilisateur n'est pas une commande valable du jeu
	 */
	public void execute(Player player)
	{
		player.getGUI().println("Je ne comprend pas ce que tu veux faire...");
	}
}
