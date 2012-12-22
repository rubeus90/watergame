package pkg_Command;
import pkg_Characters.Player;
import pkg_Items.Item;



public class TakeCommand extends Command
{
	public TakeCommand()
	{}
	
	public void execute(Player player)
	{
		if (!this.hasSecondWord()) 
		{
			player.getGUI().println("Il faut préciser quel objet tu veux prendre!");
		} else 
		{
			String mot = this.getSecondWord();
				
			if (!player.getRoom().getItemListe().containsKey(mot))
			{
				player.getGUI().println("Mais il n'y a pas de " + mot + " ici");
			}
			else 
			{		
				
				Item item = player.getRoom().getItemListe().getValue(mot);
				int poidsFuture = player.getPoidsInventaire() + item.getWeightItem();

				if (poidsFuture > player.getMaxPoids())
					player.getGUI().println("Ton sac est déjà trop lourd, tu ne peux pas prendre plus d'objet. Jette un autre objet sinon!");
				else 
				{
					// Item item = aCurrentRoom.getItemListe().getValue(mot);
					player.getItemListe().putItem(mot, item);
					player.getRoom().deleteItem(mot);
					player.getGUI().println("Tu as pris un(e) " + mot);
						
				}
			}
			
			player.getGameEngine().printInventaire();
		}
	}
}
