

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
			player.take(this);
			player.getGameEngine().printInventaire();
		}
	}
}
