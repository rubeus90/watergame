package pkg_Dialogue;

import pkg_Game.GameEngine;

public class DialogueBlaze extends Dialogue
{
	public DialogueBlaze()
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
				engine.getGUI().println("Blaze : Ha, encore une nouvelle victime qui se présente toute seule à moi! Tu n'as pas peur de mourir?");
				super.suivant();
				break;
			}
			case 2:
			{
				engine.getGUI().println("Je suis Blaze, l'ancien gagnant de la dernière édition des Water Games, tu ne pexu pas me battre!");
				super.suivant();
				break;
			}
			case 3:
			{
				engine.getGUI().println("Haha tu crois que tu vas pouvoir gagner contre moi? Tu es en train de rêver là. Alors battons-nous!");
				super.suivant();
				break;
			}
			case 0:
			{
				engine.getGUI().println("Alors si tu n'as pas peur, t'attends quoi encore pour commencer à te battre?");
				super.setEtape(4);
				break;
			}
			default: 
			{
				engine.getGUI().closeDialogue();
				super.setEtape(1);
			}
		}		
	}
}
