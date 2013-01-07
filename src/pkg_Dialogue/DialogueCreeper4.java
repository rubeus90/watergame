package pkg_Dialogue;

import pkg_Game.GameEngine;

/**
 * Cette classe gere le quatrieme dialogue avec Creeper dans la salle secrete
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class DialogueCreeper4 extends Dialogue
{
	/**
	 * Constructeur pour le quatrieme dialogue avec Creeper
	 */
	public DialogueCreeper4()
	{
		super();
	}
	
	/**
	 * Methode qui permet d'afficher le dialogue avec Creeper dans la salle secrete
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
				engine.getTransporterRoom().setImage("images/SalleSecrete.png");
				engine.getGUI().showImage(engine.getPlayer().getRoom().getImageName());
				engine.getGUI().closeDialogue();
				engine.getGUI().println("\n" + "\n" + "----------------------------------------------------------------------");
				engine.getGUI().println("Creeper est parti avant toi mais un tunnel s'est effondré sur lui, il est " +
						"mort dans les mines de Moria. C'est dommage, c'était un bon allié!");
			}
		}
	}
}
