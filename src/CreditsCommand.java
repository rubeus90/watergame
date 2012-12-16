

public class CreditsCommand extends Command
{
	public CreditsCommand()
	{}
	
	public void execute(Player player)
	{
		player.getGameEngine().credits();
	}
}
