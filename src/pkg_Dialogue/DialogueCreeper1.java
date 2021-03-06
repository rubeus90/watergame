package pkg_Dialogue;

import pkg_Characters.Bots;
import pkg_Game.GameEngine;
import pkg_Items.Item;

/**
 * Cette classe gere le premier dialogue avec Creeper dans la foret
 * 
 * @author NGUYEN Hong Ngoc
 * @author PATOIS Thibault
 *
 */
public class DialogueCreeper1 extends Dialogue
{
	/**
	 * Constructeur pour le premier dialogue avec Creeper
	 */
	public DialogueCreeper1()
	{
		super();
	}
	
	/**
	 * Methode qui permet d'afficher le dialogue avec Creeper dans la foret
	 */
	public void afficheDialogue(GameEngine engine)
	{
		if(engine.gameResetted()) //si le jeu est recommance, on remet l'etape a 1
		{
			super.setEtape(1);
			engine.setResetGame(); //on remet le jeu comme s'il n'est pas recommence, sinon le dialogue va tourjous rester a l'etape 1
		}
		
		engine.getGUI().showDialogue(1);
		
		if(!engine.getPlayer().getItemListe().containsKey("corde")) //si le joueur n'a pas de corde (logiquement le 1er dialogue)
		{
			switch(super.getEtape())
			{
				case 1:
				{
					engine.getGUI().showDialogue(2);
					
					engine.getGUI().println("Creeper : Hey hey, toi ! Oui toi là !  Je suis bloqué dans un trou juste là. Viens m'aider !");
					super.suivant();
					break;
				}
				case 2:
				{
					engine.getGUI().println("Creeper : Quoi tu n'as pas de corde ? Tu comptais m'aider comment ? " +
								"Tu es élastigirl peut-être ? Je plaisante, t'inquiètes pas. " +
								"Je pense que tu pourra trouver une corde en haut du PIC. Si tu m'aides à sortir de là, " +
								"je te donnerai quelques conseils pour survivre sur cette île");
					
					super.suivant();
					break;
				}
				case 3:
				{
					engine.getGUI().println("Creeper : Mais il parraît que le chemin pour aller jusqu'au pic était détruit par une chute de pierre." +
							"Mon petit doigt me dit qu'une certaine pierre magique peut t'aider à aller n'importe ou tu veux sur l'île," +
							"c'est peut-être ça ce qu'il te faut....");
					super.suivant();
					break;
				}
				case 0:
				{
					engine.getGUI().println("Creeper : Tu attends quoi encore? Va chercher une corde au pic pour m'aider");
					super.setEtape(4);
					break;
				}
				default: 
				{
					engine.getGUI().closeDialogue();
					super.setEtape(0);
				}
			}
		}
		else //si le joueur a une corde pour l'aider (logiquement le 2eme dialogue)
		{
			if(super.getEtape() == 0)
			{
				super.setEtape(1);
			}
			
			switch(super.getEtape())
			{
				case 1:
				{
					engine.getGUI().println("Creeper : Ah voilà tu es de retours! Je vois que tu as trouvé une corde." +
							"Maintenant utilise la corde pour me sortir de là!");
					super.suivant();
					break;
				}
				case 2:
				{
					engine.getGUI().println("Creeper : Merci de m'avoir sauvé. Pour te remercier, je vais te donner cette épée.");
					engine.getPlayer().getItemListe().putItem("epee", new Item("Une épée en acier rare", 2));
					super.suivant();
					break;
				}
				case 3:
				{
					engine.getGUI().println("Creeper : Allons-nous en d'ici, c'est trop risqué de rester ici!");
					super.suivant();
					break;
				}
				case 4:
				{
					engine.getGUI().println("Creeper : Il faut que l'on se sépare. Si tu veux me retrouver, je serai dans la " +
							"grotte pas loin d'ici.");
					super.suivant();
					break;
				}
				default: 
				{
					engine.getPlayer().getRoom().removeBot("Creeper");
					engine.getArrayListRoom().get(4).addBot("Creeper", new Bots("Creeper", null, 80, false));
					engine.getArrayListRoom().get(4).setImage("images/grotteAvecCreeper.png");
					engine.getPlayer().getRoom().setImage("images/foret.png");
					engine.getGUI().showImage(engine.getPlayer().getRoom().getImageName());
					engine.getGUI().closeDialogue();
					
				}
			}
		}
		
	}
}
