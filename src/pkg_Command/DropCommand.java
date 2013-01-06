package pkg_Command;
import pkg_Characters.Player;
import pkg_Items.Item;


/**
 * Cette classe gere la commande "drop" du jeu, qui permet au joueur de jeter un objet dans son inventaire
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class DropCommand extends Command
{
	/**
	 * Constructeur qui permet de creer une commande Drop
	 */
	public DropCommand()
	{}
	
	/**
	 * Permettre de jeter l'objet demande par le joueur. L'objet demande est defini par le second mot de la commande.
	 * 
	 * Quand un objet est jete, il est retire de l'inventaire du joueur, et ajoute dans la liste des objets dans une salle
	 */
	public void execute(Player player)
	{
		if (!this.hasSecondWord()) 
		{
			player.getGUI().println("Il faut préciser quel objet tu veux jeter!");
		} else 
		{		
			String mot = this.getSecondWord();

			if (!player.getItemListe().containsKey(mot)) //vérifier par son nom si l'objet est présent dans l'inventaire du joueur
				player.getGUI().println("Tu n'as pas de " + mot);
			else {
				Item item = player.getItemListe().getValue(mot);
				player.getItemListe().removeItem(mot); //retirer l'objet de l'inventaire du joueur
				player.getRoom().addItem(mot, item); //ajouter l'objet à la liste des objets de la salle
			}			
			
			player.getGameEngine().printInventaire();
		}
	}
}
