

public class QuitCommand extends Command
{
	public QuitCommand()
	{}
	
	public void execute(Player player)
	{
		if (this.hasSecondWord())
			player.getGUI().println("Quit quoi? Si tu veux quitter le jeux, écris juste quit");
		else
			player.getGameEngine().endGame();
	}
}
