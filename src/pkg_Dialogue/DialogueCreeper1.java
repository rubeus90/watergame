package pkg_Dialogue;


import pgk_Game.GameEngine;
import pkg_Characters.Bots;
import pkg_Room.Room;

public class DialogueCreeper1 extends Dialogue
{
	public DialogueCreeper1()
	{
		super();
	}
	
	public void afficheDialogue(GameEngine engine)
	{
		engine.getGUI().showDialogue();
		
		int etape = super.getEtape();
	
		switch(etape)
		{
			case 1:
			{
				engine.getGUI().println("Creeper : Hey hey, toi ! Oui toi là !  Je suis bloqué dans un trou juste là. Viens m'aider !" + "\n");
				break;
			}
			case 2:
			{
				if(this.help(true))
					engine.getGUI().println("Creeper : Quoi tu n'as pas de corde ? Tu comptais m'aider comment ? " +
							"Tu es élastigirl peut-être ? Je plaisante, t'inquiètes pas. " +
							"Je pense que tu pourra trouver une corde en haut du PIC. Si tu m'aides à sortir de là, " +
							"je te donnerai quelques conseils pour survivre sur cette île" + "\n");
				else
				{
//					engine.getGUI().println("Tchiiiiiiiiiiii...... BOOOOOOOM!!!!" + "Creeper a explosé dans la face, tu es mort!!!! Faillait accepter d'aider Creeper, espèce de méchant sans coeur!!!!" + "\n");
					engine.getGUI().createGameOver("creeper");
				}
				break;
			}
			case 3:
			{
				engine.getGUI().print("Mais il parraît que le chemin pour aller jusqu'au pic était détruit par une chute de pierre." +
						"Mon petit doigt me dit qu'une certaine pierre magique peut t'aider à aller n'importe ou tu veux sur l'île," +
						"c'est peut-être ça ce qu'il te faut....");
				break;
			}
			default: 
			{
				engine.getGUI().closeDialogue();
				super.setEtape(0);
			}
		}
	}
	
	public boolean help(final boolean choix)
	{
		return choix;			
	}
}
