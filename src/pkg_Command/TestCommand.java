package pkg_Command;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import pkg_Characters.Player;



public class TestCommand extends Command
{
	private Scanner sr;
	
	public TestCommand()
	{}
	
	public void execute(Player player)
	{
		try 
		{
			if(this.hasSecondWord())
			{
				String secondWord = this.getSecondWord();
				
				switch(secondWord)
				{
					case "gagner":
					{
						sr = new Scanner(new File("./src/Tests/gagner.txt"));
						break;
					}
					case "commands":
					{
						sr = new Scanner(new File("./src/Tests/testCommand.txt"));
						break;
					}
					default:
						sr = null;
									
				}			
				if(sr != null)
				{
					while (sr.hasNextLine()) 
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
