package pkg_Command;
import pkg_Characters.Player;



public class DrinkCommand extends Command
{
	public DrinkCommand()
	{}
	
	public void execute(Player player)
	{
		if (!this.hasSecondWord()) 
		{
			player.getGUI().println("\n" + "Tu veux boire quoi crétin?");
		} 
		else 
		{
			String mot = this.getSecondWord();

			if (mot.equals("potion")) {
				if (player.getRoom().containPotion("Potion")) 
				{
					player.augmenteSante(10);
					int index = player.getRoom().indexPotion("Potion");
					player.getRoom().getArrayList().remove(index);
				} 
				else 
				{
					player.getGUI().println("\n" + "Il n'y a pas (ou plus) de cette potion dans cette salle");
				}

				}

			else if (mot.equals("soin")) 
			{
				if (player.getRoom().containPotion("Soin")) 
				{
					player.augmenteSante(20);
					int index = player.getRoom().indexPotion("Soin");
					player.getRoom().getArrayList().remove(index);
				} 
				else 
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
