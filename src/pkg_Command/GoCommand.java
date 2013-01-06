package pkg_Command;
import pkg_Characters.Player;
import pkg_Room.Room;
import pkg_Dialogue.*;

/**
 * Cette classe gère la commande "go" du jeu, qui permet au joueur de se déplacer entre les endroits différents
 * du jeu
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class GoCommand extends Command
{
	/**
	 * Constructeur qui permet de créer une commande Go
	 */
	public GoCommand()
    {}

	/**
	 * Permettre de déplacer le joueur entre les salles du jeu.
	 * La direction du déplacement est défini par le second mot de la commande.
	 * Certaines salles du jeu est en fait l'océan, si le joueur se déplace vers ces endroits, il est mort noyé et a perdu le jeu
	 */
    public void execute(Player player)
    {
    	player.diminueSante(2);
		player.setMaxPoids();
		
		
		if (!this.hasSecondWord()) 
		{
			player.getGUI().println("Aller ou exactement, soit un peu plus précis!");
		}

		// Stocker la salle actuelle dans le stack (pour la méthode back)
		player.getStackRoom().push(player.getRoom());

		String direction = this.getSecondWord(); //la direction demandée par le joueur

		Room nextRoom = player.getRoom().getExit(direction);

		if (nextRoom == null) //si la salle dans la direction demandée n'existe pas, ou n'est pas atteignable
		{
			player.getGUI().println("\n" + "Tu ne peux pas aller par là, soit il n'y a rien ici, soit le chemin que tu " +
					"viens de prendre est de sens unique");
		} 
		else 
		{
			player.setRoom(nextRoom);
			
			//Si la salle suivante n'est pas l'eau, le jeu continue, si c'est l'eau, le joueur est mort, on arrête le jeu
			if(player.getRoom().getNomRoom() != "eau")
			{
				player.getGameEngine().printLocationInfo();

				if (player.getRoom().getImageName() != null) 
				{
					player.getGUI().showImage(player.getRoom().getImageName());
				}
				
				player.getGUI().resetTextPanel();
				
				player.getGUI().showBoutonTeleporter();
							
			}
			else
			{
				player.getGUI().createGameOver("eau");
			}
		}	
    }
}
