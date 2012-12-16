

public class TeleporterCommand extends Command
{
	public TeleporterCommand()
	{}
	
	public void execute(Player player)
	{
		if(player.getGameEngine().getBeamer().getValueActivation())
		{
			player.teleporter(this);
			player.getGameEngine().getBeamer().setActivation(false);
		}
		else
			player.getGUI().println("Mais c'est possible ça? Mon petit doigt me dit qu'il faut une pierre magique couplé avec la force surnaturelle de l'autel magique!");
	}
}
