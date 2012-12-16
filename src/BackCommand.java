


public class BackCommand extends Command
{
	public BackCommand()
	{}
	
	public void execute(Player player)
	{
		player.diminueSante(10);
		player.back(this);
	}
}
