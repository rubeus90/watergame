package pkg_Command;
import pkg_Characters.Player;
import pkg_Room.Room;

/**
 * Cette classe gere la commande "teleporter" du jeu, qui permet au joueur de se teleporter depuis n'importe quelle salle
 * du jeu vers n'importe quelle salle
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class TeleporterCommand extends Command
{
	/**
	 * Constructeur qui permet de creer une commande Teleporter
	 */
	public TeleporterCommand()
	{}
	
	/**
	 * Permettre au joueur de se teleporter vers n'importe quelle salle du jeu.
	 * 
	 * A partir d'une salle quelconque, on utlise la commande "teleporter + nom de la salle" pour se teleporter vers cette salle
	 */
	public void execute(Player player)
	{
		if(player.getGameEngine().getPierre().getValueActivation()) //si la pierre magique est activé
		{
			if (!this.hasSecondWord()) 
			{
				player.getGUI().println("Il faut préciser ou tu veux être téléporté!");
			}
			else
			{
				// Stocker la salle actuelle dans le stack (pour la méthode back)
				player.getStackRoom().push(player.getRoom());
				
				//La salle vers la quelle on se téléporte est définie par le second mot de la commande
				Room nextRoom = player.getGameEngine().chooseRoom(this.getSecondWord());

				if (nextRoom == null) 
				{
					player.getGUI().println("L'endroit que tu viens de demander n'existe pas, ou tu peux pas te téléporter là-bas!");
				} 
				else 
				{
					player.setRoom(nextRoom);
					player.getGameEngine().printLocationInfo();

					if (player.getRoom().getImageName() != null) 
					{
						player.getGUI().showImage(player.getRoom().getImageName());
					}
					
					player.getGUI().resetTextPanel();
				}			
				
				//on désactive la pierre magique, si le joueur veut l'utiliser encore, il faut le recharger
				player.getGameEngine().getPierre().setActivation(false); 
				
				player.getGUI().showBoutonTeleporter();
			}
		}
		else
			player.getGUI().println("Mais c'est possible ça? Mon petit doigt me dit qu'il faut une pierre magique couplé " +
					"avec la force surnaturelle de l'autel magique!");
	}
}
