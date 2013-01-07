package pkg_Dialogue;

import pkg_Characters.Bots;
import pkg_Game.GameEngine;

/**
 * Cette classe gere le troisieme dialogue avec Creeper dans le temple
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class DialogueCreeper3 extends Dialogue
{
	/**
	 * Constructeur pour le troisieme dialogue avec Creeper
	 */
	public DialogueCreeper3()
	{
		super();
	}
	
	/**
	 * Methode qui permet d'afficher le dialogue avec Creeper dans le temple
	 */
	public void afficheDialogue(GameEngine engine)
	{
		if(engine.gameResetted()) //si le jeu est recommance, on remet l'etape a 1
		{
			super.setEtape(1);
			engine.setResetGame(); //on remet le jeu comme s'il n'est pas recommence, sinon le dialogue va tourjous rester a l'etape 1
		}
		
		engine.getGUI().showDialogue(1);
		
		if(engine.getPlayer().getItemListe().containsKey("papier")) //si le joueur a toujour le papier sur lui
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
					engine.getTransporterRoom().setImage("images/sallesecretecreeper.png");
				}
			}
		}
		else //si le joueur a jete le papier quelques parts, demande lui d'aller le chercher
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
