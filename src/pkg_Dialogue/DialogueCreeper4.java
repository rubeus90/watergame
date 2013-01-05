package pkg_Dialogue;

import pkg_Game.GameEngine;

public class DialogueCreeper4 extends Dialogue
{
	public DialogueCreeper4()
	{
		super();
	}
	
	public void afficheDialogue(GameEngine engine)
	{
		if(engine.gameResetted())
		{
			super.setEtape(1);
			engine.setResetGame();
		}
		
		engine.getGUI().showDialogue(1);
		
		switch(super.getEtape())
		{
			case 1:
			{		
				engine.getGUI().println("Creeper : Voilà la salle secrète! Le mythe est vrai! Et voilà l'hallebarde " +
						"qui va te rendre le plus puissant sur l'île, prends-le!");
				super.suivant();
				break;
			}
			case 2:
			{
				engine.getGUI().println("Creeper : Maintenant que l'on peut battre n'importe quel ennimi, allons nous chercher notre " +
						"dernier ennemi sur l'île, Blaze. J'ai vu qu'il était à la plage!");
				super.suivant();
				break;
			}
			case 3:
			{
				engine.getGUI().println("Creeper : Oh mon Dieu! La porte d'entrée s'est éfondré, on ne peut plus passer par là! " +
						"Il faut que l'on passe par les tunnels de la mine de Moria pour sortir!");
				super.suivant();
				break;
			}
			case 4:
			{
				engine.getGUI().println("Creeper : Cependant ces mines sont très dangereuses et on ne sait jamais où on arrive, " +
						"car ces tunnels peuvent nous emmerner partout sur l'île, même parfois directement dans l'océan!"
						+ "Fais bien attention à toi donc! Et fais attention aux éfondrement des tunnels aussi, ils sont très vieux!");
				super.suivant();
				break;
			}
			default :
			{
				engine.getPlayer().getRoom().removeBot("Creeper");
				engine.getGUI().closeDialogue();
			}
		}
	}
}
