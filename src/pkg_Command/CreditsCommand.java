package pkg_Command;
import pkg_Characters.Player;



public class CreditsCommand extends Command
{
	public CreditsCommand()
	{}
	
	public void execute(Player player)
	{
		player.getGUI().println("Nous sommes NGUYEN Hong Ngoc aka Ngocky et PATOIS Thibault, deux étudiants très brillants en 3ème de l'ESIEE (LES E3S EN FORCE!!!!). Nous avons crée ce jeu dans le cadre de l'apprentissage de Java, mais nous avons mis là dedans tout notre amour (et n'oublie pas Thibault qui a mis tout son coeur dans les nombreuses versions de notre page web), donc j'espère que tu vas aimer notre jeu :D");
	}
	
}
