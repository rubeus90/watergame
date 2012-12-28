package pkg_Dialogue;


import pgk_Game.GameEngine;
import pkg_Characters.Bots;
import pkg_Items.Item;
import pkg_Room.Room;

public class DialogueEnderman extends Dialogue
{
	public DialogueEnderman()
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
		int etape = super.getEtape();
		
		switch(etape)
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
