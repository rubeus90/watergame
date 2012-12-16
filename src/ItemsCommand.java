

public class ItemsCommand extends Command
{
	public ItemsCommand()
	{}
	
	public void execute(Player player)
	{
		player.getGameEngine().printInventaire();
	}

}
