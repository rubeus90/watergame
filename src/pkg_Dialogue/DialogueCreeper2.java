package pkg_Dialogue;


import pgk_Game.GameEngine;
import pkg_Characters.Bots;
import pkg_Items.Item;
import pkg_Room.Room;

public class DialogueCreeper2 extends Dialogue
{
	public DialogueCreeper2()
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
				engine.getGUI().println("Creeper : ");
				super.suivant();
				break;
			}
			case 2:
			{
				engine.getGUI().println("Creeper : ");
				super.suivant();
				break;
			}
			case 3:
			{
				engine.getGUI().println("Creeper : ");
				super.suivant();
				break;
			}
			default: 
			{
				engine.getPlayer().getRoom().removeBot("Creeper");
//				engine.getArrayListRoom().get(1).addBot("Creeper", new Bots("Creeper", null, 80, false));
				engine.getGUI().closeDialogue();				
			}
		}
	}
	
}
