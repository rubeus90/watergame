package pkg_Dialogue;

import pkg_Game.GameEngine;

/**
 * Cette classe gere le dialogue avec Blaze
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class DialogueBlaze extends Dialogue
{
	/**
	 * Constructeur du dialogue avec Blaze
	 */
	public DialogueBlaze()
	{
		super();
	}

	/**
	 * Methode qui permet d'afficher le dialogue avec Blaze
	 */
	public void afficheDialogue(GameEngine engine)
	{
		if(engine.gameResetted()) //si le jeu est recommance, on remet l'etape a 1
		{
			super.setEtape(1);
			engine.setResetGame(); //on remet le jeu comme s'il n'est pas recommence, sinon le dialogue va tourjous rester a l'etape 1
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
