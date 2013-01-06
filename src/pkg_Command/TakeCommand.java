package pkg_Command;
import pkg_Characters.Player;
import pkg_Items.Item;

/**
 * Cette classe gère la commande "take" du jeu, qui permet au joueur de prendre un objet dans la salle et 
 * l'ajouter dans son inventaire
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class TakeCommand extends Command
{
	/**
	 * Constructeur qui permet de créer une commande Take
	 */
	public TakeCommand()
	{}
	
	/**
	 * Permettre de prendre un objet demandé par l'utlisateur. L'objet demandé est défini par la second mot de la commande
	 * 
	 * Quand un objet est pris par le joueur, il est ajouté dans l'inventaire du joueur et retiré de la liste des
	 * objets disponibles dans la salle
	 */
	public void execute(Player player)
	{
		if (!this.hasSecondWord()) 
		{
			player.getGUI().println("Il faut préciser quel objet tu veux prendre!");
		} else 
		{
			String mot = this.getSecondWord();
				
			if (!player.getRoom().getItemListe().containsKey(mot)) //vérifier par son si l'objet est présent dans la salle
			{
				player.getGUI().println("Mais il n'y a pas de " + mot + " ici");
			}
			else 
			{		
				
				Item item = player.getRoom().getItemListe().getValue(mot);
				int poidsFuture = player.getPoidsInventaire() + item.getWeightItem();

				//S'assurer que le joueur ne puisse pas prendre plus d'objet que le poids max défnini par sa santé le permet
				if (poidsFuture > player.getMaxPoids())
					player.getGUI().println("Ton sac est déjà trop lourd, tu ne peux pas prendre plus d'objet. Jette un autre objet sinon!");
				else 
				{
					player.getItemListe().putItem(mot, item); //ajouter l'objet à l'inventaire du joueur
					player.getRoom().deleteItem(mot); //retirer l'objet de la liste des objets de la salle
					player.getGUI().println("Tu as pris un(e) " + mot);
						
				}
			}
			
			player.getGameEngine().printInventaire();
		}
	}
}
