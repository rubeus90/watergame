package pkg_Command;
import java.util.StringTokenizer;



/**
  * La class Parser ligne les entrees utilisateurs et essaye de les convertir en
 * commandes pour le jeux. A chaque fois qu'elle est appelee, la class Parser
 * lit les lignes du terminal et les decoupes en deux mots qui sont deux
 * commandes. Cette class permet ensuite de convertir les commandes en un objet
 * de la class Command.
 * 
 * La class Parser a un nombre determine de commande. La class Parser permet de
 * comparer les entres du joueur avec les commandes connues et si la commande
 * est inconnu, elle revoie un objet signe comme une command invalide.
 * 
 */
public class Parser 
{
	private CommandWords commands;
	
	/**
	 * Constructeur qui permet de cree un parser qui va lire les entrees.
	 */
	public Parser() 
	{
		commands = new CommandWords();
//		reader = new Scanner(System.in);
	}

	/**
	 * Retourner la commande entree par l'utilisateur qui comporte 2 mots
	 * 
	 * @return la commande entree par l'utlisateur
	 */
	public Command getCommand(String inputLine) 
	{
		String word = null;
		String word2;

		StringTokenizer tokenizer = new StringTokenizer(inputLine);

		if (tokenizer.hasMoreTokens())
			word = tokenizer.nextToken(); //retourner le premier mot sous forme de String

		if (tokenizer.hasMoreTokens())
			word2 = tokenizer.nextToken(); //retourner le second mot sous forme de String
		else
			word2 = null; //si la commande entrée n'a pas de second mot

		//On crée une commande à partir des 2 mots récupérés
		Command command = commands.get(word);
		command.setSecondWord(word2);
		
		return command;
	}
	
	/**
	 * Retourner les commandes du jeu
	 * 
	 * @return les commandes du jeu
	 */
	public String showCommands() {
		return commands.getCommandlist();
	}
}
