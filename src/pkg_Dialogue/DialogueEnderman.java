package pkg_Dialogue;


import pkg_Game.GameEngine;

/**
 * Cette classe gere le dialogue avec Enderman
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class DialogueEnderman extends Dialogue
{
	/**
	 * Constructeur du dialogue avec Enderman
	 */
	public DialogueEnderman()
	{
		super();
	}
	
	/**
	 * Methode qui permet d'afficher le dialogue avec Enderman
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
				engine.getGUI().println("Enderman : Mouhaha je suis Enderman, je viens du monde de l'Ender, et je vais être le vainqueur du Water Games!");
				super.suivant();
				break;
			}
			case 2:
			{
				engine.getGUI().println("Enderman : Tu ne peux pas gagner contre moi!");
				super.suivant();
				break;
			}
			case 3:
			{
				engine.getGUI().println("Enderman : Tu attends quoi encore à te battre espèce de lâche?");
				super.suivant();
				break;
			}
			case 0:
			{
				engine.getGUI().println("");
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
