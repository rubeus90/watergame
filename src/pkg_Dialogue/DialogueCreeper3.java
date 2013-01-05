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
//			case 3:
//			{
//				engine.getGUI().println("D'après le message secret, il faut que tu réussis à une épreuve dans ce temple pour pouvoir" +
//						" ouvrir la porte vers la salle secrète");
//				super.suivant();
//				break;
//			}
//			case 4:
//			{
//				engine.getGUI().println("Cette épreuve va tester ta réactivité, alors sois bien attentif! " +
//						"Il y aura 9 pierres magiques diposées devant toi. Les pierres vont s'allumer un par un aléatoirement, " +
//						"il faut que tu réussis à appuyer dessus quand la pierre s'allume. Tu n'as que 5 secondes pour cette " +
//						"épreuve, si tu arrives à appuyer sur au moins 7 pierres, c'est gagné!");
//				super.suivant();
//				break;
//			}
			
			default: 
			{
//				engine.getGUI().createMinijeu();
				engine.getGUI().println("Creeper : Cette feuille t'as permis de trouver la porte pour aller dans la salle secrète! " +
						"Allons-y!");
				engine.getGUI().createButtonDoor();
				
				engine.getPlayer().getRoom().removeBot("Creeper");
				engine.getTransporterRoom().addBot("Creeper", new Bots("Creeper", null, 80, false));
			}
		}		
	}
}
