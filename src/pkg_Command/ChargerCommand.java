package pkg_Command;

import pkg_Characters.Player;

/**
 * Cette classe gere la commande "charger" du jeu
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class ChargerCommand extends Command
{
	/**
	 * Constructeur qui permet de creer une commande Charger
	 */
	public ChargerCommand()
	{}
	
	/**
	 * Pemettre de charger le Beamer, c'est-a-dire ajouter la salle ou le joueur charge le Beamer dans la
	 * premiere case d'une ArrayList
	 * On utilise le syntaxe "charger" sans deuxieme mot dans une salle quelconque du jeu pour effectuer cette methode
	 */
	public void execute(Player player)
	{
		player.getBeamerRoom().add(0, player.getRoom());
		player.getGUI().println("Tu as chargé le beamer ici");
	}
}
