

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
			player.drop(this);
			player.getGameEngine().printInventaire();
		}
	}
}
