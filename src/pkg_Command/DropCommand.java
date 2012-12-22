package pkg_Command;
import pkg_Characters.Player;
import pkg_Items.Item;



public class DropCommand extends Command
{
	public DropCommand()
	{}
	
	public void execute(Player player)
	{
		if (!this.hasSecondWord()) 
		{
			player.getGUI().println("Il faut préciser quel objet tu veux jeter!");
		} else 
		{		
			String mot = this.getSecondWord();

			if (!player.getItemListe().containsKey(mot))
				player.getGUI().println("Tu n'as pas de " + mot);
			else {
				Item item = player.getItemListe().getValue(mot);
				player.getItemListe().removeItem(mot);
				player.getRoom().addItem(mot, item);
			}			
			
			player.getGameEngine().printInventaire();
		}
	}
}
