

public class DrinkCommand extends Command
{
	public DrinkCommand()
	{}
	
	public void execute(Player player)
	{
		if (!this.hasSecondWord()) 
		{
			player.getGUI().println("Tu veux boire quoi crétin?");
		} 
		else 
		{
			player.boire(this);
			player.getGUI().println("Ton niveau de santé est maintenant de " + player.getSante());
		}
	}
}
