

public class TestCommand extends Command
{
	public TestCommand()
	{}
	
	public void execute(Player player)
	{
		player.getGameEngine().test();
	}
}
