package pkg_Dialogue;

import pkg_Characters.Bots;
import pkg_Game.GameEngine;

public class DialogueCreeper3 extends Dialogue
{
	public DialogueCreeper3()
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
		
		if(engine.getPlayer().getItemListe().containsKey("papier"))
		{
			if(super.getEtape() == 10)
			{
				super.setEtape(1);
			}
			switch(super.getEtape())
			{
				case 1:
				{		
					engine.getGUI().println("Creeper : Donne moi voir ton papier. Dans ce temple il y a un flamme magique" +
							" qui nous permet de lire les écrits secrets qui sont invisibles à l'oeil nu, c'est peut-être " +
							"le cas de ce papier");
					super.suivant();
					break;
				}
				case 2:
				{
					engine.getGUI().println("Creeper : J'avais bien raison. Ce papier contient le secret pour trouver la salle" +
							"secrète ou se trouve la hallebarde la plus puissante du monde qui te rendera invincible!");
					super.suivant();
					break;
				}
				default: 
				{
					engine.getGUI().println("Creeper : Cette feuille t'as permis de trouver la porte pour aller dans la salle secrète! " +
							"Allons-y!");
					engine.getGUI().createButtonDoor();
					
					engine.getPlayer().getRoom().removeBot("Creeper");
					engine.getTransporterRoom().addBot("Creeper", new Bots("Creeper", null, 80, false));
					engine.getPlayer().getRoom().setImage("images/temple.png");
					engine.getTransporterRoom().setImage("images/SalleSecrete.png");
				}
			}
		}
		else
		{
			switch(super.getEtape())
			{
				case 1:
				{
					engine.getGUI().println("Creeper : Donne moi voir ton papier. Dans ce temple il y a un flamme magique" +
							" qui nous permet de lire les écrits secrets qui sont invisibles à l'oeil nu, c'est peut-être " +
							"le cas de ce papier");
					super.suivant();
					break;
				}
				case 2:
				{
					engine.getGUI().println("Creeper : Mais alors tu as mis ou le papier? Il nous en faut pour pouvoir" +
							"trouver la porte vers la salle secrète!");
					super.suivant();
					break;
				}
				case 3:
				{
					engine.getGUI().println("Creeper : Alors si tu l'as posé quelques parts, vas le chercher et reviens ici!");
					super.suivant();
					break;
				}
				case 10:
				{
					engine.getGUI().println("Creeper : Tu attends quoi encore? Vas chercher le papier!");
					super.setEtape(4);
					break;
				}
				default: 
				{
					engine.getGUI().closeDialogue();		
					super.setEtape(10);
				}
			}
		}
				
	}
}
