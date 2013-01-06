package pkg_Command;

import pkg_Characters.Player;
import pkg_Room.Room;
import pkg_Room.TransporterRoom;

/**
 * Cette classe s'occupe de la commande "aleas"
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class AleasCommand extends Command
{
	/**
	 * Constructeur qui permet de créer une commande Aleas
	 */
	public AleasCommand()
	{}
	
	/**
	 * Quand on tape la commande "aleas + nom d'une salle", la TransporterRoom (la salle secrete dans le jeu) va toujour 
	 * emmener vers la salle avec le nom demande
	 * 
	 * Quand on tape la commande "aleas" sans un deuxieme mot, le mode aleatoire du TransporterRoom est reactive
	 */
	public void execute(Player player)
	{
		TransporterRoom secret = player.getGameEngine().getTransporterRoom();
		
		if (!this.hasSecondWord()) 
		{
			secret.setTestMode(false); //réactiver le mode aléatoire
		}
		else
		{			
			secret.setTestMode(true);  //activer le mode test, donc la téléportation ne sera plus aléatoire
			Room room = player.getGameEngine().chooseRoom(this.getSecondWord());
			secret.setTestModeRoom(room);
		}
	}
}
