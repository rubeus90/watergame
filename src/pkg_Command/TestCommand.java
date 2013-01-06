package pkg_Command;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import pkg_Characters.Player;

/**
 * Cette classe gere la commande "test" du jeu, qui permet a partir d'un fichier de texte de lancer les commandes
 * du jeu dans le but de tester le jeu plus rapidement
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class TestCommand extends Command
{
	private Scanner sr;
	
	/**
	 * Constructeur qui permet de creer une commande Test
	 */
	public TestCommand()
	{}
	
	/**
	 * Permettre de lire les lignes d'un fichier de texte et d'executer ligne par ligne les commandes inscrites
	 */
	public void execute(Player player)
	{
		try 
		{
			if(this.hasSecondWord())
			{
				String secondWord = this.getSecondWord();
				
				//On lance les fichiers de teste différents en fonction du second mot de la commande
				switch(secondWord)
				{
					case "gagner":
					{
						sr = new Scanner(new File("./src/Tests/gagner.txt"));
						break;
					}
					case "commands":
					{
						sr = new Scanner(new File("./src/Tests/commands.txt"));
						break;
					}
					case "perdre":
					{
						sr = new Scanner(new File("./src/Tests/perdre.txt"));
						break;
					}
					default:
						sr = null;
									
				}			
				if(sr != null)
				{
					while (sr.hasNextLine()) //tant que le fichier de texte n'est pas fini, on lit et exécuter la ligne suivante
					{
						player.getGameEngine().interpretCommand(sr.nextLine());
					}
				}
				else
					player.getGUI().println("Je sais pas quoi tester....");
			}
			else
				player.getGUI().println("Je sais pas quoi tester....");
		}
						

		catch (FileNotFoundException ex) 
		{
			player.getGUI().println("Fichier non trouvé");
		}
		
	}
}
