package pkg_Command;
import pkg_Characters.Player;

/**
 * Cette classe gere la commande "back" du jeu
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class BackCommand extends Command
{
	/**
	 * Constructeur qui permet de creer une commande Back
	 */
	public BackCommand()
	{}
	
	/**
	 * Implementer la commande Back pour retourner dans la salle precedente. On stocke les salles visitee 
	 * precedemment dans une Stack, a chaque fois on veut revenir sur notre pas, on utilise la commande "pop" 
	 * qui prendre la deniere valeur de la Stack (donc la derniere salle visitee) et qui retire cette salle de la Stack. 
	 * Quand la Stack est vide, on est revenu au debut du jeu.
	 */
	public void execute(Player player)
	{
		/*A chaque fois que le joueur utilise la commande Back, sa santé diminue, dans le but de limiter le nombre de
		 * mouvements possible du joueur
		 */
		player.diminueSante(2);		
		
		if (this.hasSecondWord()) 
		{
			player.getGUI().println("Tu ne peux pas revenir ou tu veux, c'est pas la fête ici!");
		} 
		else 
		{
			if (player.getStackRoom().empty() == true) //s'il n'y pas plus de salle visitée dans la pile
				player.getGUI().println("Tu es revenu au début du jeu!");
			else 
			{
				player.setRoom(player.getStackRoom().pop()); //Retourner la dernière salle visitée et retirer cette salle de la pile
				player.getGameEngine().printLocationInfo();

				if (player.getRoom().getImageName() != null)
					player.getGUI().showImage(player.getRoom().getImageName());
				
				player.getGUI().resetTextPanel();
			}
		}
	}
}
