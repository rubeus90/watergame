

public class GoCommand extends Command
{
	public GoCommand()
    {
    }

    public void execute(Player player)
    {
    	player.diminueSante(10);
		player.setMaxPoids();
		player.goRoom(this);
	}
}
