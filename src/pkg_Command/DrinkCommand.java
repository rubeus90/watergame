package pkg_Command;
import pkg_Characters.Player;

/**
 * Cette classe gere la commande "drink" du jeu, qui permet au joueur de boire une potion ou un soin
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class DrinkCommand extends Command
{
	/**
	 * Constructeur qui permet de creer une commande Drink
	 */
	public DrinkCommand()
	{}
	
	/**
	 * Permettre au joueur de boire une potion ou un soin. Les potions et les soins ne sont pas des objets deplaceables
	 * mais ils sont fixes dans des salles. 
	 * Quand le joueur boire une potion ou un soin, la potion en question est retiree de la salle, et la sante
	 * du joueur augmente. L'augmentation depend de la potion bue (une potion ou un soin)
	 */
	public void execute(Player player)
	{
		if (!this.hasSecondWord()) 
		{
			player.getGUI().println("\n" + "Tu veux boire quoi crétin?");
		} 
		else 
		{
			String mot = this.getSecondWord();

			//Si le joueur demande une potion
			if (mot.equals("potion")) 
			{
				if (player.getRoom().containPotion("Potion")) //s'il y a une potion dans la salle
				{
					player.augmenteSante(10);
					int index = player.getRoom().indexPotion("Potion");
					player.getRoom().getArrayList().remove(index); //retirer la potion de la salle
				} 
				else //s'il n'y a pas de potion
				{
					player.getGUI().println("\n" + "Il n'y a pas (ou plus) de cette potion dans cette salle");
				}

			}
			//Si le joueur demande un soin
			else if (mot.equals("soin")) 
			{
				if (player.getRoom().containPotion("Soin")) //s'il y a un soin dans la salle
				{
					player.augmenteSante(20);
					int index = player.getRoom().indexPotion("Soin");
					player.getRoom().getArrayList().remove(index); //retirer le soin de la salle
				} 
				else //s'il n'y a pas de soin
				{
					player.getGUI().println("Il n'y a pas (ou plus) de ce soin dans cette salle");
				}
			}

			else
				player.getGUI().println("\n" + "Ce que tu demandes de boire n'existe pas!");
		}
			
			
			player.getGUI().println("\n" + "Ton niveau de santé est maintenant de " + player.getSante());
	}
	
}
