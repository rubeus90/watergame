package pkg_Command;

import pkg_Characters.Player;

/**
 * Cette classe gere la commande "utiliser" du jeu, qui permet au joueur d'utiliser le Beamer pour se teleporter
 * vers la salle ou le Beamer etait charge
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class UtiliserCommand extends Command
{
	/**
	 * Constructeur qui permet de creer une commande Utiliser
	 */
	public UtiliserCommand()
	{}
	
	/**
	 * Quand le Beamer est charge, permet au joueur de se teleporter vers la salle ou le Beamer etait charge.
	 * 
	 * Cette utilisation n'est valable qu'une seule fois par recharge, apres la téléportation, le joueur doit
	 * recharger le Beamer dans une salle pour pouvoir se téléporter encore une fois.
	 */
	public void execute(Player player)
	{
		if(!player.getBeamerRoom().isEmpty()) //Si le Beamer est chargé
		{
			player.setRoom(player.getBeamerRoom().get(0)); //imposer la salle du joueur, donc le téléporter
			player.getGameEngine().printLocationInfo();

			if (player.getRoom().getImageName() != null) 
			{
				player.getGUI().showImage(player.getRoom().getImageName());
			}
			
			player.getGUI().resetTextPanel();
			player.getGUI().println("Tu as été téléporter à l'endroit ou le Beamer était chargé. Pour le réutiliser de nouveau," +
					" il faut que tu le recharge");
			
			//Enlever la salle de l'ArrayList, comme ça à chaque fois on veut utiliser le Beamer, il faut le recharger
			player.getBeamerRoom().clear();
		}
		else
		{
			player.getGUI().println("Tu n'as pas encore chargé le beamer");
		}
	}
}
